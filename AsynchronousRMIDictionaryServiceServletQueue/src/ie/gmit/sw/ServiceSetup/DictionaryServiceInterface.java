package ie.gmit.sw.ServiceSetup;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface DictionaryServiceInterface extends Remote {
	
	public ArrayList<String> findDictionary(String searchKey) throws RemoteException;
	


}