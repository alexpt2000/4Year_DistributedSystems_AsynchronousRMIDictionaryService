package ie.gmit.sw.ServiceSetup;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DictionaryServiceInterface extends Remote{
	public Book findBook(Book b) throws RemoteException;
	public List<Book> allBooks() throws RemoteException;
}