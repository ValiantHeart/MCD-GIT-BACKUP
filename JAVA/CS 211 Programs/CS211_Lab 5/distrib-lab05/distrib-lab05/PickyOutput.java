// Ask the user whether to print stuff to the screen or to a file.
// Create a new PrintWriter using different constructors, System.out
// or String filename.

import java.io.*;
import java.util.Scanner;

public class PickyOutput {
  public static void printStuff(PrintWriter out){
    out.println("Sweet output");
    out.printf("An int: %d\nA double %f\nA string: %s\n",
	       1, 2.5, "Three");
  }

  public static void main(String args[]) 
    throws FileNotFoundException // WTF?
  {
    Scanner in = new Scanner(System.in);
    System.out.print("Where do you want to print? ");
    String filename = in.next();
    PrintWriter pw;
    if(filename.equals("stdout")){
      pw = new PrintWriter(System.out);
    }
    else{
      pw = new PrintWriter(filename);
    }
    printStuff(pw);
    pw.close();				    // could close System.out
    System.out.println("Done with output"); // This will not print if using System.out!!
  }
}
