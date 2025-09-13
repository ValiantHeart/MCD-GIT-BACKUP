// Example of using unit tests for this programming assignment.  This is
// partially how your code will be graded.  Later in the class we will
// write our own unit tests.  To run them on the command line, make
// sure that the junit-cs211.jar is in the project directory.
// 
// $> javac -cp .:junit-cs211.jar VotingMachineTest.java   #compile
// $> java -cp .:junit-cs211.jar VotingMachineTest         #run tests
// 
// On windows replace : with ; (colon with semicolon)
// $> javac -cp .;junit-cs211.jar VotingMachineTest.java   #compile
// $> java -cp .;junit-cs211.jar VotingMachineTest         #run tests

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class VotingMachineTest {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("VotingMachineTest");
  }
  
  // assertEquals(expected, actual)
  // assertTrue(booleanExpression)
  // assertFalse(booleanExpression)
  
  public void compareCL(CandidateList a, CandidateList b){
    if (a.size() != b.size()) {
      fail(String.format("candidate lists weren't the same size: %s vs %s.",a.size(),b.size()));
    }
    int size = a.size();
    for (int i=0; i<size; i++){
      if ( ! a.get(i).equals(b.get(i))) {
        fail(String.format("different items in CandidateList values at index %s: \"%s\" vs \"%s\".", i, a.get(i), b.get(i)));
      }
    }
  }
  
  public void compareVL(VoteList a, VoteList b){
    if (a.size() != b.size()) {
      fail(String.format("vote lists weren't the same size: %s vs %s. ",a.size(), b.size()));
    }
    int size = a.size();
    for (int i=0; i<size; i++){
      if ( ! a.get(i).equals(b.get(i))) {
        fail(String.format("different items in VoteList values at index %s: \"%s\" vs \"%s\". (does your .equals() method work on Votes yet?)", i, a.get(i), b.get(i)));
      }
    }
  }
  
  public void compareLogStrings(String[] as, String[] bs){
    String shows = "=====Expected=====\n";
    for(String s : as){
      shows += s+"\n";
    }
    shows+="=====Received=====\n";
    for(String s : bs){
      shows += s+"\n";
    }
    shows += "==================\n";
   if(as.length != bs.length) {
     fail(String.format("Logs don't have same length: expected %s, got %s.\n%s",as.length, bs.length, shows));
   }
   int size = as.length;
   for (int i=0; i<size; i++){
     if ( ! as[i].equals(bs[i])) {
       fail(String.format("different messages in logs, at index %s: \"%s\" vs \"%s\".\n%s",i,as[i],bs[i],shows));
     }
   }
  }
  @Test (timeout=2000) public void vm_0 (){
    // spec example.
    CandidateList cl = new CandidateList(new String[]{"Francis","Claire","Heather","Viktor"});
    VoteList vs = new VoteList(new Vote[]{
        new Vote(cl,new int[]{1,4,3,2}),
        new Vote(cl,new int[]{2,1,3,4}),
        new Vote(cl,new int[]{3,2,1,4}),
        new Vote(cl,new int[]{3,2,1,4}),
        new Vote(cl,new int[]{2,1,3,4}),
        new Vote(cl,new int[]{1,3,2,4}),
        new Vote(cl,new int[]{1,2,3,4}),
        new Vote(cl,new int[]{3,2,1,4}),
        new Vote(cl,new int[]{2,3,1,4}),
        new Vote(cl,new int[]{2,4,3,1}),
        new Vote(cl,new int[]{1,2,3,4}),
        new Vote(cl,new int[]{2,3,1,4}),
    });
    VotingMachine vm = new VotingMachine("IRV master of the universe",cl,vs);
    
    String[] expected = {
      "BEGIN LOG",
      "TOTAL VOTES: 12",
      "MAJORITY: 7",
      "ROUND 1: Francis:4, Claire:2, Heather:5, Viktor:1",
      "DROP: Viktor",
      "ROUND 2: Francis:5, Claire:2, Heather:5",
      "DROP: Claire",
      "ROUND 3: Francis:7, Heather:5",
      "WINNER: Francis:7",
      "END LOG"
    };
    
    Log ans = vm.tabulate();
    compareLogStrings(expected, ans.read());
  }
    
  
  @Test (timeout=2000) public void vm_1 (){
    VotingMachine vm = new VotingMachine("class clown");
    assertEquals("class clown",vm.reportOffice());
  }
  
  
  
  @Test (timeout=2000) public void vm_2 (){
    VotingMachine vm = new VotingMachine("Class Clown");
    assertEquals("Class Clown",vm.reportOffice());
    CandidateList expected = new CandidateList();
    compareCL(expected, vm.reportCandidates());
  }
  
  
  
  @Test (timeout=2000) public void vm_3 (){
    VotingMachine vm = new VotingMachine("Class Clown");
    assertEquals("Class Clown",vm.reportOffice());
    CandidateList expected = new CandidateList();
    VoteList expVL = new VoteList();
    compareVL(expVL, vm.reportVotes());
  }
  
  
  
  @Test (timeout=2000) public void vm_4 (){
    VotingMachine vm = new VotingMachine("Class Clown");
    assertEquals("Class Clown",vm.reportOffice());
    CandidateList expCL1 = vm.reportCandidates();
    CandidateList expCL2 = vm.reportCandidates();
    VoteList expVL1 = vm.reportVotes();
    VoteList expVL2 = vm.reportVotes();
    // check that we're not getting aliases with the internal one (by comparing a couple of supposed copies).
    assertTrue(expCL1 != expCL2);
    assertTrue(expVL1 != expVL2);
  }
  
  
  
  @Test (timeout=2000) public void vm_5 (){
    CandidateList cl = new CandidateList(new String[]{"A","C","X","Z"});
    VotingMachine vm = new VotingMachine("Class Clown",cl);
    CandidateList expCL1 = vm.reportCandidates();
    CandidateList expCL2 = vm.reportCandidates();
    VoteList expVL1 = vm.reportVotes();
    VoteList expVL2 = vm.reportVotes();
    // check that we're not getting aliases with the internal one (by comparing a couple of supposed copies).
    assertTrue(expCL1 != expCL2);
    assertTrue(expVL1 != expVL2);
    // check the office.
    assertEquals("Class Clown",vm.reportOffice());
    // check the CandidateList.
    compareCL(cl, expCL1);
  }
  
  
  
  @Test (timeout=2000) public void vm_6 (){
    CandidateList cl = new CandidateList(new String[]{"A","C","X","Z"});
    VotingMachine vm = new VotingMachine("Class Clown",cl);
    Vote v1 = new Vote(cl,new int[]{1,3,2,4});
    VoteList      expVL = new VoteList(new Vote[]{v1});
    CandidateList expCL = new CandidateList(new String[]{"A","C","X","Z"});
    
    // make modifications
    vm.addVote(v1);
    CandidateList gotCL = vm.reportCandidates();
    VoteList      gotVL = vm.reportVotes();
    
    // check the internal state.
    assertEquals("Class Clown",vm.reportOffice());
    compareCL(expCL, expCL);
    compareVL(expVL, gotVL);
  }
  
  
  
  @Test (timeout=2000) public void vm_7 (){
    CandidateList cl = new CandidateList(new String[]{"A","C","X","Z"});
    VotingMachine vm = new VotingMachine("Class Clown",cl);
    Vote[] vs = {
      new Vote(cl,new int[]{1,3,2,4}),
      new Vote(cl,new int[]{1,2,3,4}),
      new Vote(cl,new int[]{2,3,4,1}),
      new Vote(cl,new int[]{3,4,1,2}),
      new Vote(cl,new int[]{1,3,2,4})
    };
    VoteList      expVL = new VoteList(vs);
    CandidateList expCL = new CandidateList(new String[]{"A","C","X","Z"});
    
    // make modifications
    for (Vote v : vs){
      vm.addVote(v);
    }
    CandidateList gotCL = vm.reportCandidates();
    VoteList      gotVL = vm.reportVotes();
    
    // check the internal state.
    assertEquals("Class Clown",vm.reportOffice());
    compareCL(expCL, gotCL);
    compareVL(expVL, gotVL);
  }
  
  
  
  @Test (timeout=2000) public void vm_8 (){
    CandidateList cl = new CandidateList(new String[]{"A","C","X","Z"});
    Vote[] vs = {
      new Vote(cl,new int[]{1,3,2,4}),
      new Vote(cl,new int[]{1,2,3,4}),
      new Vote(cl,new int[]{2,3,4,1}),
      new Vote(cl,new int[]{3,4,1,2}),
      new Vote(cl,new int[]{1,3,2,4})
    };
    VoteList      vl = new VoteList(vs);
    
    // using the three-argument constructor
    VotingMachine vm = new VotingMachine("Class Clown",cl,vl);
    
    CandidateList expCL = new CandidateList(new String[]{"A","C","X","Z"});
    Vote[] vs2 = {
      new Vote(cl,new int[]{1,3,2,4}),
      new Vote(cl,new int[]{1,2,3,4}),
      new Vote(cl,new int[]{2,3,4,1}),
      new Vote(cl,new int[]{3,4,1,2}),
      new Vote(cl,new int[]{1,3,2,4}),
      new Vote(cl,new int[]{4,3,2,1})   // extra vote!
      
    };
    VoteList      expVL = new VoteList(vs2);
    
    // make modifications
    vm.addVote(new Vote(cl, new int[]{4,3,2,1}));
    
    CandidateList gotCL = vm.reportCandidates();
    VoteList      gotVL = vm.reportVotes();
    
    // check the internal state.
    assertEquals("Class Clown",vm.reportOffice());
    compareCL(expCL, gotCL);
    compareVL(expVL, gotVL);
  }
  
  
  
  @Test (timeout=2000) public void vm_9 (){
    // test addVote.
    
    CandidateList cl = new CandidateList(new String[]{"R","S","T"});
    Vote v1 = new Vote(cl,new int[]{1,3,2});
    Vote v2 = new Vote(cl,new int[]{1,2,3});
    Vote v3 = new Vote(cl,new int[]{2,3,1});
    Vote v4 = new Vote(cl,new int[]{3,1,2});
    Vote v5 = new Vote(cl,new int[]{1,3,2});
    
    VotingMachine vm = new VotingMachine("Class Clown",cl);
    
    compareVL(new VoteList(), vm.reportVotes());
    
    vm.addVote(v1);
    compareVL(new VoteList(new Vote[]{v1}), vm.reportVotes());
    
    vm.addVote(v2);
    compareVL(new VoteList(new Vote[]{v1,v2}), vm.reportVotes());
    
    vm.addVote(v3);
    compareVL(new VoteList(new Vote[]{v1,v2,v3}), vm.reportVotes());
    
    vm.addVote(v4);
    compareVL(new VoteList(new Vote[]{v1,v2,v3,v4}), vm.reportVotes());
    
    vm.addVote(v5);
    compareVL(new VoteList(new Vote[]{v1,v2,v3,v4,v5}), vm.reportVotes());
  }
  
  
  
  @Test (timeout=2000) public void vm_10 (){
    VotingMachine vm = new VotingMachine("assistant to the branch manager");
    
    try {
      vm.addVote(null);
      fail("shouldn't be able to successfully add a null vote to a VotingMachine.");
    }
    catch (RuntimeException e){}
  }
  
  
  
  @Test (timeout=2000) public void vm_11 (){
    // test addVote.
    CandidateList cl = new CandidateList(new String[]{"E","F","G"});
    VotingMachine vm = new VotingMachine("Class Clown");
    
    compareCL(new CandidateList(),vm.reportCandidates());
    
    vm.addCandidate("E");
    compareCL(new CandidateList(new String[]{"E"}), vm.reportCandidates());
    vm.addCandidate("F");
    compareCL(new CandidateList(new String[]{"E","F"}), vm.reportCandidates());
    vm.addCandidate("G");
    compareCL(new CandidateList(new String[]{"E","F","G"}), vm.reportCandidates());
  }
  
  
  
  @Test (timeout=2000) public void vm_12 (){
    VotingMachine vm = new VotingMachine("assistant to the branch manager");
    
    try {
      vm.addCandidate(null);
      fail("shouldn't be able to successfully add a null candidate to a VotingMachine.");
    }
    catch (RuntimeException e){}
  }
  
  
  
  
  // copy log
  
  @Test (timeout=2000) public void vm_13 (){
    VotingMachine vm = new VotingMachine("King Taco");
    Log got1 = vm.copyLog();
    Log got2 = vm.copyLog();
    
    // must have different ID each time - each is another copy.
    assertTrue(got1 != got2);
  }
  
  
  
  // validateVotes
  @Test (timeout=2000) public void vm_14 (){
    CandidateList cl = new CandidateList(new String[]{"R","S","T"});
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,3,2}),
        new Vote(cl,new int[]{1,2,3}),
        new Vote(cl,new int[]{2,3,1}),
        new Vote(cl,new int[]{3,1,2}),
        new Vote(cl,new int[]{1,3,2})
    });
    VotingMachine vm = new VotingMachine("Class Clown",cl,vs);    
    assertEquals(true,vm.validateVotes());
  }
  
  
  
  @Test (timeout=2000) public void vm_15 (){
    CandidateList cl = new CandidateList(new String[]{"R","S","T"});
    VoteList vs = new VoteList(new Vote[]{
      null,
        new Vote(cl,new int[]{3,1,2}),
        new Vote(cl,new int[]{1,3,2})
    });
    VotingMachine vm = new VotingMachine("Class Clown",cl,vs);    
    // there's a null in there.
    assertEquals(false,vm.validateVotes());
  }
  
  
  
  @Test (timeout=2000) public void vm_16 (){
    CandidateList cl = new CandidateList(new String[]{"R","S","T"});
    VoteList vs = new VoteList(new Vote[]{
      new Vote(null,new int[]{3,1,2}),
        new Vote(cl,new int[]{3,1,2}),
        new Vote(cl,new int[]{6,7,8})
    });
    VotingMachine vm = new VotingMachine("Class Clown",cl,vs);    
    // some votes won't self-validate.
    assertEquals(false,vm.validateVotes());
  }
  
  
  
  @Test (timeout=2000) public void vm_17 (){
    CandidateList cl = new CandidateList(new String[]{"R","S","T"});
    VoteList vs = new VoteList(new Vote[]{
      new Vote(new CandidateList(new String[]{"R","S","T"}),new int[]{3,1,2}),
        new Vote(new CandidateList(new String[]{"R","S","T"}),new int[]{3,1,2}),
        // different candidates order below won't validate with others...
        new Vote(new CandidateList(new String[]{"R","T","S"}),new int[]{1,3,2})
    });
    VotingMachine vm = new VotingMachine("Class Clown",cl,vs);    
    // there's a null as a vote's candidate list.
    assertEquals(false,vm.validateVotes());
  }
  
  
  
  
  // tally
  @Test (timeout=2000) public void vm_18 (){
    CandidateList cl = new CandidateList(new String[]{"R","S","T"});
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3}),
        new Vote(cl,new int[]{1,2,3}),
        new Vote(cl,new int[]{1,2,3}),
        new Vote(cl,new int[]{2,1,3}),
        new Vote(cl,new int[]{2,1,3}),
        new Vote(cl,new int[]{3,2,1})
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);    
    int[] exp = {3,2,1};
    assertArrayEquals(exp,vm.tally(cl));
  }
  
  
  
  @Test (timeout=2000) public void vm_19 (){
    CandidateList cl = new CandidateList(new String[]{"R","S","T"});
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3}),
        new Vote(cl,new int[]{1,2,3}),
        new Vote(cl,new int[]{1,2,3}),
        new Vote(cl,new int[]{2,1,3}),
        new Vote(cl,new int[]{2,1,3}),
        new Vote(cl,new int[]{3,2,1})
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);    
    int[] exp = {5,1};
    assertArrayEquals(exp,vm.tally(new CandidateList(new String[]{"R","T"})));
  }
  
  
  
  @Test (timeout=2000) public void vm_20 (){
    CandidateList cl = new CandidateList(new String[]{"R","S","T"});
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3}),
        new Vote(cl,new int[]{1,2,3}),
        new Vote(cl,new int[]{1,2,3}),
        new Vote(cl,new int[]{2,1,3}),
        new Vote(cl,new int[]{2,1,3}),
        new Vote(cl,new int[]{3,2,1}) // T not available; this vote goes to S.
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);    
    int[] exp = {3,3};
    assertArrayEquals(exp,vm.tally(new CandidateList(new String[]{"R","S"})));
  }
  
  
  
  @Test (timeout=2000) public void vm_21 (){
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    VoteList vs = new VoteList(new Vote[]{
      
      new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{4,2,3,1,5}),
        new Vote(cl,new int[]{4,2,3,5,1})
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);    
    
    CandidateList current = new CandidateList(new String[]{"A","B","C","D","E"});
    int[] exp = {3,2,1,1,1};
    assertArrayEquals(exp,vm.tally(current));
    
    exp = new int[]{3,5};
    current = new CandidateList(new String[]{"A","B"});
    assertArrayEquals(exp,vm.tally(current));
    
    // note: this specific sequence wouldn't occur in a regular rounds calculation.
    exp = new int[]{5,1,1,1};
    current = new CandidateList(new String[]{"A","C","D","E"});
    assertArrayEquals(exp,vm.tally(current));
    
    // note: this specific sequence wouldn't occur in a regular rounds calculation.
    exp = new int[]{6,1,1};
    current = new CandidateList(new String[]{"B","D","E"});
    assertArrayEquals(exp,vm.tally(current));
  }
  
  
  
  @Test (timeout=2000) public void vm_22 (){
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    VoteList vs = new VoteList(new Vote[]{
      
      new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5})
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);    
    
    CandidateList current = new CandidateList(new String[]{"A","B","C","D","E"});
    int[] exp = {3,3,3,0,0};
    assertArrayEquals(exp,vm.tally(current));
    
    exp = new int[]{3,3,3};
    current = new CandidateList(new String[]{"A","B","C"});
    assertArrayEquals(exp,vm.tally(current));
    
    exp = new int[]{3,6};
    current = new CandidateList(new String[]{"A","B"});
    assertArrayEquals(exp,vm.tally(current));
  }
  
  
  
  @Test (timeout=2000) public void vm_23 (){
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{3,2,1,4,5})
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);    
    
    CandidateList current = new CandidateList(new String[]{"A","B","C","D","E"});
    int[] exp = {0,0,1,0,0};
    assertArrayEquals(exp,vm.tally(current));
  }
  
  
  
  @Test (timeout=2000) public void vm_24 (){
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E","F","G","H","I","J","K"});
    VoteList vs = new VoteList(new Vote[]{
      
      new Vote(cl,new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11}),
        new Vote(cl,new int[]{ 2, 3, 4, 5, 6, 7, 8, 9,10,11, 1}),
        new Vote(cl,new int[]{ 3, 4, 5, 6, 7, 8, 9,10,11, 1, 2}),
        new Vote(cl,new int[]{ 4, 5, 6, 7, 8, 9,10,11, 1, 2, 3}),
        new Vote(cl,new int[]{ 5, 6, 7, 8, 9,10,11, 1, 2, 3, 4}),
        new Vote(cl,new int[]{ 6, 7, 8, 9,10,11, 1, 2, 3, 4, 5}),
        new Vote(cl,new int[]{ 7, 8, 9,10,11, 1, 2, 3, 4, 5, 6}),
        new Vote(cl,new int[]{ 8, 9,10,11, 1, 2, 3, 4, 5, 6, 7}),
        new Vote(cl,new int[]{ 9,10,11, 1, 2, 3, 4, 5, 6, 7, 8}),
        new Vote(cl,new int[]{10,11, 1, 2, 3, 4, 5, 6, 7, 8, 9}),
        new Vote(cl,new int[]{11, 1, 2, 3, 4, 5, 6, 7, 8, 9,10})
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);    
    
    CandidateList current = new CandidateList(new String[]{"A","B","C","D","E","F","G","H","I","J","K"});
    int[] exp = {1,1,1,1,1,1,1,1,1,1,1};
    assertArrayEquals(exp,vm.tally(current));
    
    exp = new int[]{2,1,1,1,1,1,1,1,1,1};
    current = new CandidateList(new String[]{"A","B","C","D","E","F","G","H","I","J"});
    assertArrayEquals(exp,vm.tally(current));
    
    exp = new int[]{3,1,1,1,1,1,1,1,1};
    current = new CandidateList(new String[]{"A","B","C","D","E","F","G","H","I"});
    assertArrayEquals(exp,vm.tally(current));
    
    exp = new int[]{4,1,1,1,1,1,1,1};
    current = new CandidateList(new String[]{"A","B","C","D","E","F","G","H"});
    assertArrayEquals(exp,vm.tally(current));
    
    exp = new int[]{5,1,1,1,1,1,1};
    current = new CandidateList(new String[]{"A","B","C","D","E","F","G"});
    assertArrayEquals(exp,vm.tally(current));
    
    exp = new int[]{6,1,1,1,1,1};
    current = new CandidateList(new String[]{"A","B","C","D","E","F"});
    assertArrayEquals(exp,vm.tally(current));
    
    exp = new int[]{7,1,1,1,1};
    current = new CandidateList(new String[]{"A","B","C","D","E"});
    assertArrayEquals(exp,vm.tally(current));
    
    exp = new int[]{8,1,1,1};
    current = new CandidateList(new String[]{"A","B","C","D"});
    assertArrayEquals(exp,vm.tally(current));
    
    exp = new int[]{9,1,1};
    current = new CandidateList(new String[]{"A","B","C"});
    assertArrayEquals(exp,vm.tally(current));
    
    exp = new int[]{10,1};
    current = new CandidateList(new String[]{"A","B"});
    assertArrayEquals(exp,vm.tally(current));
    
    exp = new int[]{11};
    current = new CandidateList(new String[]{"A"});
    assertArrayEquals(exp,vm.tally(current));
  }
  
  @Test (timeout=2000) public void vm_25 (){
    CandidateList cl = new CandidateList(new String[]{"A","B","C"});
    VoteList vs = new VoteList(new Vote[]{ });
    VotingMachine vm = new VotingMachine("role",cl,vs);
    
    try {
      vm.tally(null);
      fail("tally shouldn't accept null viables list.");
    }
    catch (RuntimeException e){}
    
    try {
      vm.tally(new CandidateList(new String[]{}));
      fail("tally shouldn't accept length-zero viables list.");
    }
    catch (RuntimeException e){}
  }
  
  @Test (timeout=2000) public void vm_26 (){
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(new CandidateList(new String[]{"R","S","T","U","V"}),new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5})
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);    
    
    try{
      vm.tally(cl);
      fail("a vote here doesn't apply (wouldn't pass validation but we're testing tally right now).");
    }
    catch (RuntimeException e) {}
  }
  
  // thinHerd
  @Test (timeout=2000) public void vm_27 (){
    // all way tie
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    // everybody is getting two first-place votes.
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        
        new Vote(cl,new int[]{4,3,2,1,5}),
        new Vote(cl,new int[]{4,3,2,1,5}),
        
        new Vote(cl,new int[]{5,4,3,2,1}),
        new Vote(cl,new int[]{5,4,3,2,1}),
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);
    
    CandidateList viables = new CandidateList(new String[]{"A","B","C","D","E"});
    int[] tallies = {2,2,2,2,2};
    CandidateList expected = new CandidateList(new String[]{"A","B","C","D","E"});
    CandidateList ans = vm.thinHerd(viables, tallies);
    compareCL(expected, ans);
  }
  
  
  
  @Test (timeout=2000) public void vm_28 (){
    // drop one lowest
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    // everybody but C is getting two first place votes; C gets one.
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        
        new Vote(cl,new int[]{3,2,1,4,5}),
        // not this time! This voter overslept and missed voting. new Vote(cl,new int[]{3,2,1,4,5}),
        
        new Vote(cl,new int[]{4,3,2,1,5}),
        new Vote(cl,new int[]{4,3,2,1,5}),
        
        new Vote(cl,new int[]{5,4,3,2,1}),
        new Vote(cl,new int[]{5,4,3,2,1}),
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);
    
    CandidateList viables = new CandidateList(new String[]{"A","B","C","D","E"});
    int[] tallies = {2,2,1,2,2};
    CandidateList expected = new CandidateList(new String[]{"A","B","D","E"});
    CandidateList ans = vm.thinHerd(viables, tallies);
    compareCL(expected, ans);
  }
  
  
  
  @Test (timeout=2000) public void vm_29 (){
    // drop two lowest at once (tied last)
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        
        new Vote(cl,new int[]{2,1,3,4,5}),
        // NOPE new Vote(cl,new int[]{2,1,3,4,5}),
        
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        
        // ZZZ new Vote(cl,new int[]{4,3,2,1,5}),
        new Vote(cl,new int[]{4,3,2,1,5}),
        
        new Vote(cl,new int[]{5,4,3,2,1}),
        new Vote(cl,new int[]{5,4,3,2,1}),
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);
    
    CandidateList viables = new CandidateList(new String[]{"A","B","C","D","E"});
    int[] tallies = {2,1,2,1,2};
    CandidateList expected = new CandidateList(new String[]{"A","C","E"});
    CandidateList ans = vm.thinHerd(viables, tallies);
    compareCL(expected, ans);
  }
  
  
  
  @Test (timeout=2000) public void vm_30 (){
    // drop three lowest at once (tied last)
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        
        new Vote(cl,new int[]{3,2,1,4,5}),
        //new Vote(cl,new int[]{3,2,1,4,5}),
        
        new Vote(cl,new int[]{4,3,2,1,5}),
        //new Vote(cl,new int[]{4,3,2,1,5}),
        
        new Vote(cl,new int[]{5,4,3,2,1}),
        //new Vote(cl,new int[]{5,4,3,2,1}),
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);
    
    CandidateList viables = new CandidateList(new String[]{"A","B","C","D","E"});
    int[] tallies = {2,2,1,1,1};
    CandidateList expected = new CandidateList(new String[]{"A","B"});
    CandidateList ans = vm.thinHerd(viables, tallies);
    compareCL(expected, ans);
  }
  
  
  
  @Test (timeout=2000) public void vm_31 (){
    // drop N-1 lowest at once (note, remaining candidate wasn't yet a winner)
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        
        new Vote(cl,new int[]{4,3,2,1,5}),
        new Vote(cl,new int[]{4,3,2,1,5}),
        
        new Vote(cl,new int[]{5,4,3,2,1}),
        new Vote(cl,new int[]{5,4,3,2,1}),
        new Vote(cl,new int[]{5,4,3,2,1}),
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);
    
    CandidateList viables = new CandidateList(new String[]{"A","B","C","D","E"});
    int[] tallies = {2,2,2,2,3};
    CandidateList expected = new CandidateList(new String[]{"E"});
    CandidateList ans = vm.thinHerd(viables, tallies);
    compareCL(expected, ans);
  }
  
  
  
  @Test (timeout=2000) public void vm_32 (){
    // pass only the winner
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        
        // 9 here
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        
        new Vote(cl,new int[]{4,3,2,1,5}),
        new Vote(cl,new int[]{4,3,2,1,5}),
        
        new Vote(cl,new int[]{5,4,3,2,1}),
        new Vote(cl,new int[]{5,4,3,2,1}),
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);
    
    CandidateList viables = new CandidateList(new String[]{"A","B","C","D","E"});
    int[] tallies = {2,2,9,2,2};
    CandidateList expected = new CandidateList(new String[]{"C"});
    CandidateList ans = vm.thinHerd(viables, tallies);
    compareCL(expected, ans);
  }
  
  
  
  @Test (timeout=2000) public void vm_33 (){
    // pass two tied for first
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        
        new Vote(cl,new int[]{2,1,3,4,5}), // 4
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        
        new Vote(cl,new int[]{3,2,1,4,5}), // 4
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        
        new Vote(cl,new int[]{4,3,2,1,5}),
        new Vote(cl,new int[]{4,3,2,1,5}),
        
        new Vote(cl,new int[]{5,4,3,2,1}),
        new Vote(cl,new int[]{5,4,3,2,1}),
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);
    
    CandidateList viables = new CandidateList(new String[]{"A","B","C","D","E"});
    int[] tallies = {2,4,4,2,2};
    CandidateList expected = new CandidateList(new String[]{"B","C"});
    CandidateList ans = vm.thinHerd(viables, tallies);
    compareCL(expected, ans);
  }
  
  
  
  
  @Test (timeout=2000) public void vm_34 (){
    // relying on seconds, drop one lowest candidate.
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    // D won't be in the viables list.
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        
        new Vote(cl,new int[]{3,2,1,4,5}),
        
        new Vote(cl,new int[]{4,3,2,1,5}),
        new Vote(cl,new int[]{4,3,2,1,5}),
        new Vote(cl,new int[]{4,3,2,1,5}),
        
        new Vote(cl,new int[]{5,4,3,2,1}),
        new Vote(cl,new int[]{5,4,3,2,1}),
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);
    
    CandidateList viables = new CandidateList(new String[]{"A","B","C","E"});
    int[] tallies = {4,4,4,2};
    CandidateList expected = new CandidateList(new String[]{"A","B","C"});
    CandidateList ans = vm.thinHerd(viables, tallies);
    compareCL(expected, ans);
  }
  
  
  
  
  @Test (timeout=2000) public void vm_35 (){
    // relying on thirds, drop two lowest candidates.
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    // everybody but C is getting two first place votes; C gets one.
    // A and B won't be viables when we thin the herd.
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        
        new Vote(cl,new int[]{3,2,1,4,5}),
        
        new Vote(cl,new int[]{4,3,2,1,5}),
        new Vote(cl,new int[]{4,3,2,1,5}),
        new Vote(cl,new int[]{4,3,2,1,5}),
        
        new Vote(cl,new int[]{5,4,3,2,1}),
        new Vote(cl,new int[]{5,4,3,2,1}),
        new Vote(cl,new int[]{5,4,3,2,1}),
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);
    
    CandidateList viables = new CandidateList(new String[]{"C","D","E"});
    int[] tallies = {5,3,3};
    CandidateList expected = new CandidateList(new String[]{"C"});
    CandidateList ans = vm.thinHerd(viables, tallies);
    compareCL(expected, ans);
  }
  
  
  
  
  // isCompleteTie
  
  @Test (timeout=2000) public void vm_36 (){
    VotingMachine vm = new VotingMachine("role",new CandidateList(new String[]{"A","B","C"}));
    assertEquals(true , vm.isCompleteTie(new int[]{2,2,2}));
    assertEquals(true , vm.isCompleteTie(new int[]{200,200,200}));
    assertEquals(true , vm.isCompleteTie(new int[]{30,30,30}));
  }
  
  @Test (timeout=2000) public void vm_37 (){
    VotingMachine vm = new VotingMachine("role",new CandidateList(new String[]{"A","B","C"}));
    assertEquals(false, vm.isCompleteTie(new int[]{50,40,45}));
    assertEquals(false, vm.isCompleteTie(new int[]{10,10,11}));
  }
  
  @Test (timeout=2000) public void vm_38 (){
    VotingMachine vm = new VotingMachine("role",new CandidateList(new String[]{"A","B","C","D","E","F"}));
    assertEquals(false, vm.isCompleteTie(new int[]{10,10,10, 9,10,10}));
    assertEquals(false, vm.isCompleteTie(new int[]{40,40,99,98,99,97}));
    assertEquals(false, vm.isCompleteTie(new int[]{ 5, 5, 5,20, 5, 5}));
    assertEquals(false, vm.isCompleteTie(new int[]{13,15,14,12,16,17}));
  }
  
  
  
  
  // roundResultString. Since this only uses its parameters, we're going to use a pretty sparse VotingMachine.
  
  @Test (timeout=2000) public void vm_39 (){
    VotingMachine vm = new VotingMachine("role");
    CandidateList viables = new CandidateList(new String[]{"A","B","C"});
    int[] tallies = {5,2,4};
    String expected = "A:5, B:2, C:4";
    assertEquals(expected, vm.roundResultString(viables,tallies));
  }
  
  
  @Test (timeout=2000) public void vm_40 (){
    VotingMachine vm = new VotingMachine("role");
    CandidateList viables = new CandidateList(new String[]{"A","B","C","D","E"});
    int[] tallies = {100,200,500,400,311};
    String expected = "A:100, B:200, C:500, D:400, E:311";
    assertEquals(expected, vm.roundResultString(viables,tallies));
  }
  
  
  
  @Test (timeout=2000) public void vm_41 (){
    VotingMachine vm = new VotingMachine("role");
    CandidateList viables = new CandidateList(new String[]{"A","B","C","D","E"});
    int[] tallies = {100,2,0,0,31};
    String expected = "A:100, B:2, C:0, D:0, E:31";
    assertEquals(expected, vm.roundResultString(viables,tallies));
  }
  
  
  
  @Test (timeout=2000) public void vm_42 (){
    VotingMachine vm = new VotingMachine("role");
    CandidateList viables = new CandidateList(new String[]{"Alice","Bob","Carol"});
    int[] tallies = {65,66,67};
    String expected = "Alice:65, Bob:66, Carol:67";
    assertEquals(expected, vm.roundResultString(viables,tallies));
  }
  
  
  
  
  
  @Test (timeout=2000) public void vm_43 (){
    VotingMachine vm = new VotingMachine("role");
    CandidateList viables = new CandidateList(new String[]{"Alice"});
    int[] tallies = {101};
    String expected = "Alice:101";
    assertEquals(expected, vm.roundResultString(viables,tallies));
  }
  
  
  
  
  // tabulate
  
  // Finally! The big one. We will set up some situations and need to see the correct log messages generated.
  
  @Test (timeout=2000) public void vm_44 (){
    // all way tie
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    // everybody is getting two first-place votes.
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        
        new Vote(cl,new int[]{4,3,2,1,5}),
        new Vote(cl,new int[]{4,3,2,1,5}),
        
        new Vote(cl,new int[]{5,4,3,2,1}),
        new Vote(cl,new int[]{5,4,3,2,1}),
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);
    
    String[] expected = {
      "BEGIN LOG",
      "TOTAL VOTES: 10",
      "MAJORITY: 6",
      "ROUND 1: A:2, B:2, C:2, D:2, E:2",
      "TIE(5): A, B, C, D, E",
      "END LOG"
    };
    
    Log ans = vm.tabulate();
    compareLogStrings(expected, ans.read());
  }
  
  
  
  
  @Test (timeout=2000) public void vm_45 (){
    // drop one lowest
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    // everybody but C is getting two first place votes; C gets one.
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        
        new Vote(cl,new int[]{3,2,1,4,5}),
        // not this time! This voter overslept and missed voting. new Vote(cl,new int[]{3,2,1,4,5}),
        
        new Vote(cl,new int[]{4,3,2,1,5}),
        new Vote(cl,new int[]{4,3,2,1,5}),
        
        new Vote(cl,new int[]{5,4,3,2,1}),
        new Vote(cl,new int[]{5,4,3,2,1}),
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);
    
    String[] expected = {
      "BEGIN LOG",
      "TOTAL VOTES: 9",
      "MAJORITY: 5",
      "ROUND 1: A:2, B:2, C:1, D:2, E:2",
      "DROP: C",
      "ROUND 2: A:2, B:3, D:2, E:2",
      "DROP: A",
      "DROP: D",
      "DROP: E",
      "ROUND 3: B:9",
      "WINNER: B:9",
      "END LOG"
    };
    
    Log ans = vm.tabulate();
    compareLogStrings(expected, ans.read());
  }
  
  
  
  @Test (timeout=2000) public void vm_46 (){
    // drop two lowest at once (tied last)
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        
        new Vote(cl,new int[]{2,1,3,4,5}),
        // NOPE new Vote(cl,new int[]{2,1,3,4,5}),
        
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        
        // ZZZ new Vote(cl,new int[]{4,3,2,1,5}),
        new Vote(cl,new int[]{4,3,2,1,5}),
        
        new Vote(cl,new int[]{5,4,3,2,1}),
        new Vote(cl,new int[]{5,4,3,2,1}),
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);
    
    String[] expected = {
      "BEGIN LOG",
      "TOTAL VOTES: 8",
      "MAJORITY: 5",
      "ROUND 1: A:2, B:1, C:2, D:1, E:2",
      "DROP: B",
      "DROP: D",
      "ROUND 2: A:3, C:3, E:2",
      "DROP: E",
      "ROUND 3: A:3, C:5",
      "WINNER: C:5",
      "END LOG"
    };
    
    Log ans = vm.tabulate();
    compareLogStrings(expected, ans.read());
  }
  
  
  
  @Test (timeout=2000) public void vm_47 (){
    // drop three lowest at once (tied last)
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        
        new Vote(cl,new int[]{3,2,1,4,5}),
        //new Vote(cl,new int[]{3,2,1,4,5}),
        
        new Vote(cl,new int[]{4,3,2,1,5}),
        //new Vote(cl,new int[]{4,3,2,1,5}),
        
        new Vote(cl,new int[]{5,4,3,2,1}),
        //new Vote(cl,new int[]{5,4,3,2,1}),
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);
    
    String[] expected = {
      "BEGIN LOG",
      "TOTAL VOTES: 7",
      "MAJORITY: 4",
      "ROUND 1: A:2, B:2, C:1, D:1, E:1",
      "DROP: C",
      "DROP: D",
      "DROP: E",
      "ROUND 2: A:2, B:5",
      "WINNER: B:5",
      "END LOG"
    };
    
    Log ans = vm.tabulate();
    compareLogStrings(expected, ans.read());
  }
  
  
  
  @Test (timeout=2000) public void vm_48 (){
    // drop N-1 lowest at once (note, remaining candidate wasn't yet a winner)
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        
        new Vote(cl,new int[]{4,3,2,1,5}),
        new Vote(cl,new int[]{4,3,2,1,5}),
        
        new Vote(cl,new int[]{5,4,3,2,1}),
        new Vote(cl,new int[]{5,4,3,2,1}),
        new Vote(cl,new int[]{5,4,3,2,1}),
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);
    
    String[] expected = {
      "BEGIN LOG",
      "TOTAL VOTES: 11",
      "MAJORITY: 6",
      "ROUND 1: A:2, B:2, C:2, D:2, E:3",
      "DROP: A",
      "DROP: B",
      "DROP: C",
      "DROP: D",
      "ROUND 2: E:11",
      "WINNER: E:11",
      "END LOG"
    };
    
    Log ans = vm.tabulate();
    compareLogStrings(expected, ans.read());
  }
  
  
  
  @Test (timeout=2000) public void vm_49 (){
    // pass only the winner
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        
        // 9 here
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        
        new Vote(cl,new int[]{4,3,2,1,5}),
        new Vote(cl,new int[]{4,3,2,1,5}),
        
        new Vote(cl,new int[]{5,4,3,2,1}),
        new Vote(cl,new int[]{5,4,3,2,1}),
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);
    
    String[] expected = {
      "BEGIN LOG",
      "TOTAL VOTES: 17",
      "MAJORITY: 9",
      "ROUND 1: A:2, B:2, C:9, D:2, E:2",
      "WINNER: C:9",
      "END LOG"
    };
    
    Log ans = vm.tabulate();
    compareLogStrings(expected, ans.read());
  }
  
  
  
  @Test (timeout=2000) public void vm_50 (){
    // pass two tied for first
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        
        new Vote(cl,new int[]{2,1,3,4,5}), // 4
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        
        new Vote(cl,new int[]{3,2,1,4,5}), // 4
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
        
        new Vote(cl,new int[]{4,3,2,1,5}),
        new Vote(cl,new int[]{4,3,2,1,5}),
        
        new Vote(cl,new int[]{5,4,3,2,1}),
        new Vote(cl,new int[]{5,4,3,2,1}),
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);
    
    String[] expected = {
      "BEGIN LOG",
      "TOTAL VOTES: 14",
      "MAJORITY: 8",
      "ROUND 1: A:2, B:4, C:4, D:2, E:2",
      "DROP: A",
      "DROP: D",
      "DROP: E",
      "ROUND 2: B:6, C:8",
      "WINNER: C:8",
      "END LOG"
    };
    
    Log ans = vm.tabulate();
    compareLogStrings(expected, ans.read());
  }
  
  
  
  
  @Test (timeout=2000) public void vm_51 (){
    // relying on seconds, drop one lowest candidate.
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        
        new Vote(cl,new int[]{3,2,1,4,5}),
        
        new Vote(cl,new int[]{4,3,2,1,5}),
        new Vote(cl,new int[]{4,3,2,1,5}),
        new Vote(cl,new int[]{4,3,2,1,5}),
        
        new Vote(cl,new int[]{5,4,3,2,1}),
        new Vote(cl,new int[]{5,4,3,2,1}),
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);
    
    String[] expected = {
      "BEGIN LOG",
      "TOTAL VOTES: 14",
      "MAJORITY: 8",
      "ROUND 1: A:4, B:4, C:1, D:3, E:2",
      "DROP: C",
      "ROUND 2: A:4, B:5, D:3, E:2",
      "DROP: E",
      "ROUND 3: A:4, B:5, D:5",
      "DROP: A",
      "ROUND 4: B:9, D:5",
      "WINNER: B:9",
      "END LOG"
    };
    
    Log ans = vm.tabulate();
    compareLogStrings(expected, ans.read());
  }
  
  
  
  
  @Test (timeout=2000) public void vm_52 (){
    // relying on thirds, drop two lowest candidates.
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    // everybody but C is getting two first place votes; C gets one.
    // A and B won't be viables when we thin the herd.
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{1,2,3,4,5}),
        new Vote(cl,new int[]{1,2,3,4,5}),
        
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        
        new Vote(cl,new int[]{3,2,1,4,5}),
        
        new Vote(cl,new int[]{4,3,2,1,5}),
        new Vote(cl,new int[]{4,3,2,1,5}),
        new Vote(cl,new int[]{4,3,2,1,5}),
        
        new Vote(cl,new int[]{5,4,3,2,1}),
        new Vote(cl,new int[]{5,4,3,2,1}),
        new Vote(cl,new int[]{5,4,3,2,1}),
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);
    
    String[] expected = {
      "BEGIN LOG",
      "TOTAL VOTES: 11",
      "MAJORITY: 6",
      "ROUND 1: A:2, B:2, C:1, D:3, E:3",
      "DROP: C",
      "ROUND 2: A:2, B:3, D:3, E:3",
      "DROP: A",
      "ROUND 3: B:5, D:3, E:3",
      "DROP: D",
      "DROP: E",
      "ROUND 4: B:11",
      "WINNER: B:11",
      "END LOG"
    };
    
    Log ans = vm.tabulate();
    compareLogStrings(expected, ans.read());
  }
  
  
  @Test (timeout=2000) public void vm_53 (){
    // include an invalid vote.
    CandidateList cl = new CandidateList(new String[]{"A","B","C","D","E"});
    // everybody but C is getting two first place votes; C gets one.
    // A and B won't be viables when we thin the herd.
    VoteList vs = new VoteList(new Vote[]{
      new Vote(cl,new int[]{100000000,2,3,4,5}),
        new Vote(cl,null),
        new Vote(null,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{2,1,3,4,5}),
        new Vote(cl,new int[]{3,2,1,4,5}),
    });
    VotingMachine vm = new VotingMachine("role",cl,vs);
    
    String[] expected = {
      "BEGIN LOG",
      "INVALID VOTE",
      "END LOG"
    };
    
    Log ans = vm.tabulate();
    compareLogStrings(expected, ans.read());
  }  

  
}