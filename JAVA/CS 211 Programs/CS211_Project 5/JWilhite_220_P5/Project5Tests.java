import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

import modepkg.*;

public class Project5Tests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("Project5Tests");
  }

	//TestMe Tests:
	@Test public void mode_test1() //tests the contains method:
	{
		ArrayList<String> xs= new ArrayList<String>();
		xs.add("high");
		xs.add("LOW");
		xs.add("low");
		xs.add("high");
		assertTrue(TestMe.contains(xs, "high"));
		assertFalse(TestMe.contains(xs, "Low"));
	}
	@Test public void mode_test2() //tests the mode identifier method:
	{
		ArrayList<String> xs= new ArrayList<String>();
		ArrayList<String> mode= new ArrayList<String>();
		xs.add("high");
		xs.add("LOW");
		xs.add("low");
		xs.add("high");
		mode.add("high");
		assertEquals(TestMe.mode(xs), mode);
	}	
	@Test public void mode_test3() //tests the uniques method
	{
		ArrayList<String> xs= new ArrayList<String>();
		ArrayList<String> uniques = new ArrayList<String>();
		xs.add("high");
		uniques.add("high");
		xs.add("LOW");
		uniques.add("LOW");
		xs.add("low");
		uniques.add("low");
		xs.add("high");
		assertEquals(TestMe.uniques(xs), uniques);
	}	
	@Test public void mode_test4() //misc
	{
		ArrayList<String> xs= new ArrayList<String>();
		xs.add("high");
		xs.add("LOW");
		xs.add("low");
		xs.add("high");
	}

}
