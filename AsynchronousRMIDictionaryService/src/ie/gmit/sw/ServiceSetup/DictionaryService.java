package ie.gmit.sw.ServiceSetup;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DictionaryService extends Remote{
	
	public byte[] getFile(String filename) throws RemoteException;
	
	public ArrayList<String> getFileNames() throws RemoteException;
	
	public void uploadFile(String fileName, byte[] bytes) throws RemoteException;
}
