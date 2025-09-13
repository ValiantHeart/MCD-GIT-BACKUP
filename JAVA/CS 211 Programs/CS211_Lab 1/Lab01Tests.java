import org.junit.*;
import static org.junit.Assert.*;
// import org.junit.BeforeClass;
// import org.junit.Before;
// import org.junit.After;
// import org.junit.Test;
// import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;


public class Lab01Tests {
  /*Main method runs tests in this file*/ 
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("Lab01Tests");
  } 

  static ByteArrayOutputStream localOut, localErr;
  static PrintStream sysOut, sysErr;
  static String [] empty = {};

  @BeforeClass
  public static void setUp() throws Exception {
    sysOut = System.out;
    sysErr = System.err;
  }

  // Before every test is run, reset the streams to capture
  // stdout/stderr
  @Before
  public void setUpStreams() {
    localOut = new ByteArrayOutputStream();
    localErr = new ByteArrayOutputStream();
    System.setOut(new PrintStream(localOut));
    System.setErr(new PrintStream(localErr));
  }

  // After every test, restore stdout/stderr
  @After
  public void cleanUpStreams() {
    System.setOut(null);
    System.setErr(null);
    System.setOut(sysOut);
    System.setErr(sysErr);
  }

  // Determine what the newline is on the running system
  String newline = System.getProperty("line.separator");

  // Test the CSHelloLab class
  @Test public void HelloLab_main() {
    String expect = "Hello, Lab!\n";
    HelloLab.main(empty);
    String actual = localOut.toString();
    String actualNewline = actual.replaceAll("\\r?\\n","\n"); // Eliminate windows linebreaks
    String msg = String.format("Output mismatch\nEXPECTED:\n%s\nACTUAL:\n%s\n",expect,actual);
    assertTrue(msg,expect.equals(actualNewline));
  }

  // Test the CSWisdom class
  @Test public void CSWisdom_main() {
    String expect = 
      "If in physics there's something you don't understand, you can always\n" +
      "hide behind the uncharted depths of nature. You can always blame\n" +
      "God. You didn't make it so complex yourself. But if your program\n" +
      "doesn't work, there is no one to hide behind. You cannot hide behind\n" +
      "an obstinate nature. If it doesn't work, you've messed up.\n" +
      "\n" +
      "- Edsger Dijkstra\n";
    CSWisdom.main(empty);
    String actual = localOut.toString();
    String actualNewline = actual.replaceAll("\\r?\\n","\n"); // Eliminate windows linebreaks
    String msg = String.format("Output mismatch\nEXPECTED:\n%s\nACTUAL:\n%s\n",expect,actual);
    assertTrue(msg,expect.equals(actualNewline));
  }

}
