package ie.gmit.sw.ServiceSetup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoadDictionary {
	
	
	
	static Dictionary initializeDictionary() throws IOException {

		// Variables
		String inputLine = null;
		String oldWordKey = "";

		// Reference name for the file dictionary
		BufferedReader reader = new BufferedReader(new FileReader(new File("WebstersUnabridgedDictionary.txt")));

		// Instantiate class WordDetail
		Dictionary wordDetail = new Dictionary();

		// load the file dictionary into a HashMap an Arralist
		while ((inputLine = reader.readLine()) != null) {
			String[] wordsKey = inputLine.split("\\s+");

			// Ignore empty lines.
			if (inputLine.equals("")) {
				continue;
			}

			if (wordsKey.length == 1) {
				oldWordKey = wordsKey[0];
			}
			// if not, is just definition
			else {
				wordDetail.addDicWord(oldWordKey.toUpperCase(), inputLine);

			}
		}

		return wordDetail;
	}
	
	
	
	
	
//	static Dictionary initializeDictionary() throws IOException {
//
//		// Variables
//		String inputLine = null;
//		String quotes;
//		String oldWordKey = "";
//
//		// Reference name for the file dictionary
//		BufferedReader reader = new BufferedReader(new FileReader(new File("dictionary.csv")));
//
//		// Instantiate class WordDetail
//		Dictionary wordDetail = new Dictionary();
//
//		// load the file dictionary into a HashMap an Arralist
//		while ((inputLine = reader.readLine()) != null) {
//			String[] wordsKey = inputLine.split(",", 2);
//
//			// Ignore empty lines.
//			if (inputLine.equals("")) {
//				continue;
//			}
//
//			// Take first character into variable quotes
//			quotes = wordsKey[0].substring(0, 1);
//
//			// replace quotes by blank space
//			wordsKey[0] = wordsKey[0].replace("\"", "");
//
//			// if first character in this line is quotes, this line have key and
//			// definition
//			if ("\"".equals(quotes)) {
//				oldWordKey = wordsKey[0];
//				wordDetail.addDicWord(oldWordKey.toUpperCase(), inputLine);
//
//				// System.out.println(inputLine);
//			}
//			// if not, is just definition
//			else {
//				wordDetail.addDicWord(oldWordKey.toUpperCase(), inputLine);
//			}
//		}
//
//		return wordDetail;
//	}
	

}
