package gmit;

	/**
	  * @desc WordDetail - extends Dictionary

	*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class WordDetail extends Dictionary {

	//dictionary will receive Words and definitions HashMap with ArrayList
	private Map<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();
	
	//Book will receive Words and pages HashMap with TreeSet
	private Map<String, TreeSet<Integer>> book = new HashMap<String, TreeSet<Integer>>();
	
	//ignoreWord will receive just Words TreeSet
	private TreeSet<String> ignoreWord = new TreeSet<String>();

	public WordDetail() {
		
	}

	// Add Methods
	
	/**
	  * @desc addBookWord - for each time the method is call the will verify is the word exist
	  * if not, the method will insert the word and create a new object for page number represents that word,
	  * if exist will just add the page number
	  * 
	  * @param String bookWord, Integer pageBook
	*/
	public void addBookWord(String bookWord, Integer pageBook) {
		if (book.get(bookWord) == null) {
			book.put(bookWord, new TreeSet<Integer>());
		}
		book.get(bookWord).add(pageBook);
	}

	
	/**
	  * @desc addDicWord - for each time the method is call the will verify is the word exist
	  * if not, the method will insert the word and create a new object for word definition  that word,
	  * if exist will just add the word definition
	  * 
	  * @param String word, String definitionWord
	*/
	public void addDicWord(String word, String definitionWord) {
		if (dictionary.get(word) == null) {
			dictionary.put(word, new ArrayList<String>());
		}
		
		dictionary.get(word).add("\n" + definitionWord);
	}

	
	
	/**
	  * @desc addIgnoreWord - for each time the method is call the will add word to the list
	  * 
	  * @param String ignoreWords
	*/
	public void addIgnoreWord(String ignoreWords) {
		ignoreWord.add(ignoreWords);
	}

	
	
	// Gets
	
	/**
	 * @return Word and definition from dictionary
	 * 
	 * */
	public Map<String, ArrayList<String>> getDictionary() {
		return dictionary;
	}

	
	/**
	 * @return Word and pages number from book
	 * 
	 * */
	public Map<String, TreeSet<Integer>> getBook() {
		return book;
	}
	

	
	/**
	 * @return Word from ignoreWord
	 * 
	 * */
	public TreeSet<String> getIgnoreWord() {
		return ignoreWord;
	}
}
