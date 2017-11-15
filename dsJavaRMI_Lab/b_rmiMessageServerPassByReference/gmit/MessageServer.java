package gmit;

/* MessageServer is responsible for creating an instance of the remote object (MessageServiceImpl)
 * and binding it to a naming server (the RMI Registry) with a human-readable name. The RMI registry
 * can be started from the command line with the "rmiregistry" command, or programmatically using:
 *			LocateRegistry.createRegistry(1099);
 * Where 1099 is the port number that the registry is listening on for incoming client requests.
 *
 * Note: When a client asks the RMI registry for an object, the registry returns an instance of the
 * remote interface - a stub (technically a remote proxy). Therefore, before starting the registry, 
 * make sure to run the rmic compiler over the remote object to create the stub:
 *				rmic gmit.MessageServiceImpl
 *
 */


import java.rmi.*;
import java.rmi.registry.*;

public class MessageServer {
	public static void main(String[] args) throws Exception{
		//A string, representing the message we want to associate with our Message object
		String myMessageStr = "Hello from 127.0.0.1 part b";
		
		//Create an instance of the class Message and pass the string as an argument to its constructor.
		RemoteMessage myMessage = new RemoteMessageImpl(myMessageStr);
		
		//Create an instance of a MessageService. As MessageServiceImpl implements the MessageService
		//interface, it can be referred to as a MessageService type.
		MessageService ms = new MessageServiceImpl(myMessage);
		
		//Start the RMI regstry on port 1099
		LocateRegistry.createRegistry(1099);
		
		//Bind our remote object to the registry with the human-readable name "howdayService"
		Naming.rebind("howdayService", ms);
		
		//Print a nice message to standard output
		System.out.println("Server ready.");
	}
}
