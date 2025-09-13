// Demonstrate the PrintWriter class for doing output to arbitrary
// locations.  The most common use is to print to files using either
// new PrintStream("filename.txt") 
// or new PrintStream(new File("filename.txt"))
// 
// However, one can also print to standard output (terminal) using the
// constructor
// new PrintStream(System.out)
// 
// Just make sure not to close standard output.

import java.io.*;
public class PrintWriterDemo {
  public static void main(String args[]) 
    throws FileNotFoundException // WTF?
  {
    PrintWriter out = new PrintWriter(new File("myfile.txt"));
    // PrintWriter out = new PrintWriter("myfile.txt"); // Alternative constructor that also writes to a file
    // PrintWriter out = new PrintWriter(System.out);   // Write to the screen instead
    out.println("Sweet foutput");
    out.printf("An int: %d\nA double %.1f\nA string: %s\n",
	       1, 2.5, "Three");
    out.close();                // May close System.out (bad)
  }
}
