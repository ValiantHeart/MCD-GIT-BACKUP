// Public tests for Lab07 ALUtils

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class Lab07Tests {
  /*Main method runs tests in this file*/ 
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("Lab07Tests");
  } 

  // Utility to test reversal results
  public void checkReverse(String d[], String expectS){
    ArrayList<String> a = new ArrayList<String>();
    a.addAll(Arrays.asList(d));
    String sourceBefore = a.toString();

    ArrayList<String> actualA = ALUtils.reverse(a);

    String sourceAfter = a.toString();
    String actualS = actualA.toString();
    String msg =
      String.format("Reverse incorrect\n")+
      String.format("Source  : %s (before reverse)\n",sourceBefore)+
      String.format("Expect  : %s\n",expectS)+
      String.format("Actual  : %s\n",actualS)+
      String.format("Source  : %s (after reverse)\n",sourceAfter);
    assertEquals(msg,expectS,actualS);
    assertEquals(msg,sourceBefore,sourceAfter);
  }

  @Test public void test_reverse1() {
    String [] d = {"A","B","C","D","E"};
    String expectS = "[E, D, C, B, A]";
    checkReverse(d,expectS);
  }

  @Test public void test_reverse2() {
    String [] d = {"A","B","C","D"};
    String expectS = "[D, C, B, A]";
    checkReverse(d,expectS);
  }

  @Test public void test_reverse3() {
    String [] d = {"A",};
    String expectS = "[A]";
    checkReverse(d,expectS);
  }

  @Test public void test_reverse4() {
    String [] d = {};
    String expectS = "[]";
    checkReverse(d,expectS);
  }

  @Test public void test_reverse5() {
    String [] d = {"5", "4", "3"};
    String expectS = "[3, 4, 5]";
    checkReverse(d,expectS);
  }

  @Test public void test_reverse6() {
    String [] d = {"5", "4", "3", "2", "1", "0", "A", "B"};
    String expectS = "[B, A, 0, 1, 2, 3, 4, 5]";
    checkReverse(d,expectS);
  }

  // Utility to check rotation reults
  public void checkRotate(Integer d[], int shift, String expectS){
    ArrayList<Integer> a = new ArrayList<Integer>();
    a.addAll(Arrays.asList(d));
    String sourceBefore = a.toString();

    ArrayList<Integer> actualA = ALUtils.rotate(a, shift);

    String sourceAfter = a.toString();
    String actualS = actualA.toString();
    String msg =
      String.format("Rotation incorrect\n")+
      String.format("Source  : %s (before rotate)\n",sourceBefore)+
      String.format("Shift   : %d\n",shift)+
      String.format("Expect  : %s\n",expectS)+
      String.format("Actual  : %s\n",actualS)+
      String.format("Source  : %s (after rotate)\n",sourceAfter);
    assertEquals(msg,expectS,actualS);
    assertEquals(msg,sourceBefore,sourceAfter);
  }

  @Test public void test_rotate1() {
    Integer [] d = { 10, 20, 30, 40, 50, 60 };
    int shift = 2;
    String expectS = "[50, 60, 10, 20, 30, 40]";
    checkRotate(d,shift,expectS);
  }
  @Test public void test_rotate2() {
    Integer [] d = { 10, 20, 30, 40, 50, 60 };
    int shift = 7;
    String expectS = "[60, 10, 20, 30, 40, 50]";
    checkRotate(d,shift,expectS);
  }
  @Test public void test_rotate3() {
    Integer [] d = { 10, 20, 30, 40};
    int shift = 3;
    String expectS = "[20, 30, 40, 10]";
    checkRotate(d,shift,expectS);
  }
  @Test public void test_rotate4() {
    Integer [] d = { 10, 20, 30, 40};
    int shift = 4;
    String expectS = "[10, 20, 30, 40]";
    checkRotate(d,shift,expectS);
  }
  @Test public void test_rotate5() {
    Integer [] d = { };
    int shift = 4;
    String expectS = "[]";
    checkRotate(d,shift,expectS);
  }
  @Test public void test_rotate6() {
    Integer [] d = { };
    int shift = 0;
    String expectS = "[]";
    checkRotate(d,shift,expectS);
  }
  @Test public void test_rotate7() {
    Integer [] d = { 10, 20, 30, 40};
    int shift = 0;
    String expectS = "[10, 20, 30, 40]";
    checkRotate(d,shift,expectS);
  }
  @Test public void test_rotate8() {
    Integer [] d = { 10, 20, 30, 40, 50, 60, 70};
    int shift = 28;
    String expectS = "[10, 20, 30, 40, 50, 60, 70]";
    checkRotate(d,shift,expectS);
  }
  @Test public void test_rotate9() {
    Integer [] d = { 10, 20, 30, 40, 50, 60, 70};
    int shift = 39;
    String expectS = "[40, 50, 60, 70, 10, 20, 30]";
    checkRotate(d,shift,expectS);
  }
  @Test public void test_rotate10() {
    Integer [] d = { 10, 20, 30, 40};
    int shift = 4;
    String expectS = "[10, 20, 30, 40]";
    checkRotate(d,shift,expectS);
  }


}
