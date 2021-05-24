package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class RandomPhraseGenerator2 {

	
	private static HashMap<String, List<String>> table;
	private static StringBuilder str;
	
	public RandomPhraseGenerator2() {
		str = new StringBuilder();
		table = new HashMap<String, List<String>>();
		
	}
	public static void main(String[] args) {
		
		readFromFile(new File(args[0]));

		for(int i = 0; i <  Integer.parseInt(args[1]); i++) {
			generateRandom();
		}
		
	}
	
	public static void readFromFile(File file) {

		List<String> trackerList = new ArrayList<String>();
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
	}
	
	public static void generateRandom() {
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
		
		System.out.println(str);

	}


}
