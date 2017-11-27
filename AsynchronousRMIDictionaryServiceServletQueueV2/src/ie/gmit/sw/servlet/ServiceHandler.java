package ie.gmit.sw.servlet;

import java.io.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.*;
import javax.servlet.http.*;

import ie.gmit.sw.ServiceSetup.DictionaryServiceInterface;
import ie.gmit.sw.TreadQueue.Request;

import javax.servlet.annotation.*;

/* NB: You will need to add the JAR file $TOMCAT_HOME/lib/servlet-api.jar to your CLASSPATH 
 *     variable in order to compile a servlet from a command line.
 */
@WebServlet("/getDefinition")

public class ServiceHandler extends HttpServlet {
	
	/* Declare any shared objects here. For example any of the following can be handled from 
	 * this context by instantiating them at a servlet level:
	 *   1) An Asynchronous Message Facade: declare the IN and OUT queues or MessageQueue
	 *   2) An Chain of Responsibility: declare the initial handler or a full chain object
	 *   1) A Proxy: Declare a shared proxy here and a request proxy inside doGet()
	 */
	
	private String environmentalVariable = null; //Demo purposes only. Rename this variable to something more appropriate
	private static long jobNumber = 0;
	
	
	private static final long serialVersionUID = 1L;
	private static DictionaryServiceInterface look_up;
	
	private final int POOL_SIZE = 6;
	
	private static Map<String, DictionaryServiceInterface> outQueue; 
	private static BlockingQueue<Request> inQueue;
	private static ExecutorService executor;
	private boolean checkProcessed;


	/* This method is only called once, when the servlet is first started (like a constructor). 
	 * It's the Template Patten in action! Any application-wide variables should be initialised 
	 * here. Note that if you set the xml element <load-on-startup>1</load-on-startup>, this
	 * method will be automatically fired by Tomcat when the web server itself is started.
	 */
	
	public void init() throws ServletException {
		ServletContext ctx = getServletContext(); //The servlet context is the application itself.
		
		//Reads the value from the <context-param> in web.xml. Any application scope variables 
		//defined in the web.xml can be read in as follows:
		
		environmentalVariable = ctx.getInitParameter("SOME_GLOBAL_OR_ENVIRONMENTAL_VARIABLE"); 
		
		
		
		outQueue = new HashMap<String, DictionaryServiceInterface>();
		inQueue = new LinkedBlockingQueue<Request>();
		executor = Executors.newFixedThreadPool(POOL_SIZE);
		
		System.out.println("Starting the client.....");


			try {
				look_up = (DictionaryServiceInterface) Naming.lookup("//localhost/RMIdictionary");
			} catch (MalformedURLException | RemoteException | NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}


	/* The doGet() method handles a HTTP GET request. Please note the following very carefully:
	 *   1) The doGet() method is executed in a separate thread. If you instantiate any objects
	 *      inside this method and don't pass them around (ie. encapsulate them), they will be
	 *      thread safe.
	 *   2) Any instance variables like environmentalVariable or class fields like jobNumber will 
	 *      are shared by threads and must be handled carefully.
	 *   3) It is standard practice for doGet() to forward the method invocation to doPost() or
	 *      vice-versa.
	 */
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Step 1) Write out the MIME type
		resp.setContentType("text/html"); 
		
		//Step 2) Get a handle on the PrintWriter to write out HTML
		PrintWriter out = resp.getWriter(); 
		
		//Step 3) Get any submitted form data. These variables are local to this method and thread safe...
		String title = req.getParameter("txtTitle");
		String taskNumber = req.getParameter("frmTaskNumber");

		//We could use the following to track asynchronous tasks. Comment it out otherwise...
		if (taskNumber == null){
			taskNumber = new String("T" + jobNumber);
			jobNumber++;
			//Add job to in-queue
		}else{
			RequestDispatcher dispatcher = req.getRequestDispatcher("/poll");
			dispatcher.forward(req,resp);
			//Check out-queue for finished job with the given taskNumber
		}
		
		
		//We can also dynamically write out a form using hidden form fields. The form itself is not
		//visible in the browser, but the JavaScript below can see it.
		out.print("<form name=\"frmRequestDetails\" action=\"poll\">");
		out.print("<input name=\"txtTitle\" type=\"hidden\" value=\"" + title + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");

		
		//JavaScript to periodically poll the server for updates (this is ideal for an asynchronous operation)
		out.print("<script>");
		out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\");"); //Refresh every 10 seconds
		out.print("</script>");


	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}
}