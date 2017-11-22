package ie.gmit.sw.ServiceSetup;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class DictionaryServiceImpl extends UnicastRemoteObject implements DictionaryServiceInterface {

	private static final long serialVersionUID = 1L;
	private Dictionary wordDetail;
	private boolean processed;

	protected DictionaryServiceImpl(Dictionary list) throws RemoteException {
		super();
		this.wordDetail = list;
	}

	@Override
	public ArrayList<String> findDictionary(String keyWord) {

		ArrayList<String> wordDetailReturn = new ArrayList<String>();

		System.out.println("Client Request the KeyWord -> " + keyWord);

		wordDetailReturn = wordDetail.getDictionary().get(keyWord);

		return wordDetailReturn;
	}

	public boolean isProcessed() throws RemoteException {
		return processed = true;
	}

}
