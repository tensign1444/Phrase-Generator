package comprehensive;

import java.io.File;

/**
 * 
 * @author Tanner Ensign & Erick Callisaya
 * This class uses the RandomPhraseMaker java class to create a random phrase from a file
 * which the user inputs. The user also inputs however many phrases they would like.
 *
 */
public class RandomPhraseGenerator {

	public static void main(String[] args) {
		
		RandomPhraseMaker maker = new RandomPhraseMaker(new File(args[0]));

		for(int i = 0; i <  Integer.parseInt(args[1]); i++) {
			maker.generateRandom();
		}
		
	}


}
