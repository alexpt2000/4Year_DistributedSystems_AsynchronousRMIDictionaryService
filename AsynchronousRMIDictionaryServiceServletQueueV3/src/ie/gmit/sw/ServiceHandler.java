package ie.gmit.sw;

import java.io.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceHandler extends HttpServlet {

	private static long jobNumber = 0;
	private final int POOL_SIZE = 6;

	private static Map<String, Validator> outQueue;
	private static BlockingQueue<Words> inQueue;
	private static ExecutorService executor;

	private boolean checkProcessed;
	private String returningDefinitons;
	private String oldKeyWord;

	/*
	 * This method is only called once, when the servlet is first started (like a
	 * constructor). It's the Template Patten in action! Any application-wide
	 * variables should be initialised here. Note that if you set the xml element
	 * <load-on-startup>1</load-on-startup>, this method will be automatically fired
	 * by Tomcat when the web server itself is started.
	 */
	public void init() {

		outQueue = new HashMap<String, Validator>();
		inQueue = new LinkedBlockingQueue<Words>();
		executor = Executors.newFixedThreadPool(POOL_SIZE);

	}

	/*
	 * The doGet() method handles a HTTP GET request. Please note the following very
	 * carefully: 1) The doGet() method is executed in a separate thread. If you
	 * instantiate any objects inside this method and don't pass them around (ie.
	 * encapsulate them), they will be thread safe. 2) Any instance variables like
	 * environmentalVariable or class fields like jobNumber will are shared by
	 * threads and must be handled carefully. 3) It is standard practice for doGet()
	 * to forward the method invocation to doPost() or vice-versa.
	 */

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DictionaryService service = null;

		// Step 1) Write out the MIME type
		resp.setContentType("text/html");

		// Step 2) Get a handle on the PrintWriter to write out HTML
		PrintWriter out = resp.getWriter();

		try {
			service = (DictionaryService) Naming.lookup("rmi://localhost:1099/RMIdictionary");
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Step 3) Get any submitted form data. These variables are local to this method
		// and thread safe...
		String keyWord = req.getParameter("keyWordIndex");

		// Change "keyWord" to UpperCase and remove any blank space
		keyWord = keyWord.toUpperCase().replaceAll("\\s+", "");
		String taskNumber = req.getParameter("frmTaskNumber");

		// Compare the new search to storage into another variable
		// if is true the "returningDefinitons" will be set with a new value
		if (!(keyWord.equals(oldKeyWord))) {
			returningDefinitons = "Waiting for response...";
			oldKeyWord = keyWord;
		}

		// Step 4) Process the input and write out the response.
		// The following string should be extracted as a context from web.xml
		out.print("<html><head><title>Distributed Systems Assignment</title>");
		out.print("</head>");
		out.print("<body>");

		// We could use the following to track asynchronous tasks. Comment it out
		// otherwise...
		if (taskNumber == null) {
			taskNumber = new String("T" + jobNumber);

			checkProcessed = false;

			Words requestDefinition = new Words(keyWord, taskNumber);

			// Add job to in-queue
			inQueue.add(requestDefinition);

			// Start the Thread
			Runnable work = new ServiceQueue(inQueue, outQueue, service);
			executor.execute(work);

			jobNumber++;
		} else {

			if (outQueue.containsKey(taskNumber)) {

				// get the Resultator object from outMap based on tasknumber
				Validator outQItem = outQueue.get(taskNumber);

				// System.out.println("\nChecking Status of Task No:" + taskNumber);

				// Check out-queue for finished job with the given taskNumber
				checkProcessed = outQItem.isProcessed();

				// Check to see if the Resultator Item is Processed
				if (checkProcessed == true) {
					// Remove the processed item from Map by taskNumber
					outQueue.remove(taskNumber);

					// Get the Definitons of the Current Task
					returningDefinitons = outQItem.getResult();

					// System.out.println("\nTask " + taskNumber + " Processed");
					// System.out.println("String " + keyWord + " - " + returningDefinitons);
				}
			}
		}

		out.print("<html><head><title>Distributed Systems Assignment</title>");
		out.print("</head>");
		out.print("<body>");

		// Output some headings at the top of the generated page
		out.print("<H1>Dictionary Service</H1>");
		out.print("Task N. " + taskNumber);

		// Print the keyWord
		out.print("<br><H3>Query Word : " + keyWord + "</H3>");

		// Button to return to index.jsp
		out.print("<button onClick=\"window.location='index.jsp'\"><b>Make another query</b></button><br>");

		// Print the defition
		out.print("<br><font face=\"verdana\">" + returningDefinitons + "</font>");

		// We can also dynamically write out a form using hidden form fields. The form
		// itself is not
		// visible in the browser, but the JavaScript below can see it.
		out.print("<form name=\"frmRequestDetails\">");
		out.print("<input name=\"keyWordIndex\" type=\"hidden\" value=\"" + keyWord + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("</form>");
		out.print("</body>");
		out.print("</html>");

		// refresh the page, set the time
		// JavaScript to periodically poll the server for updates (this is ideal for an
		// asynchronous operation)
		out.print("<script>");
		out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 5000);"); // Refresh every 5 seconds
		out.print("</script>");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}