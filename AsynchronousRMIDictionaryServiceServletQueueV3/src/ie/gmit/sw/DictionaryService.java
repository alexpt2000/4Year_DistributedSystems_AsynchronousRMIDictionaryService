package ie.gmit.sw;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DictionaryService extends Remote {
	//public ArrayList<String> findDictionary(String searchKey) throws RemoteException;
    public Resultator findDefinition(String keyWord) throws RemoteException;
}
