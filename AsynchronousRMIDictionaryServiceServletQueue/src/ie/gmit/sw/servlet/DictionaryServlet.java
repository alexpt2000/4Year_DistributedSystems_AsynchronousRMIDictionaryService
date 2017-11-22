package ie.gmit.sw.servlet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.RequestDispatcher;


import ie.gmit.sw.ServiceSetup.DictionaryServiceInterface;
import ie.gmit.sw.TreadQueue.Definitions;
import ie.gmit.sw.TreadQueue.Reference;

/**
 * Servlet implementation class DictionaryServlet
 */
@WebServlet("/dictionaryDefinitions")
public class DictionaryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static DictionaryServiceInterface look_up;
	
	private static long jobNumber = 0;
	private final int POOL_SIZE = 6;
	
	private static Map<String, Definitions> outQueue; 
	private static BlockingQueue<Reference> inQueue;
	private static ExecutorService executor ;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DictionaryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		System.out.println("Starting the client.....");

		try {
			look_up = (DictionaryServiceInterface) Naming.lookup("//localhost/RMIdictionary");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		outQueue = new HashMap<String, Definitions>();
		inQueue = new LinkedBlockingQueue<Reference>();
		executor = Executors.newFixedThreadPool(POOL_SIZE);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String taskNumber = request.getParameter("frmTaskNumber");
		
		
		String keyWord = request.getParameter("keyWord");

		keyWord = keyWord.toUpperCase().replaceAll("\\s+", "");

		System.out.println(keyWord);
		
		
		
		
		
		
		if (taskNumber == null){
			taskNumber = new String("T" + jobNumber);
			


			Reference r = new Reference(jobNumber, keyWord);
			inQueue.add(r);
			
			
			Runnable work = new Worker(inQueue, outQueue, service);
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
					//Get the Distance of the Current Task
					returningDistance = outQItem.getResult();

					System.out.println("\nTask " + taskNumber + " Processed");
					System.out.println("String (" + str1 + ") and String (" + str2 + ") Distance = " + returningDistance);
				}
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		ArrayList<String> responseDefinition = look_up.findDictionary(keyWord);

		String sendToPage = "";

		if (responseDefinition == null) {

			sendToPage = "The word <b>" + keyWord + "</b>, not found in dictionary.";

		} else {
			sendToPage = "<h3>" + keyWord + "</h3>";
			for (String string : responseDefinition) {
				// System.out.print(string);
				sendToPage += string + "<br>";
			}
		}

		request.setAttribute("definitionList", sendToPage);
		request.getRequestDispatcher("response.jsp").forward(request, response);
	}

}
