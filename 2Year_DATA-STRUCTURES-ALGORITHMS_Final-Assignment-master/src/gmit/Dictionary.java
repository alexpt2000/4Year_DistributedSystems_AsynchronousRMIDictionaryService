package gmit;

/** 
  * @desc allows a word index to be created from an e-book. the index should contain a list
  * of words that relate to the page numbers they appear on and the dictionary defined meaning of
  * the word:
  * @examples include countLines(String bookName) and dictionaryMethod(String bookName, String searchKey)
  * 
  * @author Alexander Souza G0037835@GMIT.IE
  * @required WordDetail.java
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

public class Dictionary {

	// object console to get imput
	static Scanner console = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		String bookName = "";
		String searchKey = "";

		int op = 0;

		// loop ref list of book available
		do {
			System.out.println("\n============== Select the Book =============");
			System.out.println("  1 - WarAndPeace-LeoTolstoy.txt");
			System.out.println("  2 - DeBelloGallico.txt");
			System.out.println("  3 - PoblachtNaHEireann.txt");

			System.out.println("\n  9 - Exit");
			System.out.println("=============================================");

			System.out.print("\nEnter book No..: ");
			op = console.nextInt();

			// System.out.println("\n--------------------------- Dictionary and
			// Definition ----------------------------------------");

			switch (op) {
			case 1:
				bookName = "WarAndPeace-LeoTolstoy.txt";
				dictionaryMethod(bookName, searchKey);
				break;

			case 2:
				bookName = "DeBelloGallico.txt";
				dictionaryMethod(bookName, searchKey);
				break;

			case 3:
				bookName = "PoblachtNaHEireann.txt";
				dictionaryMethod(bookName, searchKey);
				break;

			case 9:
				System.out.println("\nFinish...");
				System.exit(0);
				break;

			default: // default - Invalid option
				System.out.printf("Invalid entry - only 1 or 3 allowed - try again%n");
			}

		} while (!(op == 9));
	}

	/**
	 * @desc Count number lines into the book
	 * @param string
	 *            bookName - the the file to check
	 * @return int - total number lines into the book
	 */
	public static int countLines(String bookName) throws IOException {
		LineNumberReader reader = null;
		try {
			reader = new LineNumberReader(new FileReader(bookName));
			while ((reader.readLine()) != null)
				;
			return reader.getLineNumber();
		} catch (Exception ex) {
			return -1;
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	/**
	 * @desc Count take the word for each file and storage hashMap, TreeSet or
	 *       ArrayList
	 * @param string
	 *            bookName - name the file book, String searchKey - the word to
	 *            be search
	 * @return void
	 */
	static void dictionaryMethod(String bookName, String searchKey) throws IOException {

		// Instantiate class WordDetail
		WordDetail wordDetail = new WordDetail();

		// Reference name for the file dictionary
		BufferedReader reader = new BufferedReader(new FileReader(new File("dictionary.csv")));

		// Reference name for the file stopwords
		BufferedReader readerIgnoreWords = new BufferedReader(new FileReader(new File("stopwords.txt")));

		// Reference name for the file select in menu
		BufferedReader readerBook = new BufferedReader(new FileReader(new File(bookName)));

		// Variables
		String inputLine = null;
		String inputLineIgnoreWords = null;
		String inputLineBook = null;
		String oldWordKey = "";
		String quotes;

		int lineCounter = 0;
		int page = 1;
		int countIgnoreWords = 0;

		// load the file stopwords into a TreeSet
		while ((inputLineIgnoreWords = readerIgnoreWords.readLine()) != null) {
			wordDetail.addIgnoreWord(inputLineIgnoreWords);
		}

		// load the file dictionary into a HashMap an Arralist
		while ((inputLine = reader.readLine()) != null) {
			String[] wordsKey = inputLine.split(",", 2);

			// Ignore empty lines.
			if (inputLine.equals("")) {
				continue;
			}

			// Take first character into variable quotes
			quotes = wordsKey[0].substring(0, 1);

			// replace quotes by blank space
			wordsKey[0] = wordsKey[0].replace("\"", "");

			// if first character in this line is quotes, this line have key and
			// definition
			if ("\"".equals(quotes)) {
				oldWordKey = wordsKey[0];
				wordDetail.addDicWord(oldWordKey.toUpperCase(), inputLine);
			}
			// if not, is just definition
			else {
				wordDetail.addDicWord(oldWordKey.toUpperCase(), inputLine);
			}
		}

		int lines = countLines(bookName); // Count how many line are into the
											// file book
		String progessBar = "";

		// print header
		System.out.println("\nLoading the book " + bookName);
		System.out.println("|-----------------------------------------------------|");

		// load the file book into a HashMap an TreeSet
		while ((inputLineBook = readerBook.readLine()) != null) {

			lineCounter++; // Count the line

			// Count page for each 40 lines
			if (lineCounter % 40 == 0) {
				page++;
			}

			// print quotes for progress bar each 10 lines
			if (lineCounter % (lines / 10) == 0) { // the total lines was take
													// countLines(bookName)
				progessBar += "*"; // and divide by 10
				System.out.print(progessBar); // print screen the progress bar
			}

			String[] words = inputLineBook.split(" "); // takes each word
														// separate by space

			// Ignore empty lines.
			if (inputLineBook.equals("")) {
				continue;
			}

			// read into string array each word
			for (int i = 0; i < words.length; i++) {

				String word = new String();
				word = words[i];

				// Remove any commas and dots.
				word = word.replace(".", "");
				word = word.replace(",", "");
				word = word.replace("\"", "");
				word = word.replace(";", "");
				word = word.replace(" ", "");
				word = word.replace("-", "");
				word = word.replace("--", "");
				word = word.replace("?", "");
				word = word.replace("!", "");

				// verify each word and if word exist into the list ignoreword,
				// if yes, this word will skip
				if (!(wordDetail.getIgnoreWord().contains(word))) {
					wordDetail.addBookWord(word.toLowerCase(), page);
				} else {
					countIgnoreWords++; // count number word ignored
				}
			}
		}

		// Loop to get word to show screen
		do {

			System.out.print("\n\n====================================");
			System.out.print("\nEnter the word ----> ");
			searchKey = console.next().toUpperCase(); // get the word and
														// convert o
														// UPPERCASE

			System.out.println(
					"\n--------------------------- Dictionary and Definition ----------------------------------------");

			// Print the definition from dictionary by search input
			System.out.println(wordDetail.getDictionary().get(searchKey));

			// loop into HashMap Book verify search input exist
			System.out.println("\n------------------------------ Book Detals --------------------------------------");
			System.out.println("Word.: " + searchKey.toLowerCase());
			System.out.println("Book.: " + bookName);
			System.out.print("Indices Pages.: " + wordDetail.getBook().get(searchKey.toLowerCase()));
			System.out.println("\nTotal stop words.: " + countIgnoreWords);
			System.out.println("Total pages.: " + page + "   Lines.: " + lineCounter);
			System.out.println("----------------------------------------------------------------------------------");

		} while (searchKey != "0");

		reader.close();
		readerBook.close();
		readerIgnoreWords.close();
	}
}
