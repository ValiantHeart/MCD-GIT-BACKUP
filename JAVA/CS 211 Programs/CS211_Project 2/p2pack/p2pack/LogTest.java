// Example of using unit tests for this programming assignment.  This is
// partially how your code will be graded.  Later in the class we will
// write our own unit tests.  To run them on the command line, make
// sure that the junit-cs211.jar is in the project directory.
// 
// $> javac -cp .:junit-cs211.jar LogTest.java   #compile
// $> java -cp .:junit-cs211.jar LogTest         #run tests
// 
// On windows replace : with ; (colon with semicolon)
// $> javac -cp .;junit-cs211.jar LogTest.java   #compile
// $> java -cp .;junit-cs211.jar LogTest         #run tests

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class LogTest {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("LogTest");
  }
  
  
  // assertEquals(expected, actual)
  // assertTrue(booleanExpression)
  // assertFalse(booleanExpression)
  
  @Test (timeout=2000) public void log_1 (){
    // does an empty log read back an empty array?
    Log log = new Log();
    assertArrayEquals(new String[]{}, log.read());
  }
  
  @Test (timeout=2000) public void log_2 (){
    // can a log record a single message and read it back?
    Log log = new Log();
    log.record("first message");
    String[] expected = {"first message"};
    assertArrayEquals(expected, log.read());
  }
  @Test (timeout=2000) public void log_3 (){
    // can a log record multiple messages and read them back in order?
    Log log = new Log();
    log.record("A");
    log.record("B");
    log.record("C");
    log.record("D");
    String[] expected = {"A","B","C","D"};
    assertArrayEquals(expected, log.read());
  }
  @Test (timeout=2000) public void log_4 (){
    // are multiple messages recorded in their given order or are they (incorrectly) sorted? 
    Log log = new Log();
    log.record("Z");
    log.record("Y");
    log.record("W");
    log.record("X");
    String[] expected = {"Z","Y","W","X"};
    assertArrayEquals(expected, log.read());
  }
  @Test (timeout=2000) public void log_5 (){
    // Can messages have non-newline spaces in them?
    Log log = new Log();
    log.record("A");
    log.record("B\tb");
    log.record("C c .");
    log.record("D D |) |>");
    String[] expected = {"A","B\tb","C c .", "D D |) |>"};
    assertArrayEquals(expected, log.read());
  }
  @Test (timeout=2000) public void log_6 (){
    // do different logs keep out of each other's way?
    Log log1 = new Log();
    Log log2 = new Log();
    log1.record("A");
    log2.record("B");
    String[] exp1 = {"A"};
    String[] exp2 = {"B"};
    
    assertArrayEquals(exp1, log1.read());
    assertArrayEquals(exp2, log2.read());
  }
  @Test (timeout=2000) public void log_7 (){
    // does copying a log truly copy the current batch of messages?
    Log log1 = new Log();
    log1.record("A");
    log1.record("B");
    Log log2 = log1.copy();
    log1.record("C");
    String[] exp1 = {"A","B","C"};
    String[] exp2 = {"A","B"};
    
    assertArrayEquals(exp1, log1.read());
    assertArrayEquals(exp2, log2.read());
  }
}