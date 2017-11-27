package ie.gmit.sw;

import java.io.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.ServerException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.ServletContext;



public class ServiceHandler extends HttpServlet {
	private String remoteHost = null;
	private static long jobNumber = 0;
	private final int POOL_SIZE = 6;
	
	private static Map<String, Resultator> outQueue; 
	private static BlockingQueue<Words> inQueue;
	private static ExecutorService executor ;
	
	private boolean checkProcessed;
	private String returningDefinitons = "Waiting for response...";
	
	public void init() {
		ServletContext ctx = (ServletContext) getServletContext();
		remoteHost = ctx.getInitParameter("RMI_SERVER"); //Reads the value from the <context-param> in web.xml
		
		outQueue = new HashMap<String, Resultator>();
		inQueue = new LinkedBlockingQueue<Words>();
		executor = Executors.newFixedThreadPool(POOL_SIZE);
		
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DictionaryService service = null;
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		
		try {
			service = (DictionaryService) Naming.lookup("rmi://localhost:1099/RMIdictionary");
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Request variables 
		String keyWord = req.getParameter("keyWordIndex");
		keyWord = keyWord.toUpperCase().replaceAll("\\s+", "");
		
		String taskNumber = req.getParameter("frmTaskNumber");
		
		
		out.print("<html><head><title>Distributed Systems Assignment</title>");		
		out.print("</head>");		
		out.print("<body>");
		
		if (taskNumber == null){
			taskNumber = new String("T" + jobNumber);
			
			checkProcessed = false;

			Words requestDefinition = new Words(keyWord, taskNumber);
			inQueue.add(requestDefinition);
			
			
			Runnable work = new ServiceQueue(inQueue, outQueue, service);
			executor.execute(work);
			
			jobNumber++;
		} else {
			
			if (outQueue.containsKey(taskNumber)) {
				//get the Resultator object from outMap based on tasknumber
				Resultator outQItem = outQueue.get(taskNumber);

				System.out.println("\nChecking Status of Task No:" + taskNumber);

				checkProcessed = outQItem.isProcessed();

				// Check to see if the Resultator Item is Processed
				if (checkProcessed == true) {
					// Remove the processed item from Map by taskNumber
					outQueue.remove(taskNumber);
					
					//Get the Definitons of the Current Task
					returningDefinitons = outQItem.getResult();

					System.out.println("\nTask " + taskNumber + " Processed");
					System.out.println("String " + keyWord + "  -  "  + returningDefinitons);
				}
			}
		}
		
		out.print("<html><head><title>Distributed Systems Assignment</title>");		
		out.print("</head>");		
		out.print("<body>");
		
		out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
		out.print("<div id=\"r\"></div>");
		
		out.print("<font color=\"#993333\"><b>");
		out.print("RMI Server is located at " + remoteHost);
		out.print("<br>Response : " + keyWord);

		
		if(returningDefinitons != null){
			out.print("<br>Response:  " + returningDefinitons);
		}
		
		
		out.print("<form name=\"frmRequestDetails\">");
		out.print("<input name=\"keyWordIndex\" type=\"hidden\" value=\"" + keyWord + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("</form>");								
		out.print("</body>");	
		out.print("</html>");	
		
		//refresh the page
		out.print("<script>");
		out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 10000);");
		out.print("</script>");

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}
}