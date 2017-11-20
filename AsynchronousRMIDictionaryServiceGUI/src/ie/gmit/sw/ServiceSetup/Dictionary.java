package ie.gmit.sw.ServiceSetup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dictionary implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	// dictionary will receive Words and definitions HashMap with ArrayList
	private Map<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();

	public Dictionary() {
		super();
	}

	public Dictionary(Map<String, ArrayList<String>> dictionary) {
		super();
		this.dictionary = dictionary;
	}


	public void addDicWord(String word, String definitionWord) {
		if (dictionary.get(word) == null) {
			dictionary.put(word, new ArrayList<String>());
		}

		dictionary.get(word).add("\n" + definitionWord);
	}

	public Map<String, ArrayList<String>> getDictionary() {
		return dictionary;
	}

	public void setDictionary(Map<String, ArrayList<String>> dictionary) {
		this.dictionary = dictionary;
	}

}
