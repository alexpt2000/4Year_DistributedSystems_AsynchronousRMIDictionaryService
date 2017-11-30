package ie.gmit.sw;

import java.rmi.Remote;
import java.rmi.RemoteException;

// Find definition base on keyWord
public interface DictionaryService extends Remote {
	public Validator findDefinition(String keyWord) throws RemoteException;
}
