package ie.gmit.sw.ServiceSetup;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;



public class DictionaryServiceSetup {
	
	
	protected DictionaryServiceSetup() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	private static List<Book> initializeList(){
		List<Book> list = new ArrayList<>();
		list.add(new Book("Head First Java, 2nd Edition", "978-0596009205", 31.41));
		list.add(new Book("Java In A Nutshell", "978-0596007737", 10.90));
		list.add(new Book("Java: The Complete Reference", "978-0071808552", 40.18));
		list.add(new Book("Head First Servlets and JSP", "978-0596516680", 35.41));
		list.add(new Book("Java Puzzlers: Traps, Pitfalls, and Corner Cases", "978-0321336781", 39.99));
		return list;
	}
	
	public static void main(String[] args){
        try {
        	
        	//Start the RMI regstry on port 1099
    		LocateRegistry.createRegistry(1099);
    		
            Naming.rebind("//localhost/RMIdictionary", new DictionaryServiceImpl(initializeList()));
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
        }
    }
}