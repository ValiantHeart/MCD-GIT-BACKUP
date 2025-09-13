//Necessary Imports:
import java.util.Scanner; //Scanner class.
import java.io.*; //File class and file accessories.
/* import Project3.StringComparison; //StringComparison from local package.
import Project3.Document; //Document from local package.  */

public class SpellChecker
{
	/*A class to do spell checking. This version only marks misspelled words with
	asterisks as in **mispeling**.  It serves as a parent class for other spell
	checkers to inherit functionality to add features by overriding methods. */
	
	//Instance Variables:
	protected String[] dictWords; // Array of words considered correct by SpellChecker
	protected boolean ignoreCase; // If true, ignore case when checking the spelling of words.

	public SpellChecker(String dictFilename, boolean ignoreCase) //Constructor Type 1 (dictionary filename String and ignoreCase flag provided) (Complete)
	{
		this.ignoreCase = ignoreCase; //assigns the boolean flag to the class variable. 
		this.dictWords = readAllLines(dictFilename); //assigns the string array of contents from the file with the filename provided
	}

	public static String[] readAllLines(String filename) //reads all the words in a file with the name provided. (Complete)
	{
		try
		{
			int size = 0; // var size initialized at 0.
			Scanner s = new Scanner(new File(filename)); //creates and attaches a new Scanner s to the File with the name provided.
			//pass 1: gets the size for the array:
			while (s.hasNextLine()) //while there are lines ahead in the file:
			{
				size++; //ups the counter.
				s.nextLine(); //goes to the next line.
			} 
			s.close(); //resets the scanners cursor location to the beginning of the file.
			String[] contents = new String[size]; //creates a new array of the size of the number of lines in the file.

			Scanner c = new Scanner(new File(filename)); //creates and attaches a new Scanner c to the File with the name provided.
			//pass 2: popualtes the array with the lines of the file:
			int i = 0; // index value intialized at 0 (first index value of any array).
			while (c.hasNextLine()) //while there are lines ahead in the file:
			{
				contents[i] = c.nextLine(); // the next line's contents gets assigned to the slot of the associated index value.
				i++; // ups the value of the index by 1.
			}
			c.close(); //closes the scanner.
			return contents; //returns the constructed array of words in the file.
		}
		catch (Exception e) //catches any exception as e. Then does the following:
		{
			String[] err_array = new String[0]; //creates a new String array of length 0 called err_array.
			return err_array; //returns err_array.
		}
	}
	
	public int dictSize() //return the number of words in the dictionary. (Complete)
	{
		return dictWords.length; //returns the size of the array dictWords.
	}

	public boolean isCorrect(String word) //checks if the word is correct. Returns true if correct, false if not. (Complete)
	{
		if (this.ignoreCase == true) //if the ignoreCase flag is true:
		{
			for (int i = 0; i < dictWords.length;i++) //looks through the contents of the dictWords array:
			{
				if (dictWords[i].equalsIgnoreCase(word)) //if it finds a match either of itself, all of itself lower case, all of itself capitalized (whichever is found first):
				{
					return true; //returns true.
				}
			}
			return false; //returns false if no match found
		}
		else //if ignoreCase flag is not true:
		{
			for (int i = 0; i < dictWords.length; i++) //looks through the contents of the dictWords array:
			{
				if (dictWords[i].equals(word)) //if match found:
				{
					return true; //returns true.
				}
			}
			return false; //returns false if no match found.
		}
	}
	
	public String correctWord(String word) //highlights the words that are feed into it with asterisks. (Complete)
	{
		return "**" + word + "**";
	}

 	public void correctDocument(Document doc) //corrects the words in the Document type provided. 
	{
		while (doc.hasNextWord()) //so long as there are words ahead of the marker:
		{
			String word = doc.nextWord();
			if (isCorrect(word) == false)
			{
				doc.replaceLastWord(correctWord(word)); //replace the last word with the corrected version of that word.
			}
		}
	}
	// From the beginning of the document, apply corrections to all words in the
	// document. Each misspelled word will be marked with asterisks according to
	// the correctWord() method.  Methods of Document such as nextWord(),
	// hasNextWord(), and replaceLastWord(w) are used to modify the provided
	// document.
}