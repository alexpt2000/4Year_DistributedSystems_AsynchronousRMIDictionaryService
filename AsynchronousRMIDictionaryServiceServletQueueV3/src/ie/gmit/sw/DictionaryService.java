package ie.gmit.sw;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DictionaryService extends Remote {
	public Validator findDefinition(String keyWord) throws RemoteException;
}
