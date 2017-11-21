package ie.gmit.sw.servlet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.RequestDispatcher;

import ie.gmit.sw.ServiceSetup.DictionaryServiceInterface;

/**
 * Servlet implementation class DictionaryServlet
 */
@WebServlet("/dictionaryDefinitions")
public class DictionaryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static DictionaryServiceInterface look_up;

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

		System.out.println("Stating Server.....");

		try {
			look_up = (DictionaryServiceInterface) Naming.lookup("//localhost/RMIdictionary");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

		String keyWord = request.getParameter("keyWord");

		keyWord = keyWord.replaceAll("\\s+","");
		
		System.out.println(keyWord.toUpperCase());

		ArrayList<String> responseDefinition = look_up.findDictionary(keyWord.toUpperCase());

		String sendToPage = "";

		if (responseDefinition == null) {

			sendToPage = "Word not found in dictionary.";

		} else {

			for (String string : responseDefinition) {
				// System.out.print(string);
				sendToPage += "<br>" + string;
			}

		}

		request.setAttribute("definitionList", sendToPage);
		request.getRequestDispatcher("response.jsp").forward(request, response);

	}

}
