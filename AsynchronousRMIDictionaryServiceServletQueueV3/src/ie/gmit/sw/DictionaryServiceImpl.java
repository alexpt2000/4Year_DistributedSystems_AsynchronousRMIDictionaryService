package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DictionaryServiceImpl extends UnicastRemoteObject implements DictionaryService {
	private static final long serialVersionUID = 1L;
	private Dictionary wordDetail;

	public DictionaryServiceImpl() throws RemoteException {
		super();
	}

	protected DictionaryServiceImpl(Dictionary list) throws RemoteException {
		super();
		this.wordDetail = list;
	}

	public Validator findDefinition(String keyWord) throws RemoteException {

		String sendToPage;

		Validator resultDefinition = new ValidatorImp();
		ArrayList<String> wordDetailReturn = new ArrayList<String>();

		wordDetailReturn = wordDetail.getDictionary().get(keyWord);

		if (wordDetailReturn == null) {

			sendToPage = "The word <b>" + keyWord + "</b>, not found in dictionary.";

		} else {
			sendToPage = "<h3>" + keyWord + "</h3>";
			for (String string : wordDetailReturn) {
				sendToPage += string + "<br>";
			}
		}

		resultDefinition.setResult(sendToPage);

		resultDefinition.setProcessed();
		return resultDefinition;
	}
}
