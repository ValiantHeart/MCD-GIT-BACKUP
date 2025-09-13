// Example of using unit tests for programming assignment 1.  This is
// partially how your code will be graded.  Later in the class we will
// write our own unit tests.  To run them on the command line, make
// sure that the junit-cs211.jar is in the project directory.
// 
// $> javac -cp .:junit-cs211.jar MaxErrorsTest.java   #compile
// $> java -cp .:junit-cs211.jar MaxErrorsTest         #run tests
// 
// On windows replace : with ; (colon with semicolon)
// $> javac -cp .;junit-cs211.jar MaxErrorsTest.java   #compile
// $> java -cp .;junit-cs211.jar MaxErrorsTest         #run tests

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class MaxErrorsTest {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("MaxErrorsTest");
  }
  
  @Test (timeout=1000) public void MaxErrors_t1(){
    assertEquals(MaxErrors.max3(1,3,5),5);
    assertEquals(MaxErrors.max3(1,5,1),5);
    assertEquals(MaxErrors.max3(-1,-10,-3),-1);
    assertEquals(MaxErrors.max3(2,6,6),6);
  }
  
  @Test (timeout=1000) public void MaxErrors_t2(){
    assertEquals(MaxErrors.max(new int[]{1,2,3,4,5,6,7}),7);
    assertEquals(MaxErrors.max(new int[]{2,4,6,1,5,3,7,1,0,-1}),7);
    assertEquals(MaxErrors.max(new int[]{0}),0);
    assertEquals(MaxErrors.max(new int[]{-2,-4,-6,-8}),-2);
    assertEquals(MaxErrors.max(new int[]{Integer.MIN_VALUE}),Integer.MIN_VALUE);
    assertEquals(MaxErrors.max(new int[]{2000,5000000,Integer.MAX_VALUE}),Integer.MAX_VALUE);
  }
  
  @Test (timeout=1000) public void MaxErrors_t3(){
    assertEquals(MaxErrors.max(),"max");
  }
}
