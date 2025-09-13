//Necessary Imports:
import java.io.*; //File class and file accessories. Also PrintWriter.
import java.util.Scanner;

public class PersonalSC extends InteractiveSC
{
// A spell checker which allows use of a personal dictionary. The
// personal dictionary is initially read from a file though the file
// may be non-existen in which case the personal dictioary is empty to
// begin with.  When checking for correctness of words, both the
// system dictionary and personal dictionary are checked. If a
// misspelled word is to be corrected, the user is interactively
// prompted as to whether the word should instead be added to the
// personal dictionary.  The class can save the personal dictionary
// back to the file from which it was read.


	protected String personalDictFilename; //Name of the file for the personal dictionary
	protected String [] personalDictWords; //Personal dictionary words

	public PersonalSC(String dictFilename, boolean ignoreCase, Scanner input, PrintWriter output, String personalDictFilename)
	{
		super(dictFilename, ignoreCase, input, output); //calls parent construction.
		this.personalDictFilename = personalDictFilename; //assigns filename to the class definition. 
		this.personalDictWords = readAllLines(personalDictFilename); //constructs the dictiaonry for the class.
	}

	public int personalDictSize() //Return the size of the personal dictionary used by this SpellChecker.
	{
		return personalDictWords.length; //returns tyhe length of the array.
	}
	
	

	@Override public boolean isCorrect(String word)
	{
		if (super.isCorrect(word) == false) //if the word is not correct:
		{
			for (int i = 0; i < personalDictWords.length;i++) //look through the words in the personal dictionary:
			{
				if (word.compareTo(personalDictWords[i]) == 0)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	// Check if the word is correct according to the same methodology as
	// the parent class.  If not, check whether the word appears in the
	// personal dictionary associated with this spell checker. Honor the
	// ignoreCase setting when checking the personal dictionary.

	@Override public String correctWord(String word)
	{
		if (isCorrect(word) == false)
		{
			String msg = "@- "+"**"+word+"**"+ "not in dictionary add it? (yes / no)";
			Scanner input = new Scanner(System.in); 
			String user_input = input.nextLine();
			if (user_input.equals("no"))
			{
				String[] personalDictWords_appended = new String[personalDictWords.length +1]; 
				for (int i = 0; i < personalDictWords.length; i++) //builds a new dictionary that is one greater in length than the first.
				{
					personalDictWords_appended[i] = personalDictWords[i];
				}
				personalDictWords_appended[personalDictWords.length + 1] = word; //appends the new word to the end of the dictionary.
				this.personalDictWords = personalDictWords_appended; //overwrites the previous var with the appended one.
				return word; //returns word
			}
			else
			{
				String correction = input.nextLine(); //asks user for correction.
				return correction; //returns the correction.
			}
		}
		else
		{
			return word;
		}
	}
	
	// If the parameter word is not in the system or personal
	// dictionary, prompt the user on whether they would like to add it
	// to the dictionary as in
	//
	// @- **tumato** not in dictionary add it? (yes / no)
	//
	// If the response is "yes" (read using the spell checkers scanner),
	// append it to the personalDictWords. You may use library methods
	// from java.util.Arrays to make the append easier.  After
	// appending, return the word as it is now considered correct.
	// 
	// If the answer on whether to add is not "yes" (e.g. "no"), prompt
	// the user for a correction in the same way that the parent class does. 

	public String getAllPersonalDictWords() 
	{
		String dictContents = "";
		for (int i = 0; i < personalDictWords.length; i++)
		{
			dictContents += personalDictWords[i] + "\n";
		}
		return dictContents;
	}
	
	// Return a string showing all words currently in the spell checkers
	// personal dictionary, one word per line.

	public void savePersonalDict() throws Exception //save personal dictionary to file with name from class.
	{
		File PersonalDict = new File(this.personalDictFilename); //creates a new file object named PersonalDict using the class's personalDictFilename.
		PrintWriter PD_writer = new PrintWriter(PersonalDict); //creates a new PrintWriter and attaches it to the PersonalDict file.
		PD_writer.write(getAllPersonalDictWords());
		PD_writer.close();
		System.out.println("@ Personal dictionary written to file" + personalDictFilename + ".txt");
	}
	// Write the contents of personalDictWords to the file from which
	// they were initially read (personalDictFilename).  Write one word
	// per line.  Print a message to the screen indicating the
	// dictionary has been saved in the format:
	//
	// @ Personal dictionary written to file personal-dict.txt
	//
	// where the last word on the line is the name of the file where the
	// contents are saved.

}