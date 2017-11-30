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

	// Find definition into ArrayList
	public Validator findDefinition(String keyWord) throws RemoteException {
		String sendToPage;

		// Control of Queues
		Validator resultDefinition = new ValidatorImp();

		ArrayList<String> wordDetailReturn = new ArrayList<String>();

		// find the word
		wordDetailReturn = wordDetail.getDictionary().get(keyWord);

		// Verify if the word exist, if yes return the definition
		if (wordDetailReturn == null) {
			sendToPage = "The word <b>" + keyWord + "</b>, not found in dictionary.";
		} else {
			// Concat all values from ArrayList into one single String
			sendToPage = "<h3>" + keyWord + "</h3>";
			for (String string : wordDetailReturn) {
				sendToPage += string + "<br>";
			}
		}

		// Set the String in result
		resultDefinition.setResult(sendToPage);

		// Set the definition as Processed into Queue
		resultDefinition.setProcessed();

		return resultDefinition;
	}
}
