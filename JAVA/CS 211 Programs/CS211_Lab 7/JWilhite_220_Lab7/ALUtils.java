//Necessary Imports:
import java.util.*;

public class ALUtils
{

	/*
		The reverse method parses through the provided string arraylist from the back to the front 
		adding each variable in the list to the return list as it goes:
		Effectively, flipping/reversing the list around.
	*/
	public static ArrayList<String> reverse(ArrayList<String> a) //(COMPLETE)
	{
		ArrayList<String> rvrsd_ars = new ArrayList<String>(); //creates a new ArrayList named rvrsd_ars of type String.
		//V-- the following for loop goes from back to front of a and populates rvrsd_ars with said values. --V
		for (int i = a.size() - 1;i >= 0;i--) 
		{
			rvrsd_ars.add(a.get(i));
		}
		return rvrsd_ars; //returns rvrsd_ars.
	}

	/*
		The rotate method shifts the contents of an ArrayList over by a provided amount (int shift),
		wrapping highger index slots around to lower slots. If the provided shift amount is greater 
		than the number of slots in the array, it will automatically reduce it to within the Lists size.
	*/
	
	public static ArrayList<Integer> rotate(ArrayList<Integer> a, int shift) //(COMPLETE)
	{
		ArrayList<Integer> shifted = new ArrayList<Integer>(); //createsa new ArrayList called shifted of type Integer.
		
		if (a.isEmpty() == true) //checks if the array is empty
		{
			return a; //returns a, if true
		}
		
		while (shift > a.size()) // while the shift value is greater than the size of the array provided:
		{
			shift -= a.size(); //reduce the shift down to within the size of the array. 
		}
		
		//V The following for loop grabs the values ahead of the shift-spot and adds them to the array first. V.
		for (int i = (a.size() - shift); i < a.size(); i++)
		{
			shifted.add(a.get(i));
		}
		
		//V The following array then grabs the remain values before those that were shifted to the front and adds them to shifted V.
		for (int i = 0; i < (a.size() - shift); i++)
		{
			shifted.add(a.get(i));
		}
		return shifted; //returns shifted
	}

}