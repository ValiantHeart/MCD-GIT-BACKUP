import org.junit.Assert;
import static org.junit.Assert.*;
// import static org.junit.*;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.io.*;
import java.util.*;

public class Lab05Tests {
  /*Main method runs tests in this file*/ 
  public static void main(String args[])
  {
    org.junit.runner.JUnitCore.main("Lab05Tests");
  } 

  // Make sure all 3 constructors are present and CensoredWriter
  // extends PrintWriter
  @Test
  public void cw_ensure_constructors () throws Exception {
    CensoredWriter cw;
    OutputStream os;
    os = new ByteArrayOutputStream();
    cw = new CensoredWriter(os, "frak");
    cw = new CensoredWriter("testfile_X.txt", "frak");
    cw = new CensoredWriter(new File("testfile_X.txt"), "frak");
    
    PrintWriter pw;
    os = new ByteArrayOutputStream();
    pw = new CensoredWriter(os, "frak");
    pw = new CensoredWriter("testfile_X.txt", "frak");
    pw = new CensoredWriter(new File("testfile_X.txt"), "frak");
  }

  // Tests formatted: censor, output string, expected string
  public static String tests[][] = {
    {"crap", "This lab is crap", "This lab is %!^*#@"},
    {"poop", "poop Poop POOP","%!^*#@ Poop POOP"},
    {"[Ff][Ll][Ii][Pp]", "Flip flip FLIP fLiP", "%!^*#@ %!^*#@ %!^*#@ %!^*#@"},
    {"Voldemort", "Don't say Voldemort! Say you-know-who!", "Don't say %!^*#@! Say you-know-who!"},
  };


  // Test transform() method
  @Test
  public void cw_transform () throws Exception {
    String actual, msg;
    CensoredWriter out;

    // Test System.out constructor
    for(int i=0; i<tests.length; i++){
      String [] test = tests[i];
      OutputStream os;
      os = new ByteArrayOutputStream();
      out = new CensoredWriter(os,test[0]);
      actual = out.transform(test[1]);
      msg = String.format("Censoring %s\nInput : %s\nExpect: %s\nActual: %s\n",
			  test[0],test[1],test[2],actual);
      assertEquals(msg,test[2],actual);
    }
    // Test String->file constructor
    String filename = "testfile_X.txt";
    for(int i=0; i<tests.length; i++){
      String [] test = tests[i];
      out = new CensoredWriter(filename,test[0]);
      actual = out.transform(test[1]);
      msg = String.format("Censoring %s\nInput : %s\nExpect: %s\nActual: %s\n",
			  test[0],test[1],test[2],actual);
      assertEquals(msg,test[2],actual);
    }
    // Test File constructor
    File file = new File(filename);
    for(int i=0; i<tests.length; i++){
      String [] test = tests[i];
      out = new CensoredWriter(file,test[0]);
      actual = out.transform(test[1]);
      msg = String.format("Censoring %s\nInput : %s\nExpect: %s\nActual: %s\n",
			  test[0],test[1],test[2],actual);
      assertEquals(msg,test[2],actual);
    }
  }

  // Tests which are censor, output lines, expected lines
  public static String [][][] fileLines = {
    {{"Voldemort"}, 
     {"Don't say Voldemort! Say you-know-who!",}, 
     {"Don't say %!^*#@! Say you-know-who!",}
    },
    {{"[Pp]oop"}, 
     {"A pooper-scooper, or poop scoop, is a device used to pick up animal",
      "feces from public places and yards, particularly those of",
      "dogs. Pooper-scooper devices often have a bag or bag attachment. 'Poop",
      "bags' are alternatives to pooper scoopers, and are simply a bag,",
      "usually turned inside out, to carry the feces to a proper disposal",
      "area. Sometimes, the person performing the cleanup is also known as",
      "the pooper-scooper.",},
     {"A %!^*#@er-scooper, or %!^*#@ scoop, is a device used to pick up animal",
      "feces from public places and yards, particularly those of",
      "dogs. %!^*#@er-scooper devices often have a bag or bag attachment. '%!^*#@",
      "bags' are alternatives to %!^*#@er scoopers, and are simply a bag,",
      "usually turned inside out, to carry the feces to a proper disposal",
      "area. Sometimes, the person performing the cleanup is also known as",
      "the %!^*#@er-scooper.",}
    },
  };
     
