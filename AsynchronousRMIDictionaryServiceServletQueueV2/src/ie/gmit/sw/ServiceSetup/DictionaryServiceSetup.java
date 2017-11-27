package ie.gmit.sw.ServiceSetup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class DictionaryServiceSetup {

	static LoadDictionary loadDictionary;

	public static void main(String[] args) {
		try {

			// Start the RMI regstry on port 1099
			LocateRegistry.createRegistry(1099);

			Naming.rebind("//localhost/RMIdictionary",
					new DictionaryServiceImpl(loadDictionary.initializeDictionary()));
			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.getMessage());
		}
	}
}