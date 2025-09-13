// Example of using unit tests for this programming assignment.  This is
// partially how your code will be graded.  Later in the class we will
// write our own unit tests.  To run them on the command line, make
// sure that the junit-cs211.jar is in the project directory.
// 
// $> javac -cp .:junit-cs211.jar CandidateListTest.java   #compile
// $> java -cp .:junit-cs211.jar CandidateListTest         #run tests
// 
// On windows replace : with ; (colon with semicolon)
// $> javac -cp .;junit-cs211.jar CandidateListTest.java   #compile
// $> java -cp .;junit-cs211.jar CandidateListTest         #run tests

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class CandidateListTest {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("CandidateListTest");
  }
  
  // assertEquals(expected, actual)
  // assertTrue(booleanExpression)
  // assertFalse(booleanExpression)
  
  @Test (timeout=2000) public void can_1 (){
    CandidateList c = new CandidateList();
    assertEquals(0, c.size());
  }
  @Test (timeout=2000) public void can_2 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    assertEquals(3, c.size());
  }
  @Test (timeout=2000) public void can_3 (){
    CandidateList c = new CandidateList();
    c.add("A");
    assertEquals(1, c.size());
    c.add("B");
    assertEquals(2, c.size());
    c.add("C");
    assertEquals(3, c.size());
  }
  @Test (timeout=2000) public void can_4 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    CandidateList d = c.copy();
    assertFalse(c==d); // are they aliases?
  }
  @Test (timeout=2000) public void can_5 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    CandidateList d = c.copy();
    // adding to c shouldn't affect d, as it was a copy and not an alias.
    c.add("D");
    assertEquals(4,c.size());
    assertEquals(3,d.size());
  }
  @Test (timeout=2000) public void can_6 (){
    CandidateList c = new CandidateList();
    c.add("A");
    c.add("B");
    c.add("C");
    assertEquals("A", c.get(0));
    assertEquals("B", c.get(1));
    assertEquals("C", c.get(2));
  }
  @Test (timeout=2000) public void can_7 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    // does adding affect get?
    c.add("D");
    assertEquals("A", c.get(0));
    assertEquals("B", c.get(1));
    assertEquals("C", c.get(2));
    assertEquals("D", c.get(3));
  }
  
  @Test (expected = RuntimeException.class, timeout=2000)
  public void can_8 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    // this should raise an exception; the test case is expecting it because of the @Test annotation above.
    c.get(-1);
  }
  
  @Test (expected = RuntimeException.class, timeout=2000)
  public void can_9 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    // this should raise an exception; the test case is expecting it because of the @Test annotation above.
    c.get(4);
  }
  
  @Test (timeout=2000) public void can_10 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    // will setting these make the .get()'s work later?
    c.set(0,"Q");
    c.set(1,"R");
    c.set(2,"S");
    c.set(3,"T");
    assertEquals("Q",c.get(0));
    assertEquals("R",c.get(1));
    assertEquals("S",c.get(2));
    assertEquals("T",c.get(3));
  }
  @Test (timeout=2000) public void can_11 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    // will setting these break .size() later?
    c.set(0,"Q");
    c.set(1,"R");
    c.set(2,"S");
    c.set(3,"T");
    assertEquals(4,c.size());
  }
  @Test (timeout=2000) public void can_12 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    try {
      c.set(-1,"hello");
      fail("should have raised exception, out of bounds index.");
    }
    catch (RuntimeException e){}
  }
  @Test (timeout=2000) public void can_13 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    try {
      c.set(4,"yo");
      fail("should have raised exception, out of bounds index.");
    }
    catch (RuntimeException e){}
  }
  @Test (timeout=2000) public void can_14 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    assertEquals(0, c.indexOf("W"));
    assertEquals(1, c.indexOf("X"));
    assertEquals(2, c.indexOf("Y"));
    assertEquals(3, c.indexOf("Z"));
  }
  @Test (timeout=2000) public void can_15 (){
    CandidateList c = new CandidateList(new String[]{"onlyone"});
    assertEquals(0, c.indexOf("onlyone"));
  }
  @Test (timeout=2000) public void can_16 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C","D","E","C","D"});
    assertEquals(2, c.indexOf("C"));
  }
  @Test (timeout=2000) public void can_17 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C","D","E","C","D"});
    // must *not* throw an exception when not found, as per spec.
    assertEquals(-1, c.indexOf("not there"));
  }
  
  @Test (timeout=2000) public void can_18 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C","D","E"});
    c.add("X");
    c.add("Y");
    assertEquals("X",c.get(5));
    assertEquals("Y",c.get(6));
  }
  
  @Test (timeout=2000) public void can_19 (){
    CandidateList c = new CandidateList(new String[]{"A","C","E","G"});
    
    // A-C-E-G
    c.addAt(1,"B");
    // A-B-C-E-G
    
    assertEquals("A",c.get(0));
    assertEquals("B",c.get(1));
    assertEquals("C",c.get(2));
    assertEquals("E",c.get(3));
    assertEquals("G",c.get(4));
    assertEquals(5, c.size());
  }
  
  @Test (timeout=2000) public void can_20 (){
    CandidateList c = new CandidateList(new String[]{"A","C","E","G"});
    
    // A-C-E-G
    c.addAt(1,"B");
    // A-B-C-E-G
    c.addAt(4,"F");
    // A-B-C-E-F-G
    c.addAt(3,"D");
    // A-B-C-D-E-F-G
    c.addAt(7,"H");
    assertEquals("A",c.get(0));
    assertEquals("B",c.get(1));
    assertEquals("C",c.get(2));
    assertEquals("D",c.get(3));
    assertEquals("E",c.get(4));
    assertEquals("F",c.get(5));
    assertEquals("G",c.get(6));
    assertEquals("H",c.get(7));
    
    assertEquals(8, c.size());
  }
  
  
  @Test (timeout=2000) public void can_21 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    
    // A-B-C
    c.addAt(0,"z");
    c.addAt(0,"y");
    c.addAt(0,"x");
    // x-y-z-A-B-C
    assertEquals("x",c.get(0));
    assertEquals("y",c.get(1));
    assertEquals("z",c.get(2));
    assertEquals("A",c.get(3));
    assertEquals("B",c.get(4));
    assertEquals("C",c.get(5));
    
    assertEquals(6, c.size());
    
  }
  
  @Test (timeout=2000) public void can_22 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C","D","E"});
    
    String ans = c.removeAt(0);
    assertEquals("A",ans);
    assertEquals("B",c.get(0));
    assertEquals("C",c.get(1));
    assertEquals("D",c.get(2));
    assertEquals("E",c.get(3));
    
    assertEquals(4,c.size());
    
  }
  @Test (timeout=2000) public void can_23 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C","D","E"});
    
    // A-B-C-D-E
    String ans1 = c.removeAt(0); // A
    
    // B-C-D-E
    
    assertEquals("B",c.get(0));
    assertEquals("C",c.get(1));
    assertEquals("D",c.get(2));
    assertEquals("E",c.get(3));
    assertEquals(4,c.size());
    
    String ans2 = c.removeAt(3); // E
    // B-C-D
    assertEquals("B",c.get(0));
    assertEquals("C",c.get(1));
    assertEquals("D",c.get(2));
    assertEquals(3,c.size());
    
    String ans3 = c.removeAt(1); // C
    // B-D
    assertEquals("B",c.get(0));
    assertEquals("D",c.get(1));
    assertEquals(2,c.size());
    
    String ans4 = c.removeAt(0); // B
    // D
    assertEquals("D",c.get(0));
    assertEquals(1,c.size());
    
    String ans5 = c.removeAt(0);
    // {}
    assertEquals(0,c.size());
  }
  
  @Test (timeout=2000) public void can_24 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    try {
      String ans1 = c.removeAt(-1);
      fail("should have thrown exception, index out of range.");
    } catch (RuntimeException e) { }
  }
  @Test (timeout=2000) public void can_25 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    try {
      String ans1 = c.removeAt(3);
      fail("should have thrown exception, index out of range.");
    } catch (RuntimeException e) { }
  }
  @Test (timeout=2000) public void can_26 (){
    CandidateList c = new CandidateList(new String[]{});
    CandidateList d = new CandidateList();
    assertEquals("{}", c.toString());
    assertEquals("{}", d.toString());
  }
  @Test (timeout=2000) public void can_27 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    assertEquals("{A, B, C}", c.toString());
  }
  @Test (timeout=2000) public void can_28 (){
    CandidateList c1 = new CandidateList(new String[]{});
    CandidateList c2 = new CandidateList(new String[]{"A"});
    CandidateList c3 = new CandidateList(new String[]{"A","B"});
    CandidateList c4 = new CandidateList(new String[]{"A","B","C"});
    CandidateList c5 = new CandidateList(new String[]{"A","B","C","D"});
    assertEquals("{}", c1.toString());
    assertEquals("{A}", c2.toString());
    assertEquals("{A, B}", c3.toString());
    assertEquals("{A, B, C}", c4.toString());
    assertEquals("{A, B, C, D}", c5.toString());
  }
  @Test (timeout=2000) public void can_29 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    try {
      c.addAt(0,null);
      fail("shouldn't let us addAt(#,null).");
    }
    catch (RuntimeException e) {}
  }
  @Test (timeout=2000) public void can_30 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    try {
      c.add(null);
      fail("shouldn't let us add(null).");
    }
    catch (RuntimeException e) {}
  }
  
}
