package ie.gmit.sw.ServiceSetup;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.function.Predicate;

public class DictionaryServiceImpl extends UnicastRemoteObject implements DictionaryServiceInterface{
	
	private static final long serialVersionUID = 1L;
	private List<Book> bookList;
	
	protected DictionaryServiceImpl(List<Book> list) throws RemoteException {
		super();
		this.bookList = list;
	}
	
	
	//The client sends a Book object with the isbn information on it (note: it could be a string with the isbn too)
	//With this method the server searches in the List bookList for any book that has that isbn and returns the whole object
	@Override
	public Book findBook(Book book) throws RemoteException {
		Predicate<Book> predicate = x-> x.getIsbn().equals(book.getIsbn());
		return bookList.stream().filter(predicate).findFirst().get();
		
	}
	
	@Override
	public List<Book> allBooks() throws RemoteException {
		return bookList;
	}

}
