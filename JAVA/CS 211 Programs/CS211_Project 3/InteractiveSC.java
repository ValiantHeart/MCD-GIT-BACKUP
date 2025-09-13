//necessary imports:
import java.util.Scanner;
import java.io.*;

public class InteractiveSC extends SpellChecker{
// A spell checker which interactively prompts users for spelling
// corrections.  It inherets much of its functionality from
// SpellCheck but the behavior of correctWord(w) and
// correctDocument(d) is modified from the parent version.

  protected Scanner input;
  // Scanner to read input from a user. The scanner should be provided
  // in the constructor and should not be created. It may be connected
  // to System.in for true interactive use or may be fixed input in
  // from a string used for testing.

  protected PrintWriter output;
  // PrintWriter used to write output for a user. It should be
  // provided in the constructor and should not be created. It may be
  // connected to System.out to write to the screen or may write a
  // temporary buffer during tests.

  public InteractiveSC(String dictFilename, boolean ignoreCase, Scanner input, PrintWriter output)
  {
	super(dictFilename, ignoreCase);
	this.input = input;
	this.output = output;
  }
  // Constructor for the interactive spell checker.  Arguments
  // dictFilename and ignoreCase should be used to invoke the super
  // class constructor.  The input and output parameter should be set
  // to the associatd fields of this class.

  @Override public String correctWord(String word)
  {
	if (isCorrect(word) == false)
	{
		String correction =  "@- Correction for " + super.correctWord(word); //**potatoe**://
		output.println(correction);
		Scanner input = new Scanner(System.in); 
		String correction_given = input.nextLine();
		String corrected = "@ Corrected to: " + correction_given;
		return corrected;
	}
	else
	{
		return word;
	}
  }
  // Prompt the user for a correction using a prompt with the format:
  //
  // @- Correction for **potatoe**:
  //
  // where "potatoe" is replaced with the misspelled word.  Read input
  // from the user and return the provided correction.  Before
  // returning, print the correction in a message formatted:
  //
  // @ Corrected to: potato
  // 
  // Note that this method overrides the version of correctWord(w)
  // from the parent class. Like the parent version, it will produce
  // corrections irrespective of whether the given word is in the
  // dictionary.

  @Override public void correctDocument(Document doc)
  {
		while (doc.hasNextWord()) //so long as there are words ahead of the marker:
		{
			String word = doc.nextWord();
			output = new PrintWriter(System.out);
			output.println("@ MISSPELLING in: " + doc);
			if (isCorrect(word) == false)
			{
				doc.replaceLastWord(correctWord(word)); //replace the last word with the corrected version of that word.
			}
		}
  }
  // Starting from the beginning of the document, apply corrections to
  // all misspelled words. When a misspelled word is found, print a
  // message and line of the document with the misspelled word
  // highlighed as in
  //
  // @ MISSPELLING in: One **potatoe**, two potatoe, three potatoe, four.
  //
  // Then prompt the user for a correction as in
  //
  // @- Correction for **potatoe**:
  // 
  // Print newlines a the end of both messages.  Printing to the
  // screen should use the output PrintWriter tracked by the
  // interactive spell checker.  Reading input should use the input
  // Scanner tracked by the spell checker.  Note that this method
  // overrides the version of correctDocument(w) from the parent
  // class.

}