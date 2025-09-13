// Public tests for Lab08 Quadrant enumeration
import org.junit.Assert;
import static org.junit.Assert.*;
// import static org.junit.*;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.io.*;
import java.util.*;


public class Lab08Tests {
  /*Main method runs tests in this file*/ 
  public static void main(String args[])
  {
    org.junit.runner.JUnitCore.main("Lab08Tests");
  } 


  static ByteArrayOutputStream localOut, localErr;
  static PrintStream sysOut, sysErr;
  static String [] empty = {};

  // Determine what the newline is on the running system
  String newline = System.getProperty("line.separator");

  @BeforeClass
  public static void setUp() throws Exception {
    sysOut = System.out;
    sysErr = System.err;
  }

  // Before every test is run, reset the streams to capture
  // stdout/stderr
  @Before
  public void setUpStreams() {
    localOut = new ByteArrayOutputStream();
    localErr = new ByteArrayOutputStream();
    System.setOut(new PrintStream(localOut));
    System.setErr(new PrintStream(localErr));
  }

  // After every test, restore stdout/stderr
  @After
  public void cleanUpStreams() {
    System.setOut(null);
    System.setErr(null);
    System.setOut(sysOut);
    System.setErr(sysErr);
  }

  @Test public void QuadrantsExist() {
    Quadrant q;
    q = Quadrant.Q1;
    q = Quadrant.Q2;
    q = Quadrant.Q3;
    q = Quadrant.Q4;
  }

  @Test public void only4() {
    for(Quadrant q : Quadrant.values()){
      if(q != Quadrant.Q1 &&
         q != Quadrant.Q2 &&
         q != Quadrant.Q3 &&
         q != Quadrant.Q4){
        fail(String.format("Quadrant %s shouldn't be there",q));
      }
    }
  }

  @Test public void flipX_all(){
    assertEquals(Quadrant.Q1.flipX(), Quadrant.Q2);
    assertEquals(Quadrant.Q2.flipX(), Quadrant.Q1);
    assertEquals(Quadrant.Q3.flipX(), Quadrant.Q4);
    assertEquals(Quadrant.Q4.flipX(), Quadrant.Q3);
  }

  @Test public void signPair_all(){
    assertEquals(Quadrant.Q1.signPair(), "(+,+)");
    assertEquals(Quadrant.Q2.signPair(), "(-,+)");
    assertEquals(Quadrant.Q3.signPair(), "(-,-)");
    assertEquals(Quadrant.Q4.signPair(), "(+,-)");
  }

  @Test public void fromInts_all(){
    assertEquals( Quadrant.fromInts(1,5),    Quadrant.Q1 );
    assertEquals( Quadrant.fromInts(12,2),   Quadrant.Q1 );
    assertEquals( Quadrant.fromInts(-1,5),   Quadrant.Q2 );
    assertEquals( Quadrant.fromInts(-13,16), Quadrant.Q2 );
    assertEquals( Quadrant.fromInts(-12,-4), Quadrant.Q3 );
    assertEquals( Quadrant.fromInts(-5,-1),  Quadrant.Q3 );
    assertEquals( Quadrant.fromInts(13,-52), Quadrant.Q4 );
    assertEquals( Quadrant.fromInts(91,-2),  Quadrant.Q4 );
  }    

  public void test_main(String argS, String expect){
    setUpStreams();
    String args[] = argS.split("\\s+");
    Quadrant.main(args);
    String actual = localOut.toString();
    String msg = String.format("\nArguments: %s\nExpect:\n%s\n\nActual:\n%s\n\n",
                               argS,expect,actual);
    assertEquals(msg, expect, actual);
    cleanUpStreams();
  }

  @Test public void main_1(){
    test_main("",
              "");
  }
  @Test public void main_2(){
    test_main("1",
              "");
  }
  @Test public void main_3(){
    test_main("-1",
              "");
  }
  @Test public void main_4(){
    test_main("1 5",
              "(1,5) has signs (+,+) and is in Q1"+newline+
              "");
  }
  @Test public void main_5(){
    test_main("-2 -13  4",
              "(-2,-13) has signs (-,-) and is in Q3"+newline+
              "");
  }
  @Test public void main_6(){
    test_main("2 -13  4 12   -1 113  19",
              "(2,-13) has signs (+,-) and is in Q4"+newline+
              "(4,12) has signs (+,+) and is in Q1"+newline+
              "(-1,113) has signs (-,+) and is in Q2"+newline+
              "");
  }
  @Test public void main_7(){
    test_main("1 2   -3 -4   5 -6 -7 -8 9 10 11 -12 -13 14 -15 -16 17 18 -19 20",
              "(1,2) has signs (+,+) and is in Q1"+newline+
              "(-3,-4) has signs (-,-) and is in Q3"+newline+
              "(5,-6) has signs (+,-) and is in Q4"+newline+
              "(-7,-8) has signs (-,-) and is in Q3"+newline+
              "(9,10) has signs (+,+) and is in Q1"+newline+
              "(11,-12) has signs (+,-) and is in Q4"+newline+
              "(-13,14) has signs (-,+) and is in Q2"+newline+
              "(-15,-16) has signs (-,-) and is in Q3"+newline+
              "(17,18) has signs (+,+) and is in Q1"+newline+
              "(-19,20) has signs (-,+) and is in Q2"+newline+
              "");
  }
}
