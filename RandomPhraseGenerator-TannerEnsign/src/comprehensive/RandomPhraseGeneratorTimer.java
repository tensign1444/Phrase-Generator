  package comprehensive;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;




/**
 * This class test the runtime of running Tanner Ensign & Erick Callisaya HashTable class
 * and java's Hashtable class.
 * @author Tanner Ensign & Erick Callisaya
 *
 */
public class RandomPhraseGeneratorTimer {

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		final int NSTART = 100000;
		final int NSTOP = 1000000;
		final int NINCR = 50000;
		System.out.println("BST\nN\tTime");
		
		
		for(int N = NSTART; N <= NSTOP; N += NINCR) {
			System.out.print(N + "\t");


			System.out.println(getTime(N));
		}
		
	
	}


	/**
	 * This method loops through and get's the run time in nano
	 * seconds of each method.
	 * @param N
	 * @return nanoTime
	 */
	@SuppressWarnings("unused")
	private static long getTime(int N) {
		
			RandomPhraseMaker slow = new RandomPhraseMaker(new File("src/comprehensive/assignment_extension_request.g"));
			
			RandomPhraseMaker fast = new RandomPhraseMaker(new File("src/comprehensive/super_simple.g"));
			
			Random rand = new Random();
			
			final int TIMES_TO_LOOP = 1000;
			
			long startTime = System.nanoTime();
			
			while(System.nanoTime() - startTime < 1000000000)
				;

			startTime = System.nanoTime();
			for(int i = 0; i < TIMES_TO_LOOP; i++) {
				slow.generateRandom();
				//fast.generateRandom();
			}
			
			long midTime = System.nanoTime();
			
			//Time the "overhead"
			for(int i = 0; i < TIMES_TO_LOOP; i++) {
				
			}
			
			long endTime = System.nanoTime();
			
			return ((midTime - startTime) - (endTime - midTime)) / TIMES_TO_LOOP;
	}
	


}
