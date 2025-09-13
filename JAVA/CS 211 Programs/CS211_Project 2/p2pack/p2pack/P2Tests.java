// Use this file to run all tests for the project at once:

// Example of using unit tests for this programming assignment.  This is
// partially how your code will be graded.  Later in the class we will
// write our own unit tests.  To run them on the command line, make
// sure that the junit-cs211.jar is in the project directory.
// 
// $> javac -cp .:junit-cs211.jar P2Tests.java   #compile
// $> java -cp .:junit-cs211.jar P2Tests         #run tests
// 
// On windows replace : with ; (colon with semicolon)
// $> javac -cp .;junit-cs211.jar P2Tests.java   #compile
// $> java -cp .;junit-cs211.jar P2Tests         #run tests


import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class P2Tests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main(
                                    "CandidateListTest",
                                    "VoteTest",
                                    "VoteListTest",
                                    "LogTest",
                                    "VotingMachineTest"
                                    // ,"HonorsTest"
                                   );
  }
}
