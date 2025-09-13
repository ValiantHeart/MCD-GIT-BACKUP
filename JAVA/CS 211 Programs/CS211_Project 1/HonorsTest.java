// Example of using unit tests for programming assignment 1.  This is
// partially how your code will be graded.  Later in the class we will
// write our own unit tests.  To run them on the command line, make
// sure that the junit-cs211.jar is in the project directory.
// 
// $> javac -cp .:junit-cs211.jar HonorsTest.java   #compile
// $> java -cp .:junit-cs211.jar HonorsTest         #run tests
// 
// On windows replace : with ; (colon with semicolon)
// $> javac -cp .;junit-cs211.jar HonorsTest.java   #compile
// $> java -cp .;junit-cs211.jar HonorsTest         #run tests

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class HonorsTest {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("HonorsTest");
  }
  
  @Test (timeout=1000) public void kth_t1 (){ assertEquals(3,Honors.kthSmallest(new int[]{1,2,3,4,5,6},3)); }
  @Test (timeout=1000) public void kth_t2 (){ assertEquals(11,Honors.kthSmallest(new int[]{14,15,19,21,18,17,11,13,16,12,20},1)); }
  @Test (timeout=1000) public void kth_t3 (){ assertEquals(12,Honors.kthSmallest(new int[]{14,15,19,21,18,17,11,13,16,12,20},2)); }
  @Test (timeout=1000) public void kth_t4 (){ assertEquals(13,Honors.kthSmallest(new int[]{14,15,19,21,18,17,11,13,16,12,20},3)); }
  @Test (timeout=1000) public void kth_t5 (){ assertEquals(20,Honors.kthSmallest(new int[]{14,15,19,21,18,17,11,13,16,12,20},10)); }
  
  public void biggerTest(int k, int expected){
    
    final int N = 10000;
    int[] xs = new int[N];
    for (int i=0;i<xs.length; i++){
      xs[i] = 100+i;
    }
    assertEquals(expected,Honors.kthSmallest(xs,k));
  }
  @Test (timeout=1000) public void kth_t6 (){ biggerTest(1,100);}
  @Test (timeout=1000) public void kth_t7 (){ biggerTest(901,1000);}
  @Test (timeout=1000) public void kth_t8 (){ biggerTest(9999,10098);}
  @Test (timeout=1000) public void kth_t9 (){ biggerTest(50,149);}
  
  public void expectException(int[] xs, int k){ expectException(xs,k,"should have raised RuntimeException when there aren't enough items."); }
  
  public void expectException(int[] xs, int k, String msg){
    try {
      Honors.kthSmallest(xs,k);
      fail (msg);
    }
    catch (RuntimeException e){ /* good.*/ }
  }
  
  @Test (timeout=1000) public void SS_f1 (){ expectException(new int[]{14,15,19,21,18,17,11,13,16,12,20}, 12); }
  @Test (timeout=1000) public void SS_f2 (){ expectException(new int[]{}, 1); }
  @Test (timeout=1000) public void SS_f3 (){ expectException(new int[]{}, 0, "k values are positive only"); }
  @Test (timeout=1000) public void SS_f4 (){ expectException(new int[]{1,2,3}, -2, "k values are positive only"); }
  @Test (timeout=1000) public void SS_f5 (){ expectException(new int[]{1,2}, 3); }
  @Test (timeout=1000) public void SS_f6 (){
    final int N = 10000;
    int[] xs = new int[N];
    for (int i=0;i<xs.length; i++){
      xs[i] = 100+i;
    }
    expectException(xs, N+1);
  }
  
  @Test (timeout=1000) public void SS_f7 (){
    expectException(null, 1,"should have thrown exception when array is null.");
  }
  
}
