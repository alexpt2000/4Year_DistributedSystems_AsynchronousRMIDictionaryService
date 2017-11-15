package gmit;

/* MessageService represents the Remote Interface. An interface is an abstraction
 * that defines what an implementing class must do, but not how it will do it.  
 * Interfaces are declarative - they may contain constants and method signatures,
 * but have no implementation code. Interfaces are also a type. Any class that
 * implements an interface may be cast to the interface type.
 *
 * Our remote interface exposes the public service methods that may be invoked by
 * a remote object. All remote methods must throw a RemoteException. In RMI, a class
 * that implements a remote interface is called a Remote Object.
 *
 * For this lab, we have altered the return type of the getMessage() method. Instead
 * of returning a serializable Message type, we now return an instance of a 
 * RemoteMessage type, i.e. we return a stub for the class RemoteMethodImp. Thus,
 * the return type of our remote method is itself a reference to a remote object.
 * You should note that the MessageServer and MessageServiceImpl classes do not require
 * specialised coding to implement this behavior.
 */

import java.rmi.*;

public interface MessageService extends Remote{
	public RemoteMessage getMessage() throws RemoteException;
}
