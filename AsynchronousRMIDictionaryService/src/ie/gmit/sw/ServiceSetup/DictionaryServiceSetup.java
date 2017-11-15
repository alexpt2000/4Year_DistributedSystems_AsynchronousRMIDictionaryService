package ie.gmit.sw.ServiceSetup;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class DictionaryServiceSetup {

	public static void main(String[] args) throws Exception {

		//Create an instance of a FileServiceImpl. As FileServiceImpl implements the FileService
		//interface, it can be referred to as a FileService type.
		DictionaryService fs = new DictionaryServiceImpl();

		//Start the RMI registry on port 1099
		LocateRegistry.createRegistry(1099);

		//Bind our remote object to the registry with the human-readable name "fileService"
		Naming.rebind("dictionaryService", fs);

		//Print a message to standard output
		System.out.println("Server ready.");

	}
}
