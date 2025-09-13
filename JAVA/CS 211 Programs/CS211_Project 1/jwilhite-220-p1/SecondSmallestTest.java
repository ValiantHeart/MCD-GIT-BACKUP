// Example of using unit tests for programming assignment 1.  This is
// partially how your code will be graded.  Later in the class we will
// write our own unit tests.  To run them on the command line, make
// sure that the junit-cs211.jar is in the project directory.
// 
// $> javac -cp .:junit-cs211.jar SecondSmallestTest.java   #compile
// $> java -cp .:junit-cs211.jar SecondSmallestTest         #run tests
// 
// On windows replace : with ; (colon with semicolon)
// $> javac -cp .;junit-cs211.jar SecondSmallestTest.java   #compile
// $> java -cp .;junit-cs211.jar SecondSmallestTest         #run tests

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class SecondSmallestTest {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("SecondSmallestTest");
  }
  
  @Test (timeout=1000) public void SS_t1 (){ assertEquals(2, SecondSmallest.secondSmallest(new int[]{1,2,3,4,5})); }
  @Test (timeout=1000) public void SS_t2  (){ assertEquals(3, SecondSmallest.secondSmallest(new int[]{5,10,2,100,3})); }
  @Test (timeout=1000) public void SS_t3  (){ assertEquals(2, SecondSmallest.secondSmallest(new int[]{1,4,3,2,5})); }
  @Test (timeout=1000) public void SS_t4  (){ assertEquals(2, SecondSmallest.secondSmallest(new int[]{1,5,4,3,2})); }
  @Test (timeout=1000) public void SS_t5  (){ assertEquals(1, SecondSmallest.secondSmallest(new int[]{1,1,2,3,4})); }
  @Test (timeout=1000) public void SS_t6  (){ assertEquals(2, SecondSmallest.secondSmallest(new int[]{1,2,2,3,4})); }
  @Test (timeout=1000) public void SS_t7  (){ assertEquals(1, SecondSmallest.secondSmallest(new int[]{1,1,1,2,2})); }
  @Test (timeout=1000) public void SS_t8  (){ assertEquals(1, SecondSmallest.secondSmallest(new int[]{1,1,1,2,3})); }
  @Test (timeout=1000) public void SS_t9  (){ assertEquals(Integer.MIN_VALUE+1, SecondSmallest.secondSmallest(new int[]{Integer.MIN_VALUE,Integer.MIN_VALUE+1, -1000,-10,0,1,2,3,4,5})); }
  @Test (timeout=1000) public void SS_t10 (){ assertEquals(4, SecondSmallest.secondSmallest(new int[]{67,6,8,9,7,6,7,67,5,43,6,8,7,6,42,30,5,7,8,9,6,4,14,6,78,3})); }
  @Test (timeout=1000) public void SS_f1 (){ 
    try {
      int ans = SecondSmallest.secondSmallest(new int[]{});
      fail ("should have raised RuntimeException when there aren't any items.");
    }
    catch (RuntimeException e){ return; }
  }
  @Test (timeout=1000) public void SS_f2 (){ 
    try {
      SecondSmallest.secondSmallest(new int[]{5});
      fail ("should have raised RuntimeException when there aren't enough items items.");
    }
    catch (RuntimeException e){ return; }
    
  }
  @Test (timeout=1000) public void SS_f3 (){ 
    try {
      SecondSmallest.secondSmallest(null);
      fail ("should have raised RuntimeException when the array is null.");
    }
    catch (RuntimeException e){ return; }
    
  }
  
}
