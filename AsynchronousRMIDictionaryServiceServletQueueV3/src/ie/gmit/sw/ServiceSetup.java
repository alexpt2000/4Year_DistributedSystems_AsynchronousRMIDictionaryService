package ie.gmit.sw;

//import ie.gmit.sw;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;



public class ServiceSetup {

	static LoadDictionary loadDictionary;
	
    public static void main(String[] args){

    	
		try {

			// Start the RMI regstry on port 1099
			LocateRegistry.createRegistry(1099);

			Naming.rebind("//localhost/RMIdictionary", new DictionaryServiceImpl(loadDictionary.initializeDictionary()));
			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.getMessage());
		}
    	
    	
    	
    	
//        DictionaryService mss = new DictionaryServiceImpl();
//        //Start the RMI registry on port 1099
//        LocateRegistry.createRegistry(1099);
//     
//        Naming.rebind("AsynchronousRMIDictionaryService", mss);
//       
//        System.out.println("Asynchronous RMI Dictionary Service on port 1099.");
    }
}