  // Verify a file contains the given lines
  public static void assertLines(String [] expectLines, String filename) throws Exception{
    Scanner input = new Scanner(new File(filename));
    for(int i=0; i<expectLines.length; i++){
      String actual = input.nextLine();
      String expect = expectLines[i];
      String msg = String.format("File %s Line %d\nExpect: %s\nActual: %s\n",
				 filename, i+1, expect, actual);
      assertEquals(msg,expect,actual);
    }
    input.close();
  }
				 
  @Test
  public void cw_println_file1 () throws Exception {
    String actual, msg, expect;
    String filename;
    CensoredWriter out;
    for(int i=0; i<fileLines.length; i++){
      String [][] test = fileLines[i];
      String censor = test[0][0];
      String [] outLines = test[1];
      String [] expectLines = test[2];
      filename = String.format("testfile_%d.txt",i);
      out = new CensoredWriter(filename,censor);
      for(int j=0; j<outLines.length; j++){
	out.println(outLines[j]);
      }
      out.close();
      assertLines(expectLines, filename);
    }
  }

  @Test
  public void cw_println_file2 () throws Exception {
    String actual, msg, expect;
    String filename;
    CensoredWriter out;
    for(int i=0; i<fileLines.length; i++){
      String [][] test = fileLines[i];
      String censor = test[0][0];
      String [] outLines = test[1];
      String [] expectLines = test[2];
      filename = String.format("testfile_%d.txt",i);
      out = new CensoredWriter(new File(filename),censor);
      for(int j=0; j<outLines.length; j++){
	out.println(outLines[j]);
      }
      out.close();
      assertLines(expectLines, filename);
    }
  }

  // Test print method of System.out, don't actually use System.out
  // though as Marmoset gets cranky.  Use a local outputstream
  @Test
  public void cw_print1 () {
    String actual, msg, expect;
    CensoredWriter cw;
    for(int i=0; i<tests.length; i++){
      OutputStream os = new ByteArrayOutputStream();
      String [] test = tests[i];
      cw = new CensoredWriter(os,test[0]);
      cw.print(test[1]);
      cw.flush();
      actual = os.toString();
      expect = test[2]; 
      msg = String.format("Censoring %s\nInput : %s\nExpect: %s\nActual: %s\n",
  			  test[0],test[1],expect,actual);
      assertEquals(msg,expect,actual);
    }
  }

  // Test the println method to System.out
  @Test
  public void cw_println1 () {
    String actual, msg, expect;
    CensoredWriter cw;
    for(int i=0; i<tests.length; i++){
      OutputStream os = new ByteArrayOutputStream();
      String [] test = tests[i];
      cw = new CensoredWriter(os,test[0]);
      cw.println(test[1]);
      cw.flush();
      actual = os.toString();
      expect = test[2] + newline; 
      msg = String.format("Censoring %s\nInput : %s\nExpect: %s\nActual: %s\n",
    			  test[0],test[1],expect,actual);
      assertEquals(msg,expect,actual);
    }
  }

  static String newline = System.getProperty("line.separator");

  // static ByteArrayOutputStream localOut, localErr;
  // static PrintStream sysOut, sysErr;
  // static ByteArrayInputStream localIn;
  // static InputStream sysIn;
  // static String [] emptyArgs = {};
  // static String theInput;

  // @BeforeClass
  // public static void setUp() throws Exception {
  //   sysIn  = System.in;
  //   sysOut = System.out;
  //   sysErr = System.err;
  // }

  // // Before every test is run, reset the streams to capture
  // // stdout/stderr
  // @Before
  // public void setUpStreams() {
  //   localIn  = null;
  //   localOut = new ByteArrayOutputStream();
  //   localErr = new ByteArrayOutputStream();
  //   System.setOut(new PrintStream(localOut));
  //   System.setErr(new PrintStream(localErr));
  // }

  // // After every test, restore stdout/stderr
  // @After
  // public void cleanUpStreams() {
  //   // System.setOut(null);
  //   // System.setErr(null);
  //   System.setIn(sysIn);
  //   System.setOut(sysOut);
  //   System.setErr(sysErr);
  // }

  // public void setInput(String s){
  //   localIn = new ByteArrayInputStream(s.getBytes());
  //   System.setIn(localIn);
  // }

}
