// Public tests for Lab03 sumReals methods

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.io.*;

public class Lab03Tests {
  /*Main method runs tests in this file*/ 
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("Lab03Tests");
  } 

  // Black magic to enable access to name of running test via
  // name.getMethodName()
  @Rule public TestName name = new TestName();


  // Test whether real numbers in space-separated strings can be read;
  // opens the scanner and passes it on to user function
  public static void testSumReals_Scanner(double expect, String parseString){
    Scanner input = new Scanner(parseString);
    double actual = ScannerPractice.sumReals(input);
    double tolerance = 1e-3;
    String msg = String.format("Wrong sum\nString:\n%s\nExpect: %s\nActual: %s\n",
                               parseString,expect,actual);
    assertEquals(msg,expect,actual,tolerance);
  }    
  @Test(timeout=500) public void sumReals_Scanner_0() {
    testSumReals_Scanner(0.0,"");
  }
  @Test(timeout=500) public void sumReals_Scanner_1() {
    testSumReals_Scanner(-2.1," 0.5 1.2   -3.8");
  }
  @Test(timeout=500) public void sumReals_Scanner_2() {
    testSumReals_Scanner(4.2,"  word 1.0 3.2  ");
  }
  @Test(timeout=500) public void sumReals_Scanner_3() {
    testSumReals_Scanner(5.45," 13 doh! 4 7.65 -22.34\nbarf stuff  3.14  nada");
  }
  @Test(timeout=500) public void sumReals_Scanner_4() {
    testSumReals_Scanner(10.9,"line\n9.9\ndon't\t\t1.0");
  }
  @Test(timeout=500) public void sumReals_Scanner_5() {
    testSumReals_Scanner(201.2,"Punctuation like ! and && as well as 123 or 78.2 are words.  ");
  }

  // Test whether reading from string version is available
  public static void testSumReals_String(double expect, String parseString){
    double actual = ScannerPractice.sumReals(parseString);
    double tolerance = 1e-3;
    String msg = String.format("Wrong sum\nString:\n%s\nExpect: %s\nActual: %s\n",
                               parseString,expect,actual);
    assertEquals(msg,expect,actual,tolerance);
  }    
  @Test(timeout=500) public void sumReals_String_0() {
    testSumReals_String(0.0,"");
  }
  @Test(timeout=500) public void sumReals_String_1() {
    testSumReals_String(-2.1," 0.5 1.2   -3.8");
  }
  @Test(timeout=500) public void sumReals_String_2() {
    testSumReals_String(4.2,"  word 1.0 3.2  ");
  }
  @Test(timeout=500) public void sumReals_String_3() {
    testSumReals_String(5.45," 13 doh! 4 7.65 -22.34\nbarf stuff  3.14  nada");
  }
  @Test(timeout=500) public void sumReals_String_4() {
    testSumReals_String(10.9,"line\n9.9\ndon't\t\t1.0");
  }
  @Test(timeout=500) public void sumReals_String_5() {
    testSumReals_String(201.2,"Punctuation like ! and && as well as 123 or 78.2 are words.  ");
  }

  // Write strings to file named after test then ask user code to read
  // them in and some existing numbers
  public static void testSumReals_File(double expect, String parseString, String filename){
    double actual = -1.0;
    try{
      PrintWriter out = new PrintWriter(filename);
      out.print(parseString);
      out.close();
      actual = ScannerPractice.sumRealsInFile(filename);
    } catch(Exception e){
      fail(e.getMessage());
    }
    String msg = String.format("Wrong sum\nFile %s contents:\n%s\nExpect: %s\nActual: %s\n",
                               filename,parseString,expect,actual);
    double tolerance = 1e-3;
    assertEquals(msg,expect,actual,tolerance);
  }    
  @Test(timeout=500) public void sumReals_File_0() {
    testSumReals_File(0.0,"",
                        name.getMethodName()+".txt");
  }
  @Test(timeout=500) public void sumReals_File_1() {
    testSumReals_File(-2.1," 0.5 1.2   -3.8",
                        name.getMethodName()+".txt");
  }
  @Test(timeout=500) public void sumReals_File_2() {
    testSumReals_File(4.2,"  word 1.0 3.2  ",
                        name.getMethodName()+".txt");
  }
  @Test(timeout=500) public void sumReals_File_3() {
    testSumReals_File(5.45," 13 doh! 4 7.65 -22.34\nbarf stuff  3.14  nada",
                        name.getMethodName()+".txt");
  }
  @Test(timeout=500) public void sumReals_File_4() {
    testSumReals_File(10.9,"line\n9.9\ndon't\t\t1.0",
                        name.getMethodName()+".txt");
  }
  @Test(timeout=500) public void sumReals_File_5() {
    testSumReals_File(201.2,"Punctuation like ! and && as well as 123 or 78.2 are words.  ",
                        name.getMethodName()+".txt");
  }

}
