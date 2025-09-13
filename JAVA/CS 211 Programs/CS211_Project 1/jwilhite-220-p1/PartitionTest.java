// Example of using unit tests for programming assignment 1.  This is
// partially how your code will be graded.  Later in the class we will
// write our own unit tests.  To run them on the command line, make
// sure that the junit-cs211.jar is in the project directory.
// 
// $> javac -cp .:junit-cs211.jar PartitionTest.java   #compile
// $> java -cp .:junit-cs211.jar PartitionTest         #run tests
// 
// On windows replace : with ; (colon with semicolon)
// $> javac -cp .;junit-cs211.jar PartitionTest.java   #compile
// $> java -cp .;junit-cs211.jar PartitionTest         #run tests

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class PartitionTest {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("PartitionTest");
  }
  
  public void runPartitionTest(int[] in, int[]out){
    Partition.partitionOddEven(in);   // perform the partitioning action (in place).
    assertArrayEquals(in,out);        // do they now have the same sequence of values?
  }
  
  @Test (timeout=1000) public void partition_1 (){ runPartitionTest(new int[]{}, new int[]{}); }
  @Test (timeout=1000) public void partition_2 (){ runPartitionTest(new int[]{7,6}, new int[]{6,7}); }
  @Test (timeout=1000) public void partition_3 (){ runPartitionTest(new int[]{20,3,}, new int[]{20,3}); }
  @Test (timeout=1000) public void partition_4 (){ runPartitionTest(new int[]{1,3,5}, new int[]{1,3,5}); }
  @Test (timeout=1000) public void partition_5 (){ runPartitionTest(new int[]{1,2,3,4,5}, new int[]{2,4,1,3,5}); }
  @Test (timeout=1000) public void partition_6 (){ runPartitionTest(new int[]{9,7,5,6,8,10}, new int[]{6,8,10,9,7,5}); }
  @Test (timeout=1000) public void partition_7 (){ runPartitionTest(new int[]{1,6,8,3,4}, new int[]{6,8,4,1,3}); }
  @Test (timeout=1000) public void partition_8 (){ runPartitionTest(new int[]{1,2,3,3,4,5,5,6,5,-2}, new int[]{2,4,6,-2,1,3,3,5,5,5}); }
  @Test (timeout=1000) public void partition_9 (){ runPartitionTest(new int[]{-3,-2,-1,0,1,2,3,4,5}, new int[]{-2,0,2,4,-3,-1,1,3,5}); }
  @Test (timeout=1000) public void partition_10 (){
   try {
     Partition.partitionOddEven(null);
   }
   catch(RuntimeException e){
     return;
   }
   fail ("should have thrown exception for null array.");
 }
}
