// Example of using unit tests for programming assignment 1.  This is
// partially how your code will be graded.  Later in the class we will
// write our own unit tests.  To run them on the command line, make
// sure that the junit-cs211.jar is in the project directory.
// 
// $> javac -cp .:junit-cs211.jar BalancedTest.java   #compile
// $> java -cp .:junit-cs211.jar BalancedTest         #run tests
// 
// On windows replace : with ; (colon with semicolon)
// $> javac -cp .;junit-cs211.jar BalancedTest.java   #compile
// $> java -cp .;junit-cs211.jar BalancedTest         #run tests

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class BalancedTest {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("BalancedTest");
  }
  
  @Test (timeout=1000) public void Balanced_isBalanced_t1 (){ assertTrue(Balanced.isBalanced("")); }
  @Test (timeout=1000) public void Balanced_isBalanced_t2 (){ assertTrue(Balanced.isBalanced("()")); }
  @Test (timeout=1000) public void Balanced_isBalanced_t3 (){ assertTrue(Balanced.isBalanced("()()")); }
  @Test (timeout=1000) public void Balanced_isBalanced_t4 (){ assertTrue(Balanced.isBalanced("(())")); }
  @Test (timeout=1000) public void Balanced_isBalanced_t5 (){ assertTrue(Balanced.isBalanced("(()())")); }
  @Test (timeout=1000) public void Balanced_isBalanced_t6 (){ assertTrue(Balanced.isBalanced("((())(()))")); }
  @Test (timeout=1000) public void Balanced_isBalanced_t7 (){ assertTrue(Balanced.isBalanced("()()()")); }
  @Test (timeout=1000) public void Balanced_isBalanced_t8 (){ assertTrue(Balanced.isBalanced("(())((())())")); }
  @Test (timeout=1000) public void Balanced_isBalanced_t9 (){ assertTrue(Balanced.isBalanced("(((((((((()())())())())())())())()))")); }
  
  @Test (timeout=1000) public void Balanced_isBalanced_t10 (){ assertFalse(Balanced.isBalanced(")")); }
  @Test (timeout=1000) public void Balanced_isBalanced_t11 (){ assertFalse(Balanced.isBalanced("(")); }
  @Test (timeout=1000) public void Balanced_isBalanced_t12 (){ assertFalse(Balanced.isBalanced(")(")); }
  @Test (timeout=1000) public void Balanced_isBalanced_t13 (){ assertFalse(Balanced.isBalanced("(())())(()")); }
  @Test (timeout=1000) public void Balanced_isBalanced_t14 (){ assertFalse(Balanced.isBalanced("())(")); }
  @Test (timeout=1000) public void Balanced_isBalanced_t15 (){ assertFalse(Balanced.isBalanced("(()")); }
  @Test (timeout=1000) public void Balanced_isBalanced_t16 (){ assertFalse(Balanced.isBalanced("())(()")); }
  @Test (timeout=1000) public void Balanced_isBalanced_t17 (){ assertFalse(Balanced.isBalanced(")))))")); }
  
  public void expectException(String s){
    try {
      Balanced.isBalanced(s);
      fail("should have raised RuntimeException (no non-parenthesis characters allowed)");
    }
    catch (RuntimeException e){ /*good. */} 
  }
  
  @Test (timeout=1000) public void Balanced_isBalanced_f1(){ expectException("A"); }
  @Test (timeout=1000) public void Balanced_isBalanced_f2(){ expectException(" "); }
  @Test (timeout=1000) public void Balanced_isBalanced_f3(){ expectException("( )"); }
  @Test (timeout=1000) public void Balanced_isBalanced_f4(){ expectException("(()())\n"); }
  @Test (timeout=1000) public void Balanced_isBalanced_f5(){ expectException("(()()((()()()(((((p)l)e)a)s)e)n)o)"); }
}
