package gmit;

/* MessageClient is obviously a client of our remote message service. The Naming.lookup
 * is a request to a remote registry for an object with a human-readable name. The object 
 * returned is really an instance of MessageServiceImpl_Stub.class, but our client thinks
 * it is dealing with the real remote object (MessageServiceImpl_Stub also implements 
 * MessageService). The lookup() method returns a type of java.lang.Object which we need
 * to cast to the interface type. The statement:
 *			RemoteMessage message = ms.getMessage();
 * 		is the actual remote method invocation. Notice how there no special coding required
 * to deal with the remote invocation. This is an example of Local/Remote Transparency.
 *
 *
 * Note also that there is no reference to any server-side object type except for the remote
 * interfaces. The client is loosely-coupled with the remote objects. If we don't like our
 * implementation of the remote objects, we can substitute these with different classes that
 * implements MessageService and RemoteMessage without having to recompile the client. We would 
 * however, have to run the rmic compiler over the newer remote objects and figure out a way of
 * getting the generated stubs to the client (more about this later!).
 *
 */


import java.rmi.Naming;

public class MessageClient {
	public static void main(String[] args) throws Exception{
		
		//Ask the registry running on localhost and listening in port 1099 for the instannce of
		//the MessageService object that is bound to the RMI registry with the name howdayService.
		MessageService ms = (MessageService) Naming.lookup("rmi://localhost:1099/howdayService");
		
		//Make the remote method invocation. This results in the RemoteMessage object being transferred
		//to us over the network in serialized form. 
		RemoteMessage message = ms.getMessage();
		
		System.out.println("RemoteMessage Object ID: " + message);
		
		//Print out the message from the message object.
		System.out.println(message.message());
	}
}
