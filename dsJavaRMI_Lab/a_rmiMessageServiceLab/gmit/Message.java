package gmit;

/* Message is a plain Java class that happens to also implement the 
 * Serializable interface. This is a marker interface (one that does
 * note require any methods be implemented) that tells the JVM that
 * the non-transient instance variable can be written out to any
 * type of OutputStream. Note the use of the constant serialVersionUID.
 * This class field is used to check during unmarshalling (deserialization)
 * that the receivers version of the class is the same as the senders.
 *
 * In our example, a Message object is returned by the remote method and  
 * thus, must be serializable. This is an example of (remote) pass by value.
 */

import java.io.*;
public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public Message(String message){
		this.msg = message;
	}
	
	public String message(){
		return msg;
	}
}
