package gmit;

/* The remote interface MessageService declares a single remote method:
 *
 * 		public RemoteMessage getMessage() throws RemoteException;
 *
 * As we want to simulate a pass-by-reference, the return type itself (RemoteMessage) is
 * really a reference to another server-side remote object. Notice that the remote interface 
 * declares a remote method called message(). This is so, because we will reuse the functionality
 * of the class Message by creating a remote object implementation of RemoteMessage (called
 * RemoteMessageImpl) that is composed of a Message object, i.e. it has an instance variable
 * of type Message. Calls to the remote objects message() method are delegated to the message()
 * method of the composed Message object.
 */

import java.rmi.*;
public interface RemoteMessage extends Remote{
	public String message() throws RemoteException;
}
