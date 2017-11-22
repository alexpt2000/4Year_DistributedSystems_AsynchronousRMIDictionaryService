package ie.gmit.sw.TreadQueue;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Definitions extends Remote {
    public String getResult() throws RemoteException;
    public void setResult(String result) throws RemoteException;
    public boolean isProcessed() throws RemoteException;
    public void setProcessed() throws RemoteException;
}
