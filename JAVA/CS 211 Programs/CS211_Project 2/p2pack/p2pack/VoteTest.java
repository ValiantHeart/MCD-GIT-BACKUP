// Example of using unit tests for this programming assignment.  This is
// partially how your code will be graded.  Later in the class we will
// write our own unit tests.  To run them on the command line, make
// sure that the junit-cs211.jar is in the project directory.
// 
// $> javac -cp .:junit-cs211.jar VoteTest.java   #compile
// $> java -cp .:junit-cs211.jar VoteTest         #run tests
// 
// On windows replace : with ; (colon with semicolon)
// $> javac -cp .;junit-cs211.jar VoteTest.java   #compile
// $> java -cp .;junit-cs211.jar VoteTest         #run tests

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class VoteTest {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("VoteTest");
  }
  
  
  // assertEquals(expected, actual)
  // assertTrue(booleanExpression)
  // assertFalse(booleanExpression)
  
  @Test (timeout=2000) public void vote_1 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    Vote v = new Vote(c);
  }
  @Test (timeout=2000) public void vote_2 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    int[] s = {1,3,2};
    Vote v = new Vote(c,s);
  }
  @Test (timeout=2000) public void vote_3 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    int[] s = {1,3,2};
    Vote v = new Vote(c,s);
    // check that indexOfRank finds them all.
    assertEquals(0,v.indexOfRank(1));
    assertEquals(1,v.indexOfRank(3));
    assertEquals(2,v.indexOfRank(2));
  }
  @Test (timeout=2000) public void vote_4 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C","D","E","F","G","H"});
    int[] s = {8,7,6,4,5,1,3,2};
    Vote v = new Vote(c,s);
    // check that indexOfRank finds them all.
    assertEquals(0,v.indexOfRank(8));
    assertEquals(1,v.indexOfRank(7));
    assertEquals(2,v.indexOfRank(6));
    assertEquals(3,v.indexOfRank(4));
    assertEquals(4,v.indexOfRank(5));
    assertEquals(5,v.indexOfRank(1));
    assertEquals(6,v.indexOfRank(3));
    assertEquals(7,v.indexOfRank(2));
  }
  @Test (timeout=2000) public void vote_5 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    int[] s = {1,3,2};
    Vote v = new Vote(c,s);
    try {
      int x = v.indexOfRank(4);
      fail ("don't let indexOfRank return when rank not found.");
    } catch (RuntimeException e){}
    try {
      int x = v.indexOfRank(-3);
      fail ("don't let indexOfRank return when rank not found.");
    } catch (RuntimeException e){}
    try {
      int x = v.indexOfRank(0);
      fail ("don't let indexOfRank return when rank not found.");
    } catch (RuntimeException e){}
  }
  @Test (timeout=2000) public void vote_6 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    int[] s = {1,3,2};
    Vote v = new Vote(c,s);
    // check that indexOfRank finds them all.
    
    assertEquals("A",v.getRankedCandidate(1));
    assertEquals("B",v.getRankedCandidate(3));
    assertEquals("C",v.getRankedCandidate(2));
  }
  @Test (timeout=2000) public void vote_7 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C","D","E","F","G","H"});
    int[] s = {8,7,6,4,5,1,3,2};
    Vote v = new Vote(c,s);
    // check that getRankedCandidate finds them all.
    
    assertEquals("A",v.getRankedCandidate(8));
    assertEquals("B",v.getRankedCandidate(7));
    assertEquals("C",v.getRankedCandidate(6));
    assertEquals("D",v.getRankedCandidate(4));
    assertEquals("E",v.getRankedCandidate(5));
    assertEquals("F",v.getRankedCandidate(1));
    assertEquals("G",v.getRankedCandidate(3));
    assertEquals("H",v.getRankedCandidate(2));
  }
  @Test (timeout=2000) public void vote_8 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C"});
    int[] s = {1,3,2};
    Vote v = new Vote(c,s);
    try {
      String  x = v.getRankedCandidate(4);
      fail ("don't let getRankedCandidate return when rank not found.");
    } catch (RuntimeException e){}
    try {
      String x = v.getRankedCandidate(-1);
      fail ("don't let getRankedCandidate return when rank not found.");
    } catch (RuntimeException e){}
    try {
      String x = v.getRankedCandidate(0);
      fail ("don't let getRankedCandidate return when rank not found.");
    } catch (RuntimeException e){}
  }
  @Test (timeout=2000) public void vote_9 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C","D"});
    int[] s = new int[4];
    Vote v = new Vote(c,s);
    //         (rank,index)
    v.recordChoice(1,0);
    v.recordChoice(4,1);
    v.recordChoice(2,2);
    v.recordChoice(3,3);
    
    // check that all our recordings worked.
    assertEquals(0,v.indexOfRank(1));
    assertEquals(1,v.indexOfRank(4));
    assertEquals(2,v.indexOfRank(2));
    assertEquals(3,v.indexOfRank(3));
  }
  @Test (timeout=2000) public void vote_10 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C","D"});
    int[] s = new int[4];
    Vote v = new Vote(c,s);
    try{
      v.recordChoice(1,-1);
      fail("don't let recordChoice accept negative indexes.");
    } catch (RuntimeException e){}
    try{
      v.recordChoice(1,5);
      fail("don't let recordChoice accept too-large indexes.");
    } catch (RuntimeException e){}
  }
  @Test (timeout=2000) public void vote_11 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C","D"});
    int[] s = new int[4];
    Vote v = new Vote(c,s);
    try{
      v.recordChoice(-13,0);
      fail("don't let recordChoice accept invalid ranks.");
    } catch (RuntimeException e){}
    try{
      v.recordChoice(123,4);
      fail("don't let recordChoice accept invalid ranks.");
    } catch (RuntimeException e){}
  }
  @Test (timeout=2000) public void vote_12 (){
    CandidateList c = new CandidateList();
    int[] s = new int[0];
    Vote v = new Vote(c,s);
    try{
      // neither rank nor index makes sense here, should crash.
      v.recordChoice(1,0);
      fail("don't let recordChoice accept invalid rank/index.");
    } catch (RuntimeException e){}
  }
  
  
  
  
  
  
  @Test (timeout=2000) public void vote_13 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C","D"});
    int[] s = new int[4];
    Vote v = new Vote(c,s);
    //         (rank,candidate)
    v.recordChoice(1,"A");
    v.recordChoice(4,"B");
    v.recordChoice(2,"C");
    v.recordChoice(3,"D");
    
    // check that all our recordings worked.
    assertEquals(0,v.indexOfRank(1));
    assertEquals(1,v.indexOfRank(4));
    assertEquals(2,v.indexOfRank(2));
    assertEquals(3,v.indexOfRank(3));
  }
  @Test (timeout=2000) public void vote_14 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C","D"});
    int[] s = new int[4];
    Vote v = new Vote(c,s);
    try{
      v.recordChoice(1,"MISSING");
      fail("don't let recordChoice accept missing candidates.");
    } catch (RuntimeException e){}
  }
  @Test (timeout=2000) public void vote_15 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C","D"});
    int[] s = new int[4];
    Vote v = new Vote(c,s);
    try{
      v.recordChoice(-3,"A");
      fail("don't let recordChoice accept negative indexes.");
    } catch (RuntimeException e){}
    try{
      v.recordChoice(123,"C");
      fail("don't let recordChoice accept invalid ranks.");
    } catch (RuntimeException e){}
  }
  
  
  @Test (timeout=2000) public void vote_bc_1 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C","D"});
    //         A B C D
    int[] s = {4,2,1,3};
    Vote v = new Vote(c,s);
    assertEquals("C",v.bestChoice(new CandidateList(new String[]{"A","B","C","D"})));
  }
  
  @Test (timeout=2000) public void vote_bc_2 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C","D"});
    //         A B C D
    int[] s = {4,2,1,3};
    Vote v = new Vote(c,s);
    assertEquals("B",v.bestChoice(new CandidateList(new String[]{"A","B","D"})));
    assertEquals("D",v.bestChoice(new CandidateList(new String[]{"A","D"})));
    assertEquals("A",v.bestChoice(new CandidateList(new String[]{"A"})));
  }
  
  @Test (timeout=2000) public void vote_bc_3 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C","D"});
    //         A B C D
    int[] s = {4,2,1,3};
    Vote v = new Vote(c,s);
    assertEquals(null,v.bestChoice(new CandidateList(new String[]{"X","Y","Z"})));
  }
  
  @Test (timeout=2000) public void vote_bc_4 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C","D"});
    //         A B C D
    int[] s = {1,2,3,4};
    Vote v = new Vote(c,s);
    assertEquals("A",v.bestChoice(new CandidateList(new String[]{"A","B","C","D"})));
    assertEquals("B",v.bestChoice(new CandidateList(new String[]{"B","C","D"})));
    assertEquals("C",v.bestChoice(new CandidateList(new String[]{"C","D"})));
    assertEquals("D",v.bestChoice(new CandidateList(new String[]{"D"})));
  }
  
  @Test (timeout=2000) public void vote_bc_5 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C","D"});
    //         A B C D
    int[] s = {1,2,3,4};
    Vote v = new Vote(c,s);
    
    CandidateList other = v.copyOfCandidateList();
    
    // must be different objects:
    assertEquals(false,c==other);
  }
  
  @Test (timeout=2000) public void vote_copylist_1 (){
    CandidateList c = new CandidateList(new String[]{"A","B","C","D"});
    //         A B C D
    int[] s = {1,2,3,4};
    Vote v = new Vote(c,s);
    
    CandidateList other = v.copyOfCandidateList();
    
    // must have same candidates:
    assertEquals(4, c.size());
    assertEquals(4, other.size());
    
    assertEquals("A",c.get(0));
    assertEquals("B",c.get(1));
    assertEquals("C",c.get(2));
    assertEquals("D",c.get(3));
    
    assertEquals("A",other.get(0));
    assertEquals("B",other.get(1));
    assertEquals("C",other.get(2));
    assertEquals("D",other.get(3));
  }

  @Test (timeout=2000) public void vote_validate_1 (){
    Vote v = new Vote(new CandidateList(new String[]{"A","B","C"}), new int[]{1,2,3});
    assertEquals(true,v.validateVote());
  }

  @Test (timeout=2000) public void vote_validate_2 (){
    Vote v = new Vote(null, new int[]{1,2,3});
    assertEquals(false,v.validateVote());
  }

  @Test (timeout=2000) public void vote_validate_3 (){
    Vote v = new Vote(new CandidateList(new String[]{"A","B","C"}), null);
    assertEquals(false,v.validateVote());
  }

  @Test (timeout=2000) public void vote_validate_4 (){
    Vote v = new Vote(new CandidateList(new String[]{"A","B","C"}), new int[]{1,2,3,4,5});
    assertEquals(false,v.validateVote());
  }

  @Test (timeout=2000) public void vote_validate_5 (){
    Vote v = new Vote(new CandidateList(new String[]{"A","B","C"}), new int[]{1,2});
    assertEquals(false,v.validateVote());
  }

  @Test (timeout=2000) public void vote_validate_6 (){
    // all possible rank orderings
    Vote v1 = new Vote(new CandidateList(new String[]{"A","B","C"}), new int[]{1,2,3});
    Vote v2 = new Vote(new CandidateList(new String[]{"A","B","C"}), new int[]{1,3,2});
    Vote v3 = new Vote(new CandidateList(new String[]{"A","B","C"}), new int[]{2,1,3});
    Vote v4 = new Vote(new CandidateList(new String[]{"A","B","C"}), new int[]{2,3,1});
    Vote v5 = new Vote(new CandidateList(new String[]{"A","B","C"}), new int[]{3,1,2});
    Vote v6 = new Vote(new CandidateList(new String[]{"A","B","C"}), new int[]{3,2,1});
    assertEquals(true,v1.validateVote());
    assertEquals(true,v2.validateVote());
    assertEquals(true,v3.validateVote());
    assertEquals(true,v4.validateVote());
    assertEquals(true,v5.validateVote());
    assertEquals(true,v6.validateVote());
  }
  @Test (timeout=2000) public void vote_validate_7 (){
    // each has rank issues
    Vote v1 = new Vote(new CandidateList(new String[]{"A","B","C"}), new int[]{1,1,3});
    Vote v2 = new Vote(new CandidateList(new String[]{"A","B","C"}), new int[]{1,4,2});
    Vote v3 = new Vote(new CandidateList(new String[]{"A","B","C"}), new int[]{0,1,2});
    Vote v4 = new Vote(new CandidateList(new String[]{"A","B","C"}), new int[]{2,2,2});
    assertEquals(false,v1.validateVote());
    assertEquals(false,v2.validateVote());
    assertEquals(false,v3.validateVote());
    assertEquals(false,v4.validateVote());
  }
  @Test (timeout=2000) public void vote_str_1 (){
    Vote v = new Vote(new CandidateList(new String[]{"A","B","C"}), new int[]{1,2,3});
    assertEquals("{A:1, B:2, C:3}", v.toString());
  }
  @Test (timeout=2000) public void vote_str_2 (){
    Vote v1 = new Vote(new CandidateList(new String[]{"X","Z","Y"}), new int[]{1,2,3});
    Vote v2 = new Vote(new CandidateList(new String[]{"X","Z","Y"}), new int[]{1,3,2});
    Vote v3 = new Vote(new CandidateList(new String[]{"X","Z","Y"}), new int[]{2,1,3});
    Vote v4 = new Vote(new CandidateList(new String[]{"X","Z","Y"}), new int[]{2,3,1});
    Vote v5 = new Vote(new CandidateList(new String[]{"X","Z","Y"}), new int[]{3,1,2});
    Vote v6 = new Vote(new CandidateList(new String[]{"X","Z","Y"}), new int[]{3,2,1});
    assertEquals("{X:1, Z:2, Y:3}", v1.toString());
    assertEquals("{X:1, Z:3, Y:2}", v2.toString());
    assertEquals("{X:2, Z:1, Y:3}", v3.toString());
    assertEquals("{X:2, Z:3, Y:1}", v4.toString());
    assertEquals("{X:3, Z:1, Y:2}", v5.toString());
    assertEquals("{X:3, Z:2, Y:1}", v6.toString());
  }
  @Test (timeout=2000) public void vote_str_3 (){
    Vote v1 = new Vote(new CandidateList(new String[]{}), new int[]{});
    assertEquals("{}",v1.toString());
  }
  @Test (timeout=2000) public void vote_str_4 (){
    Vote v1 = new Vote(new CandidateList(new String[]{}), new int[]{});
    assertEquals("{}",v1.toString());
  }
  @Test (timeout=2000) public void vote_str_5 (){
    Vote v1 = new Vote(new CandidateList(new String[]{"grape","lemon","lime"}), new int[]{2,1,3});
    assertEquals("{grape:2, lemon:1, lime:3}",v1.toString());
  }
}