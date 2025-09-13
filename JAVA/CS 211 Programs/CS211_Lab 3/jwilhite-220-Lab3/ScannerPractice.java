
// Contains some simple parsing functions for reading from Scanners,
// Strings, and Files

import java.util.Scanner;
import java.io.*;

public class ScannerPractice
{

 	
	//Section for testing only....
	public static void main(String[] args)
	{
		sumReals("");
		
		
	} 

  // Accept a Scanner argument and read space-separated words from
  // it. If a word is a valid real number such as 0.1 or -3.14159 or
  // 87 then add the number onto a running total.  When there is no
  // more input available in the scanner, return the total.
  
  public static double sumReals(Scanner input)
  {
	 double x = 0.0;
	 if (input.hasNextDouble() == true)
	 {
		try 
		{
			while(true == true)// Solution: Keep going till you can't go any more.... (aka get a ElementNotFound Error...).
			{
				x += input.nextDouble();
			}
		}
		
		catch (Exception e)
		{
			return x;
		}
	 }
	 
	 else
	 {
		return x;
	 }
  }

  // Perform the same computation as sumReals(Scanner) except parse
  // the given string for real numbers.  You will need to create a new
  // Scanner which reads from parameter String.  It may be useful to
  // use employ sumReals(Scanner) in this function.
  
  public static double sumReals(String parseString)
  {
	Scanner sc = new Scanner(parseString);
	 double x = 0.0;
	 if (sc.hasNextDouble() == true)
	 {
		try 
		{
			while(true == true)// Solution: Keep going till you can't go any more.... (aka get a ElementNotFound Error...).
			{
				x += sc.nextDouble();
			}
		}
		
		catch (Exception e)
		{
			return x;
		}
	 }
	 
	 else
	 {
		return x;
	 }
  }

  // Perform the same computation as sumReals(Scanner) except read
  // from the file named by the parameter filename.  The actual file
  // with the text should already be stored on disk. Some sample files
  // are stored in wordsNnumbers1.txt and wordsNnumbers2.txt.  This
  // function may throw an exception if the named file does not
  // exist. It may be useful to use the function sumReals(Scanner).
  
  public static double sumRealsInFile(String filename) throws Exception
  {
	Scanner sc = new Scanner(new File(filename));
	double x = 0.0;
	return x;
  }
}