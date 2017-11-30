package ie.gmit.sw.Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

import javax.swing.JOptionPane;

import ie.gmit.sw.ServiceSetup.Book;
import ie.gmit.sw.ServiceSetup.Dictionary;
import ie.gmit.sw.ServiceSetup.DictionaryServiceInterface;

public class Client {

	private static DictionaryServiceInterface look_up;

	// object console to get imput
	static Scanner console = new Scanner(System.in);

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

		look_up = (DictionaryServiceInterface) Naming.lookup("//localhost/RMIdictionary");

		System.out.print("====================================");
		System.out.print("\nEnter the word ----> ");
		String searchKey = console.next().toUpperCase();

		ArrayList<String> response = look_up.findDictionary(searchKey);

		// Dictionary response = look_up.findDictionary(new Dictionary(searchKey));
		// System.out.println(response.getDictionary().get(searchKey));
		// Print the definition from dictionary by search input
		// System.out.println(wordDetail.getDictionary().get(searchKey));

		for (String string : response) {
			System.out.print(string);
		}

	}
}
