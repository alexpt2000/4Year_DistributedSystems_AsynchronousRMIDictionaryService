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
 */

import java.rmi.*;

public interface MessageService extends Remote{
	public Message getMessage() throws RemoteException;
}
