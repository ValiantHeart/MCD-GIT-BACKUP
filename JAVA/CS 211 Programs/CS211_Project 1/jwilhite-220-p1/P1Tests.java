// Use this file to run all tests for the project at once:

// Example of using unit tests for programming assignment 1.  This is
// partially how your code will be graded.  Later in the class we will
// write our own unit tests.  To run them on the command line, make
// sure that the junit-cs211.jar is in the project directory.
// 
// $> javac -cp .:junit-cs211.jar P1Tests.java   #compile
// $> java -cp .:junit-cs211.jar P1Tests         #run tests
// 
// On windows replace : with ; (colon with semicolon)
// $> javac -cp .;junit-cs211.jar P1Tests.java   #compile
// $> java -cp .;junit-cs211.jar P1Tests         #run tests


import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class P1Tests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main(
                                    "MaxErrorsTest",
                                    "HighlyCompositeTest",
                                    "SecondSmallestTest",
                                    "PartitionTest",
                                    "BalancedTest"
                                    // ,"HonorsTest"
                                   );
  }
}
