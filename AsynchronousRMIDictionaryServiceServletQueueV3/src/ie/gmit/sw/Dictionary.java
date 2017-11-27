package ie.gmit.sw;

import java.io.Serializable;
import java.util.*;

public class Dictionary implements Serializable {

	private static final long serialVersionUID = 1L;

	// dictionary will receive Words and definitions HashMap with ArrayList
	private Map<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();

	private String keyWord;

	public Dictionary() {

	}

	public Dictionary(String keyWord) {
		this.keyWord = keyWord;
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

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

}
