// Example of using unit tests for this programming assignment.  This is
// partially how your code will be graded.  Later in the class we will
// write our own unit tests.  To run them on the command line, make
// sure that the junit-cs211.jar is in the project directory.
// 
// $> javac -cp .:junit-cs211.jar VoteListTest.java   #compile
// $> java -cp .:junit-cs211.jar VoteListTest         #run tests
// 
// On windows replace : with ; (colon with semicolon)
// $> javac -cp .;junit-cs211.jar VoteListTest.java   #compile
// $> java -cp .;junit-cs211.jar VoteListTest         #run tests

import org.junit.*;  // Test, Rule
import static org.junit.Assert.*;
import java.util.*;

// junit 4.12 --->  import org.junit.rules.Timeout; // in order to set the global timeout

public class VoteListTest {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("VoteListTest");
  }
  
  // junit 4.12 ---> @Rule public Timeout globalTimeout = Timeout.millis(1000);
  
  // assertEquals(expected, actual)
  // assertTrue(booleanExpression)
  // assertFalse(booleanExpression)
  
  @Test (timeout=2000) public void votelist_1 (){
    VoteList c = new VoteList();
    assertEquals(0, c.size());
  }
  @Test (timeout=2000) public void votelist_2 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    Vote v1 = new Vote(c,new int[]{1,2,3});
    Vote v2 = new Vote(c,new int[]{1,3,2});
    Vote v3 = new Vote(c,new int[]{3,1,2});
    
    VoteList vs = new VoteList(new Vote[]{v1,v2,v3});
    assertEquals(3, vs.size());
  }
  @Test (timeout=2000) public void votelist_3 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    VoteList vs = new VoteList();
    vs.add(new Vote(c,new int[]{1,2,3}));
    assertEquals(1, vs.size());
    vs.add(new Vote(c,new int[]{3,2,1}));
    assertEquals(2, vs.size());
    vs.add(new Vote(c,new int[]{2,1,3}));
    assertEquals(3, vs.size());
  }
  @Test (timeout=2000) public void votelist_4 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    Vote v1 = new Vote(c,new int[]{1,2,3});
    Vote v2 = new Vote(c,new int[]{1,3,2});
    Vote v3 = new Vote(c,new int[]{3,1,2});
    VoteList vs = new VoteList(new Vote[]{v1,v2,v3});
    VoteList other = vs.copy();
    assertFalse(vs==other); // are they aliases?
  }
  @Test (timeout=2000) public void votelist_5 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    Vote v1 = new Vote(c,new int[]{1,2,3});
    Vote v2 = new Vote(c,new int[]{1,3,2});
    Vote v3 = new Vote(c,new int[]{3,1,2});
    VoteList vs = new VoteList(new Vote[]{v1,v2,v3});
    VoteList other = vs.copy();
    // adding to vs shouldn't affect other, as it was a copy and not an alias.
    vs.add(new Vote(c,new int[]{3,2,1}));
    assertEquals(4,vs.size());
    assertEquals(3,other.size());
  }
  
  public void compareVotes(Vote a, Vote b){
    CandidateList ca = a.copyOfCandidateList();
    CandidateList cb = b.copyOfCandidateList();
    
    if (ca.size() != cb.size()){
      fail("different length candidate lists.");
    }
    for (int i=1;i<=ca.size();i++){
      if (a.indexOfRank(i) != b.indexOfRank(i)){
       fail("ranks in different places."); 
      }
    }
  }
  
  @Test (timeout=2000) public void votelist_6 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    Vote v1 = new Vote(c,new int[]{1,2,3});
    Vote v2 = new Vote(c,new int[]{1,3,2});
    Vote v3 = new Vote(c,new int[]{3,1,2});
    VoteList vs = new VoteList();
    vs.add(v1);
    vs.add(v2);
    vs.add(v3);
    assertEquals("A", c.get(0));
    assertEquals("B", c.get(1));
    assertEquals("C", c.get(2));
  }
  @Test (timeout=2000) public void votelist_7 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    Vote v1 = new Vote(c,new int[]{1,2,3});
    Vote v2 = new Vote(c,new int[]{1,3,2});
    Vote v3 = new Vote(c,new int[]{3,1,2});
    Vote v4 = new Vote(c,new int[]{2,3,1});
    VoteList vs = new VoteList(new Vote[]{v1,v2,v3});
    // does adding affect get?
    vs.add(v4);
    assertEquals(v1, vs.get(0));
    assertEquals(v2, vs.get(1));
    assertEquals(v3, vs.get(2));
    assertEquals(v4, vs.get(3));
    assertEquals(4,vs.size());
  }
  @Test (expected = RuntimeException.class, timeout=2000)
  public void votelist_8 (){
    VoteList vs = new VoteList();
    // this should raise an exception; the test case is expecting it because of the @Test annotation above.
    vs.get(-1);
  }
  
  @Test (expected = RuntimeException.class, timeout=2000)
  public void votelist_9 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    Vote v1 = new Vote(c,new int[]{1,2,3,4});
    Vote v2 = new Vote(c,new int[]{1,3,2,4});
    Vote v3 = new Vote(c,new int[]{3,1,4,2});
    Vote v4 = new Vote(c,new int[]{2,3,4,1});
    VoteList vs = new VoteList(new Vote[]{v1,v2,v3,v4});
    // this should raise an exception; the test case is expecting it because of the @Test annotation above.
    vs.get(4);
  }

  @Test (timeout=2000) public void votelist_10 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    Vote v1 = new Vote(c,new int[]{1,2,3,4});
    Vote v2 = new Vote(c,new int[]{1,2,4,3});
    Vote v3 = new Vote(c,new int[]{1,3,2,4});
    Vote v4 = new Vote(c,new int[]{1,3,4,2});
    Vote v5 = new Vote(c,new int[]{1,4,2,3});
    Vote v6 = new Vote(c,new int[]{1,4,3,2});
    Vote v7 = new Vote(c,new int[]{2,3,4,1});
    Vote v8 = new Vote(c,new int[]{2,3,4,1});
    VoteList vs = new VoteList(new Vote[]{v1,v2,v3,v4});
    // will setting these make the .get()'s work later?
    vs.set(0,v5);
    vs.set(1,v6);
    vs.set(2,v7);
    vs.set(3,v8);
    assertEquals(v5,vs.get(0));
    assertEquals(v6,vs.get(1));
    assertEquals(v7,vs.get(2));
    assertEquals(v8,vs.get(3));
  }
  @Test (timeout=2000) public void votelist_11 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    Vote v1 = new Vote(c,new int[]{1,2,3,4});
    Vote v2 = new Vote(c,new int[]{1,2,4,3});
    Vote v3 = new Vote(c,new int[]{1,3,2,4});
    Vote v4 = new Vote(c,new int[]{1,3,4,2});
    Vote v5 = new Vote(c,new int[]{1,4,2,3});
    Vote v6 = new Vote(c,new int[]{1,4,3,2});
    Vote v7 = new Vote(c,new int[]{2,3,4,1});
    Vote v8 = new Vote(c,new int[]{2,3,4,1});
    VoteList vs = new VoteList(new Vote[]{v1,v2,v3,v4});
    // will setting these make the .get()'s work later?
    vs.set(0,v5);
    vs.set(1,v6);
    vs.set(2,v7);
    vs.set(3,v8);
    assertEquals(4,c.size());
  }
  @Test (timeout=2000) public void votelist_12 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    Vote v1 = new Vote(c,new int[]{1,2,3,4});
    Vote v2 = new Vote(c,new int[]{1,2,4,3});
    Vote v3 = new Vote(c,new int[]{1,3,2,4});
    Vote v4 = new Vote(c,new int[]{1,3,4,2});
    Vote v5 = new Vote(c,new int[]{1,4,2,3});
    Vote v6 = new Vote(c,new int[]{1,4,3,2});
    Vote v7 = new Vote(c,new int[]{2,3,4,1});
    Vote v8 = new Vote(c,new int[]{2,3,4,1});
    VoteList vs = new VoteList(new Vote[]{v1,v2,v3,v4});
    try {
      vs.set(-1,v7);
      fail("should have raised exception, out of bounds index.");
    }
    catch (RuntimeException e){}
  }
  @Test (timeout=2000) public void votelist_13 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    Vote v1 = new Vote(c,new int[]{1,2,3,4});
    Vote v2 = new Vote(c,new int[]{1,2,4,3});
    Vote v3 = new Vote(c,new int[]{1,3,2,4});
    Vote v4 = new Vote(c,new int[]{1,3,4,2});
    Vote v5 = new Vote(c,new int[]{1,4,2,3});
    Vote v6 = new Vote(c,new int[]{1,4,3,2});
    Vote v7 = new Vote(c,new int[]{2,3,4,1});
    Vote v8 = new Vote(c,new int[]{2,3,4,1});
    VoteList vs = new VoteList(new Vote[]{v1,v2,v3,v4});
    try {
      vs.set(4,v8);
      fail("should have raised exception, out of bounds index.");
    }
    catch (RuntimeException e){}
  }
  @Test (timeout=2000) public void votelist_14 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    Vote v1 = new Vote(c,new int[]{1,2,3,4});
    Vote v2 = new Vote(c,new int[]{1,2,4,3});
    Vote v3 = new Vote(c,new int[]{1,3,2,4});
    Vote v4 = new Vote(c,new int[]{1,3,4,2});
    Vote v5 = new Vote(c,new int[]{1,4,2,3});
    Vote v6 = new Vote(c,new int[]{1,4,3,2});
    Vote v7 = new Vote(c,new int[]{2,3,4,1});
    Vote v8 = new Vote(c,new int[]{2,3,4,1});
    VoteList vs = new VoteList(new Vote[]{v1,v2,v3,v4});
    assertEquals(0, vs.indexOf(v1));
    assertEquals(1, vs.indexOf(v2));
    assertEquals(2, vs.indexOf(v3));
    assertEquals(3, vs.indexOf(v4));
  }
  @Test (timeout=2000) public void votelist_15 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    Vote v1 = new Vote(c,new int[]{1,2,3,4});
    VoteList vs = new VoteList(new Vote[]{v1});
    assertEquals(0, vs.indexOf(v1));
  }
  @Test (timeout=2000) public void votelist_16 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    Vote v1 = new Vote(c,new int[]{1,2,3,4});
    Vote v2 = new Vote(c,new int[]{1,2,4,3});
    Vote v3 = new Vote(c,new int[]{1,3,2,4});
    Vote v4 = new Vote(c,new int[]{1,3,4,2});
    Vote v5 = new Vote(c,new int[]{1,4,2,3});
    Vote v6 = new Vote(c,new int[]{1,4,3,2});
    Vote v7 = new Vote(c,new int[]{2,3,4,1});
    Vote v8 = new Vote(c,new int[]{2,3,4,1});
    VoteList vs = new VoteList(new Vote[]{v1,v2,v3,v4});
    assertEquals(2, vs.indexOf(v3));
  }
  @Test (timeout=2000) public void votelist_17 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    Vote v1 = new Vote(c,new int[]{1,2,3,4});
    Vote v2 = new Vote(c,new int[]{1,2,4,3});
    Vote v3 = new Vote(c,new int[]{1,3,2,4});
    Vote v4 = new Vote(c,new int[]{1,3,4,2});
    Vote v5 = new Vote(c,new int[]{1,4,2,3});
    VoteList vs = new VoteList(new Vote[]{v1,v2,v3,v4});
    // must *not* throw an exception when not found, as per spec.
    assertEquals(-1, vs.indexOf(v5));
  }
  
  @Test (timeout=2000) public void votelist_18 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    Vote v1 = new Vote(c,new int[]{1,2,3,4});
    Vote v2 = new Vote(c,new int[]{1,2,4,3});
    Vote v3 = new Vote(c,new int[]{1,3,2,4});
    Vote v4 = new Vote(c,new int[]{1,3,4,2});
    Vote v5 = new Vote(c,new int[]{1,4,2,3});
    Vote v6 = new Vote(c,new int[]{1,4,3,2});
    VoteList vs = new VoteList(new Vote[]{v1,v2,v3,v4});
    vs.add(v5);
    vs.add(v6);
    assertEquals(v5,vs.get(4));
    assertEquals(v6,vs.get(5));
  }
  
  @Test (timeout=2000) public void votelist_19 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    Vote vA = new Vote(c,new int[]{1,2,3,4});
    Vote vB = new Vote(c,new int[]{1,2,4,3});
    Vote vC = new Vote(c,new int[]{1,3,2,4});
    Vote vD = new Vote(c,new int[]{1,3,4,2});
    Vote vE = new Vote(c,new int[]{1,4,2,3});
    Vote vF = new Vote(c,new int[]{1,4,3,2});
    Vote vG = new Vote(c,new int[]{2,3,4,1});
    Vote vH = new Vote(c,new int[]{2,3,4,1});
    VoteList vs = new VoteList(new Vote[]{vA,vC,vE,vG});
    
    // A-C-E-G
    vs.addAt(1,vB);
    // A-B-C-E-G
    
    assertEquals(vA,vs.get(0));
    assertEquals(vB,vs.get(1));
    assertEquals(vC,vs.get(2));
    assertEquals(vE,vs.get(3));
    assertEquals(vG,vs.get(4));
    assertEquals(5, vs.size());
  }
  
  @Test (timeout=2000) public void votelist_20 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    Vote vA = new Vote(c,new int[]{1,2,3,4});
    Vote vB = new Vote(c,new int[]{1,2,4,3});
    Vote vC = new Vote(c,new int[]{1,3,2,4});
    Vote vD = new Vote(c,new int[]{1,3,4,2});
    Vote vE = new Vote(c,new int[]{1,4,2,3});
    Vote vF = new Vote(c,new int[]{1,4,3,2});
    Vote vG = new Vote(c,new int[]{2,3,4,1});
    Vote vH = new Vote(c,new int[]{2,3,4,1});
    VoteList vs = new VoteList(new Vote[]{vA,vC,vE,vG});
    
    // A-C-E-G
    vs.addAt(1,vB);
    // A-B-C-E-G
    vs.addAt(4,vF);
    // A-B-C-E-F-G
    vs.addAt(3,vD);
    // A-B-C-D-E-F-G
    vs.addAt(7,vH);
    assertEquals(vA,vs.get(0));
    assertEquals(vB,vs.get(1));
    assertEquals(vC,vs.get(2));
    assertEquals(vD,vs.get(3));
    assertEquals(vE,vs.get(4));
    assertEquals(vF,vs.get(5));
    assertEquals(vG,vs.get(6));
    assertEquals(vH,vs.get(7));
    
    assertEquals(8, vs.size());
  }
  
  
  @Test (timeout=2000) public void votelist_21 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    Vote vA = new Vote(c,new int[]{1,2,3,4});
    Vote vB = new Vote(c,new int[]{1,2,4,3});
    Vote vC = new Vote(c,new int[]{1,3,2,4});
    Vote vD = new Vote(c,new int[]{1,3,4,2});
    Vote vE = new Vote(c,new int[]{1,4,2,3});
    Vote vF = new Vote(c,new int[]{1,4,3,2});
    Vote vG = new Vote(c,new int[]{2,3,4,1});
    Vote vH = new Vote(c,new int[]{2,3,4,1});
    VoteList vs = new VoteList(new Vote[]{vA,vB,vC});
    
    // A-B-C
    vs.addAt(0,vH);
    vs.addAt(0,vG);
    vs.addAt(0,vF);
    // f-g-h-A-B-C
    assertEquals(vF,vs.get(0));
    assertEquals(vG,vs.get(1));
    assertEquals(vH,vs.get(2));
    assertEquals(vA,vs.get(3));
    assertEquals(vB,vs.get(4));
    assertEquals(vC,vs.get(5));
    
    assertEquals(6, vs.size());
    
  }
  
  @Test (timeout=2000) public void votelist_22 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    Vote vA = new Vote(c,new int[]{1,2,3,4});
    Vote vB = new Vote(c,new int[]{1,2,4,3});
    Vote vC = new Vote(c,new int[]{1,3,2,4});
    Vote vD = new Vote(c,new int[]{1,3,4,2});
    Vote vE = new Vote(c,new int[]{1,4,2,3});
    Vote vF = new Vote(c,new int[]{1,4,3,2});
    Vote vG = new Vote(c,new int[]{2,3,4,1});
    Vote vH = new Vote(c,new int[]{2,3,4,1});
    VoteList vs = new VoteList(new Vote[]{vA,vB,vC,vD,vE});
    
    Vote ans = vs.removeAt(0);
    assertEquals(vA,ans);
    assertEquals(vB,vs.get(0));
    assertEquals(vC,vs.get(1));
    assertEquals(vD,vs.get(2));
    assertEquals(vE,vs.get(3));
    
    assertEquals(4,vs.size());
    
  }
  @Test (timeout=2000) public void votelist_23 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    Vote vA = new Vote(c,new int[]{1,2,3,4});
    Vote vB = new Vote(c,new int[]{1,2,4,3});
    Vote vC = new Vote(c,new int[]{1,3,2,4});
    Vote vD = new Vote(c,new int[]{1,3,4,2});
    Vote vE = new Vote(c,new int[]{1,4,2,3});
    Vote vF = new Vote(c,new int[]{1,4,3,2});
    Vote vG = new Vote(c,new int[]{2,3,4,1});
    Vote vH = new Vote(c,new int[]{2,3,4,1});
    VoteList vs = new VoteList(new Vote[]{vA,vB,vC,vD,vE});
    
    // A-B-C-D-E
    Vote ans1 = vs.removeAt(0); // A
    
    // B-C-D-E
    
    assertEquals(vB,vs.get(0));
    assertEquals(vC,vs.get(1));
    assertEquals(vD,vs.get(2));
    assertEquals(vE,vs.get(3));
    assertEquals(4,vs.size());
    
    Vote ans2 = vs.removeAt(3); // E
    // B-C-D
    assertEquals(vB,vs.get(0));
    assertEquals(vC,vs.get(1));
    assertEquals(vD,vs.get(2));
    assertEquals(3,vs.size());
    
    Vote ans3 = vs.removeAt(1); // C
    // B-D
    assertEquals(vB,vs.get(0));
    assertEquals(vD,vs.get(1));
    assertEquals(2,vs.size());
    
    Vote ans4 = vs.removeAt(0); // B
    // D
    assertEquals(vD,vs.get(0));
    assertEquals(1,vs.size());
    
    Vote ans5 = vs.removeAt(0);
    // {}
    assertEquals(0,vs.size());
  }
  
  @Test (timeout=2000) public void votelist_24 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    Vote vA = new Vote(c,new int[]{1,2,3,4});
    Vote vB = new Vote(c,new int[]{1,2,4,3});
    Vote vC = new Vote(c,new int[]{1,3,2,4});
    VoteList vs = new VoteList(new Vote[]{vA,vB,vC});
    try {
      Vote ans1 = vs.removeAt(-1);
      fail("should have thrown exception, index out of range.");
    } catch (RuntimeException e) { }
  }
  @Test (timeout=2000) public void votelist_25 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    Vote vA = new Vote(c,new int[]{1,2,3,4});
    Vote vB = new Vote(c,new int[]{1,2,4,3});
    Vote vC = new Vote(c,new int[]{1,3,2,4});
    VoteList vs = new VoteList(new Vote[]{vA,vB,vC});
    try {
      Vote ans1 = vs.removeAt(3);
      fail("should have thrown exception, index out of range.");
    } catch (RuntimeException e) { }
  }
  @Test (timeout=2000) public void votelist_26 (){
    VoteList c = new VoteList(new Vote[]{});
    VoteList d = new VoteList();
    assertEquals("{}", c.toString());
    assertEquals("{}", d.toString());
  }
  @Test (timeout=2000) public void votelist_27 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    Vote vA = new Vote(c,new int[]{1,2,3,4});
    Vote vB = new Vote(c,new int[]{1,2,4,3});
    Vote vC = new Vote(c,new int[]{1,3,2,4});
    VoteList vs = new VoteList(new Vote[]{vA,vB,vC});
    
    assertEquals("{{W:1, X:2, Y:3, Z:4}, {W:1, X:2, Y:4, Z:3}, {W:1, X:3, Y:2, Z:4}}", vs.toString());
  }
  @Test (timeout=2000) public void votelist_28 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    Vote vA = new Vote(c,new int[]{1,2,3,4});
    Vote vB = new Vote(c,new int[]{1,2,4,3});
    Vote vC = new Vote(c,new int[]{1,3,2,4});
    Vote vD = new Vote(c,new int[]{1,3,4,2});
    Vote vE = new Vote(c,new int[]{1,4,2,3});
    Vote vF = new Vote(c,new int[]{1,4,3,2});
    Vote vG = new Vote(c,new int[]{2,3,4,1});
    Vote vH = new Vote(c,new int[]{2,3,4,1});
    VoteList vs0 = new VoteList(new Vote[]{});
    VoteList vs1 = new VoteList(new Vote[]{vA});
    VoteList vs2 = new VoteList(new Vote[]{vA,vB});
    VoteList vs3 = new VoteList(new Vote[]{vA,vB,vC});
    VoteList vs4 = new VoteList(new Vote[]{vA,vB,vC,vD});
    VoteList vs5 = new VoteList(new Vote[]{vA,vB,vC,vD,vE});
    
    assertEquals("{}", vs0.toString());
    assertEquals("{{W:1, X:2, Y:3, Z:4}}", vs1.toString());
    assertEquals("{{W:1, X:2, Y:3, Z:4}, {W:1, X:2, Y:4, Z:3}}", vs2.toString());
    assertEquals("{{W:1, X:2, Y:3, Z:4}, {W:1, X:2, Y:4, Z:3}, {W:1, X:3, Y:2, Z:4}}", vs3.toString());
    assertEquals("{{W:1, X:2, Y:3, Z:4}, {W:1, X:2, Y:4, Z:3}, {W:1, X:3, Y:2, Z:4}, {W:1, X:3, Y:4, Z:2}}", vs4.toString());
    assertEquals("{{W:1, X:2, Y:3, Z:4}, {W:1, X:2, Y:4, Z:3}, {W:1, X:3, Y:2, Z:4}, {W:1, X:3, Y:4, Z:2}, {W:1, X:4, Y:2, Z:3}}", vs5.toString());
  }
  @Test (timeout=2000) public void votelist_29 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    Vote vA = new Vote(c,new int[]{1,2,3,4});
    Vote vB = new Vote(c,new int[]{1,2,4,3});
    Vote vC = new Vote(c,new int[]{1,3,2,4});
    VoteList vs = new VoteList(new Vote[]{vA,vB,vC});
    try {
      vs.addAt(0,null);
      fail("shouldn't let us addAt(#,null).");
    }
    catch (RuntimeException e) {}
  }
  @Test (timeout=2000) public void votelist_30 (){
    CandidateList c = new CandidateList(new String[]{"W","X","Y","Z"});
    Vote vA = new Vote(c,new int[]{1,2,3,4});
    Vote vB = new Vote(c,new int[]{1,2,4,3});
    Vote vC = new Vote(c,new int[]{1,3,2,4});
    Vote vD = new Vote(c,new int[]{1,3,4,2});
    Vote vE = new Vote(c,new int[]{1,4,2,3});
    VoteList vs = new VoteList(new Vote[]{vA,vB,vC,vD,vE});
    
    try {
      vs.add(null);
      fail("shouldn't let us add(null).");
    }
    catch (RuntimeException e) {}
  }
}
