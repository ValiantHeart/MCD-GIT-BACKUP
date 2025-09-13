// Example of using unit tests for programming assignment 1.  This is
// partially how your code will be graded.  Later in the class we will
// write our own unit tests.  To run them on the command line, make
// sure that the junit-cs211.jar is in the project directory.
// 
// $> javac -cp .:junit-cs211.jar HighlyCompositeTest.java   #compile
// $> java -cp .:junit-cs211.jar HighlyCompositeTest         #run tests
// 
// On windows replace : with ; (colon with semicolon)
// $> javac -cp .;junit-cs211.jar HighlyCompositeTest.java   #compile
// $> java -cp .;junit-cs211.jar HighlyCompositeTest         #run tests

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class HighlyCompositeTest {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("HighlyCompositeTest");
  }
  
  @Test (timeout=1000) public void HC_nd_t1 (){ assertEquals(1,  HighlyComposite.numDivisors(   1)); }
  @Test (timeout=1000) public void HC_nd_t2 (){ assertEquals(2,  HighlyComposite.numDivisors(   2)); }
  @Test (timeout=1000) public void HC_nd_t3 (){ assertEquals(2,  HighlyComposite.numDivisors(   3)); }
  @Test (timeout=1000) public void HC_nd_t4 (){ assertEquals(3,  HighlyComposite.numDivisors(   4)); }
  @Test (timeout=1000) public void HC_nd_t5 (){ assertEquals(2,  HighlyComposite.numDivisors(   5)); }
  @Test (timeout=1000) public void HC_nd_t6 (){ assertEquals(4,  HighlyComposite.numDivisors(   6)); }
  @Test (timeout=1000) public void HC_nd_t8 (){ assertEquals(4,  HighlyComposite.numDivisors(   8)); }
  @Test (timeout=1000) public void HC_nd_t9 (){ assertEquals(9,  HighlyComposite.numDivisors( 100)); }
  @Test (timeout=1000) public void HC_nd_t10(){ assertEquals(8,  HighlyComposite.numDivisors( 250)); }
  @Test (timeout=1000) public void HC_nd_t11(){ assertEquals(24, HighlyComposite.numDivisors( 360)); }
  @Test (timeout=1000) public void HC_nd_t12(){ assertEquals(60, HighlyComposite.numDivisors(5040)); }
  
  @Test (timeout=1000) public void HC_highlyComposite_t1 (){ assertTrue(HighlyComposite.highlyComposite(1)); }
  @Test (timeout=1000) public void HC_highlyComposite_t2 (){ assertTrue(HighlyComposite.highlyComposite(2)); }
  @Test (timeout=1000) public void HC_highlyComposite_t3 (){ assertTrue(HighlyComposite.highlyComposite(4)); }
  @Test (timeout=1000) public void HC_highlyComposite_t4 (){ assertTrue(HighlyComposite.highlyComposite(6)); }
  @Test (timeout=1000) public void HC_highlyComposite_t5 (){ assertTrue(HighlyComposite.highlyComposite(12)); }
  @Test (timeout=1000) public void HC_highlyComposite_t6 (){ assertTrue(HighlyComposite.highlyComposite(24)); }
  @Test (timeout=1000) public void HC_highlyComposite_t7 (){ assertTrue(HighlyComposite.highlyComposite(36)); }
  @Test (timeout=1000) public void HC_highlyComposite_t8 (){ assertTrue(HighlyComposite.highlyComposite(48)); }
  @Test (timeout=1000) public void HC_highlyComposite_t9 (){ assertTrue(HighlyComposite.highlyComposite(60)); }
  @Test (timeout=1000) public void HC_highlyComposite_t10(){ assertTrue(HighlyComposite.highlyComposite(120)); }
  @Test (timeout=1000) public void HC_highlyComposite_t11(){ assertTrue(HighlyComposite.highlyComposite(180)); }
  @Test (timeout=1000) public void HC_highlyComposite_t12(){ assertTrue(HighlyComposite.highlyComposite(240)); }
  @Test (timeout=1000) public void HC_highlyComposite_t13(){ assertTrue(HighlyComposite.highlyComposite(360)); }
  @Test (timeout=1000) public void HC_highlyComposite_t14(){ assertTrue(HighlyComposite.highlyComposite(720)); }
  @Test (timeout=1000) public void HC_highlyComposite_t15(){ assertTrue(HighlyComposite.highlyComposite(840)); }
  @Test (timeout=1000) public void HC_highlyComposite_t16(){ assertTrue(HighlyComposite.highlyComposite(1260)); }
  @Test (timeout=1000) public void HC_highlyComposite_t17(){ assertTrue(HighlyComposite.highlyComposite(1680)); }
  @Test (timeout=1000) public void HC_highlyComposite_t18(){ assertTrue(HighlyComposite.highlyComposite(2520)); }
  @Test (timeout=1000) public void HC_highlyComposite_t19(){ assertTrue(HighlyComposite.highlyComposite(5040)); }
  @Test (timeout=1000) public void HC_highlyComposite_t20(){ assertFalse(HighlyComposite.highlyComposite(3)); }
  @Test (timeout=1000) public void HC_highlyComposite_t21(){ assertFalse(HighlyComposite.highlyComposite(5)); }
  @Test (timeout=1000) public void HC_highlyComposite_t22(){ assertFalse(HighlyComposite.highlyComposite(7)); }
  @Test (timeout=1000) public void HC_highlyComposite_t23(){ assertFalse(HighlyComposite.highlyComposite(8)); }
  @Test (timeout=1000) public void HC_highlyComposite_t24(){ assertFalse(HighlyComposite.highlyComposite(9)); }
  @Test (timeout=1000) public void HC_highlyComposite_t25(){ assertFalse(HighlyComposite.highlyComposite(10)); }
  @Test (timeout=1000) public void HC_highlyComposite_t26(){ assertFalse(HighlyComposite.highlyComposite(11)); }
  @Test (timeout=1000) public void HC_highlyComposite_t27(){ assertFalse(HighlyComposite.highlyComposite(30)); }
  @Test (timeout=1000) public void HC_highlyComposite_t28(){ assertFalse(HighlyComposite.highlyComposite(100)); }
  @Test (timeout=1000) public void HC_highlyComposite_t29(){ assertFalse(HighlyComposite.highlyComposite(625)); }
  @Test (timeout=1000) public void HC_highlyComposite_t30(){ assertFalse(HighlyComposite.highlyComposite(1024)); }
  
  @Test (timeout=1000) public void HC_highlyComposite_f1(){
    try { HighlyComposite.highlyComposite(-3); }
    catch (RuntimeException e){ /*good. */ return;} 
    fail("should have raised RuntimeException (no negatives allowed)");
  }
  
  @Test (timeout=1000) public void HC_highlyComposite_f2(){
    try { HighlyComposite.highlyComposite(0); }
    catch (RuntimeException e){ /*good. */ return;} 
    fail("should have raised RuntimeException (no zeroes allowed)");
  }
  
}
