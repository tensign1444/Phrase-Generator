package comprehensive;


import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Random;
import java.util.Scanner;


/**
 * 
 * @author Erick Callisaya & Tanner Ensign
 * This class creates scans through a file which contains non-terminals
 * and terminals to create a random sentence. They are stored in a hashmap,
 * then a random phrase is created.
 *
 */
public class RandomPhraseMaker {

	//the hashmap which holds the non-terminals definitions
	private HashMap<String, List<String>> table;
	//the final phrase
	private StringBuilder str;
	
	/**
	 * Constructor
	 * @param fileName, file to be scanned
	 */
	public RandomPhraseMaker(File fileName) {
		this.str = new StringBuilder();
		this.table = readFromFile(fileName);

	}
	

	/**
	 * Returns a HashMap of the words contained in the specified file. 
	 * 
	 * @param file - the File to be read
	 * @return HashMap, with non-terminal keys and terminal list values.
	 */
	public HashMap<String, List<String>> readFromFile(File file) {
		//List of strings that holds terminals
		List<String> trackerList = new ArrayList<String>();
		//HashMap that holds terminals to keys(Non-Terminals)
		HashMap<String, List<String>> table = new HashMap<>();
		try {
			/*
			 * Java's Scanner class is a simple lexer for Strings and primitive types (see
			 * the Java API, if you are unfamiliar).
			 */
			Scanner fileInput = new Scanner(file);
			int count = 0;
			 String typeOfStorage = "";
			 String tracker = fileInput.nextLine();
             String section = "";
			while (fileInput.hasNext()) {
				if(typeOfStorage.equals("<start>") && !tracker.equals("}")) {
					if(count == 0)
						str.append(tracker);
					trackerList.add(tracker);
					count++;
				}
				else if (section.equals("{") && !tracker.equals("}") && !tracker.equals(typeOfStorage)) {
					 trackerList.add(tracker);
                 } else if (tracker.equals("{")) {
                     section = tracker;
                     typeOfStorage = fileInput.nextLine();
                 } else if (tracker.equals("}")) {
                	 table.putIfAbsent(typeOfStorage, trackerList);
                	 section = "";
                	 typeOfStorage = "";
                	 trackerList = new ArrayList<String>();
                 }
                 tracker = fileInput.nextLine();
             }
			table.putIfAbsent(typeOfStorage, trackerList);
			fileInput.close();
		}
		catch(FileNotFoundException e) {
			System.err.println("File " + file + " cannot be found.");
			}
		return table;
	}
	
	
	/**
	 * This method generates the random phrase by looping through each character in str.
	 * It then replaces the non-terminals with terminals.
	 */
	public void generateRandom() {
		Random rand = new Random();
		int counter = 0;
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '<') {
				counter = i;
			}
			else if(str.charAt(i) == '>') {
				if(table.containsKey(str.substring(counter, i+1))) {
					str.replace(counter, i+1, table.get(str.substring(counter, i+1)).get(rand.nextInt(table.get(str.substring(counter, i+1)).size())));
					i = 0;
				}
			}
		}
		
		//System.out.println(str);

	}
}
