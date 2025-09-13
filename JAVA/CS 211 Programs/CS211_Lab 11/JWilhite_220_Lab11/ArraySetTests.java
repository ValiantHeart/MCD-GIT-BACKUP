import java.io.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test; // fixes some compile problems with annotations

public class ArraySetTests{

  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("ArraySetTests");
  }

  @Test public void constructor1(){
    ArraySet<String> a = new ArraySet<String>();
    assertEquals(0, a.size());
    assertEquals("[]", a.toString());
  }
  @Test public void constructor2(){
    ArraySet<Integer> a = new ArraySet<Integer>(5);
    assertEquals(0, a.size());
  }
  @Test public void containsNothing2(){
    ArraySet<String> a = new ArraySet<String>();
    assertFalse(a.contains("hi"));
    assertFalse(a.contains("bye"));
    assertFalse(a.contains("Blackbird"));
  }

  // ADD ADDITIONAL TESTS HERE
	//test expected outputs for size method:
		@Test public void size_1() //checks that the default size is 0
		{
			ArraySet<String> a = new ArraySet<String>();
			assertEquals(0, a.size());
		}
		
		@Test public void size_2() //checks that multiple adds are successful
		{
			ArraySet<String> a = new ArraySet<String>();
			assertEquals(0, a.size());
			a.add("This");
			a.add("is");
			assertEquals(2, a.size());
			a.add("a");
			a.add("size");
			assertEquals(4, a.size());
			a.add("test");
			assertEquals(5, a.size());
			
		}		
		
		@Test public void size_3() //checks to make sure that dupes aren't counted in size
		{
			ArraySet<String> a = new ArraySet<String>();
			assertEquals(0, a.size());
			a.add("This");
			a.add("dupe");
			a.add("is");
			assertEquals(3, a.size());
			a.add("dupe");
			assertEquals(3, a.size());
			a.add("not present");
			assertEquals(4, a.size());
			
		}
	
	//test expected outputs for contains method:
		@Test public void contains_1() //checks if it returns true if the parameter item is in the set
		{
			ArraySet<String> a = new ArraySet<String>();
			a.add("hello");
			assertTrue(a.contains("hello"));
		}
		
		@Test public void contains_2() //checks if it returns true if the parameter item is in the set, checks it multiple times
		{
			ArraySet<String> a = new ArraySet<String>();
			a.add("hello");
			assertTrue(a.contains("hello"));			
			a.add("there");
			assertTrue(a.contains("there"));
		}
		
		@Test public void contains_3() //checks if it returns false if the parameter item is not in the set
		{
			ArraySet<String> a = new ArraySet<String>();
			a.add("hello");
			assertFalse(a.contains("jello"));
		}
	
	//test expected outputs for add method:
		@Test public void add_1() //checks if add works
		{
			ArraySet<String> a = new ArraySet<String>();
			assertTrue(a.add("testing 1 2 3"));
			assertTrue(a.contains("testing 1 2 3"));
		}		
		
		@Test public void add_2() //checks if add returns false if the item is already in the set
		{
			ArraySet<String> a = new ArraySet<String>();
			assertTrue(a.add("testing 1 2 3"));
			assertTrue(a.contains("testing 1 2 3"));
			assertFalse(a.add("testing 1 2 3"));
		}
		
		@Test public void add_3() //checks if add returns true for attempting to add null
		{
			ArraySet<String> a = new ArraySet<String>();
			assertTrue(a.add(null));
		}
		
		@Test public void add_4() //checks if can add after a null at pos >1
		{
			ArraySet<String> a = new ArraySet<String>();
			assertTrue(a.add("hi"));
			assertTrue(a.add(null));
			assertTrue(a.add("there"));
		}
		
		@Test public void add_5() //checks if can add after a null at pos 1
		{
			ArraySet<String> a = new ArraySet<String>();
			assertTrue(a.add(null));
			assertTrue(a.add("hi"));
			assertTrue(a.add("there"));
		}
		
		@Test public void add_6() //checks if add returns true for attempting to add null
		{
			ArraySet<String> a = new ArraySet<String>();
			assertTrue(a.add("hi"));
			assertTrue(a.add("there"));
			assertTrue(a.add(null));
		}		
	//test expected outputs for remove method:
		@Test public void remove_1() //checks if remove works as expected without null present
		{
			ArraySet<String> a = new ArraySet<String>();
			a.add("hi");
			a.add("there");
			assertTrue(a.remove("there"));
			assertFalse(a.contains("there"));
		}
		
		@Test public void remove_2() //checks if remove works as expected even if there's a null present before
		{
			ArraySet<String> a = new ArraySet<String>();
			a.add("hi");
			a.add(null);
			assertTrue(a.remove("hi"));
			assertFalse(a.contains("hi"));
		}		
		
		@Test public void remove_3() //checks if remove works as expected even if there's a null present after
		{
			ArraySet<String> a = new ArraySet<String>();
			a.add("hi");
			a.add(null);
			a.add("there");
			assertTrue(a.remove("there"));
			assertFalse(a.contains("there"));
		}
	
	
	//test expected outputs for the toString method:
		@Test public void toString_1() //test if toString performs as expected
		{
			ArraySet<String> a = new ArraySet<String>();
			a.add("This");
			a.add("is");
			a.add("a");
			a.add("toString");
			a.add("test");
			String expected = "[This, is, a, toString, test]";
			assertEquals(expected, a.toString());
		}		
		
		@Test public void toString_2() //test if toString performs as expected with null
		{
			ArraySet<String> a = new ArraySet<String>();
			a.add("goodbye");
			a.add("ciao");
			a.add(null);
			String expected = "[goodbye, ciao, null]";
			assertEquals(expected, a.toString());
		}
	
	//combo tests
		@Test public void combo_1() //checks if it can add null and add/remove things afterwords
		{
			ArraySet<String> a = new ArraySet<String>();
			assertTrue(a.add(null));
			assertTrue(a.add("I"));
			assertTrue(a.add("AM"));
			assertTrue(a.add("THE"));
			assertTrue(a.add("GALACTIC"));
			assertTrue(a.add("SENATE"));
			assertEquals(6 , a.size());
			assertTrue(a.remove("GALACTIC"));
			assertEquals(5 , a.size());
		}
		
		@Test public void combo_2() //checks if it can add null and add/remove things afterwords
		{
			ArraySet<String> a = new ArraySet<String>();
			assertTrue(a.add("I"));
			assertTrue(a.add("AM"));
			assertTrue(a.add("THE"));
			assertTrue(a.add("GALACTIC"));
			assertTrue(a.add("SENATE"));
			assertTrue(a.add(null));
			assertEquals(6 , a.size());
			assertTrue(a.remove("GALACTIC"));
			assertEquals(5 , a.size());
		}
		
		@Test public void combo_ALL() //checks if it all methods work when working in tandem without a null present-
		{
			ArraySet<String> a = new ArraySet<String>();
			String str = "[I, AM, THE, SENATE]";
			assertTrue(a.add("I"));
			assertTrue(a.add("AM"));
			assertTrue(a.add("THE"));
			assertTrue(a.add("GALACTIC"));
			assertTrue(a.add("SENATE"));
			assertEquals(5 , a.size());
			assertTrue(a.remove("GALACTIC"));
			assertEquals(4 , a.size());
			assertEquals(str, a.toString());
		}
}
