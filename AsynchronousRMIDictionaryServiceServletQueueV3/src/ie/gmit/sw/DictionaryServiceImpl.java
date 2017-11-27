package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;




public class DictionaryServiceImpl extends UnicastRemoteObject implements DictionaryService{
	private static final long serialVersionUID = 1L;
	private Dictionary wordDetail;
	private boolean processed;
	
	public DictionaryServiceImpl()throws RemoteException{
		super();
    }
	
	
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
	

    public Resultator compare(String s) throws RemoteException {
    	//
    	Resultator r = new ResultatorIMPL();
    	
    	
    	r.setResult("Imprime a Definicao");
    	
    	
    	r.setProcessed();
        return r;
    }
}
