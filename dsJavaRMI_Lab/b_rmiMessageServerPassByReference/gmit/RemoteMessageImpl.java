package gmit;

/* RemoteMessageImpl is an implementation of the RemoteMessage interface, i.e it implements
 * the remote method:   public String message() throws RemoteException;
 *
 * This class also implements the java.rmi.server.Unreferenced interface. This means that our 
 * unreferenced() method will be called when no other references exist to our remote object, i.e.
 * when the DCG reference counter reaches 0. You can think of the unreferenced method as the RMI
 * equivalent of the finalize() method.
 *
 * Notice that we have an instance variable of type Message. Any calls to the remote method
 * message() are delegated to this object variable.
 */

import java.rmi.*;
import java.rmi.server.*;

public class RemoteMessageImpl extends UnicastRemoteObject implements RemoteMessage, Unreferenced{
	private static final long serialVersionUID = 1L;
	private Message message;
	
	public RemoteMessageImpl(String message) throws RemoteException{
		this.message = new Message(message);
	}
	
	public String message() throws RemoteException{
		return message.message();
	}
	
	public void finalize() throws Throwable{
      System.out.println( "RemoteMessageImpl: finalize() called.");
    }

    public void unreferenced(){
      System.out.println( "RemoteMessageImpl: unreferenced() called.");
    }
}
