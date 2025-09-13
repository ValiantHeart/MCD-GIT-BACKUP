/*
	**************************************************************************
	 CandidateList class for Project 2 of CS211-220 (Spring 2017)
	 Coded by Jeffrey Wilhite	
	**************************************************************************
*/
public class CandidateList
{
	//initializing instance variables
	public String[] candidates;
		
	public CandidateList() //Default Constructor. *COMPLETE*
	{
	this.candidates = new String[0]; //assigning candidates a String array of length 0 = "{}".
	}
	
	public CandidateList(String[] candidates) //Constructor for providing a list of candidates.*COMPLETE*
	{
		this.candidates = candidates; //assigns var candidates to the instance variable
	}
	
	public CandidateList copy() //Makes a new CandidateList Object with the same candidates list and returns it.*COMPLETE*
	{
		CandidateList Copycat = new CandidateList(this.candidates); //should call constructor type 2 and creates new instances of class object.
		return Copycat; //returns object reference.
	}
	
	public int size() //Returns a count of candidates on the list.*COMPLETE*
	{
		int counter = 0; //initializes and sets the value for the counter at 0.
		for (int i = 0; i < this.candidates.length; i++) //explicitly scans the class's candidates list.
		{
			counter++; //ups the counter for every index.
		}
		return counter; //returns the count of every index.
	
	}
	
	public String get(int index) //retrieves the candidate at the provided index value.*COMPLETE*
	{
		if (index > this.candidates.length) //checks if the index is greater than the length of the array.
		{
			throw new RuntimeException("Index out of bounds"); //throws runtime exception if true
		}
		
		return this.candidates[index]; //returns the value for the location provided.
	}
	
	public void set(int index, String candidate) //sets the value at index provided to the candidate provided.*COMPLETE*
	{
		if (index > this.candidates.length) //checks if the index is greater than the length of the array.
		{
			throw new RuntimeException("Index out of bounds"); //throws runtime exception if true
		}
		
		candidates[index] = candidate; //sets the index to the candidate provided.
		
	}
	
	public int indexOf(String candidate) //finds the index of the candidate provided.*COMPLETE*
	{
		int ErrNum = -1; //initializes and sets the value for the error number at -1
		for (int i = 0; i<this.candidates.length; i++) //for every index in the array
		{
			if(candidates[i].equals(candidate)) //if the value at the index matches the candidate provided.
			{
				return i; //returns the candidate's index.
			}
		}
		
		return ErrNum; //default returns ErrNum
	}
	
	public void add(String candidate) //adds a candidate to the end of the list.*COMPLETE*
	{
		if (candidate == null) //checks if candidate is null
		{
			throw new RuntimeException("Candidate is null"); //throws runtime exception if true
		}
		
		String[] candidates2 = new String[candidates.length + 1]; //creates a new array one bigger in size than the current one in the class.
		for (int i = 0; i<candidates.length; i++) //for every index in the arrray
		{
			candidates2[i]=candidates[i]; //copys the contents before the end of the list into thier corresponding slots
		}
		candidates2[candidates2.length - 1] = candidate; //adds the candidate to the end of the new array.
		this.candidates = candidates2; //Overwrite the existing class instance of candidates with new list
	}
	
	public void addAt(int index, String candidate) //adds the candidate provided at the index provided. *COMPLETE*
	{
		if (candidate == null) //checks if candidate is null
		{
			throw new RuntimeException("Candidate is null"); //throws runtime exception if true
		}
		if (index > this.candidates.length + 1) //checks if the index is greater than the length of the array.
		{
			throw new RuntimeException("Index out of bounds"); //throws runtime exception if true
		}

		String[] candidates2 = new String[candidates.length + 1]; //creates a new array one bigger in size than the current one in the class.
		for (int i = 0; i<candidates.length; i++) //looks through each index of the old array
		{
			if (i > index) //after the target index is reach:
			{
				candidates2[i+1] = candidates[i]; //adds the candidate that were in front of the target index slot to the next slot ahead.
			}
			if (i == index) //when the target index is reached:
			{
				candidates2[i] = candidate; //the current slot is filled with the candidate provided.
				candidates2[i+1] = candidates[i]; //adds the slot ahead of this one with the next candidate that would have been in the slot.
				continue; //continues onto the next iteration.
			}
			if (i < index)//before it reaches the target index:
			{
				candidates2[i] = candidates[i]; //copys slots before the target straight over
			}
		}
		if (candidates2[candidates2.length - 1] == null) //if the candidate at the end is null:
		{
			candidates2[candidates2.length - 1] = candidate; //overwrites the last slot with the candidate provided.
		}
		
		this.candidates = candidates2; //Overwrite the existing class instance of candidates with new list
	}
	
	public String removeAt(int index) //removes the candidate at the provided index, shifts every value over to the left.*COMPLETE*
	{
		String removed_candidate = ""; //creating and initializing the return var as Empty String
		if (index > this.candidates.length - 1) //checks if the index is greater than the length of the array.
		{
			throw new RuntimeException("Index out of bounds"); //throws runtime exception if true
		}		
		if (index < 0) //checks if the index is less than 0.
		{
			throw new RuntimeException("Index out of bounds"); //throws runtime exception if true
		}
		
		String[] candidates2 = new String[this.candidates.length - 1]; //creates a new array one smaller in size than the current one in the class.
		removed_candidate = candidates[index]; //retrieves the name of the weakest link (IE the name of the candidate to be axed... I mean removed from the race...)
		for (int i = 0; i<candidates.length; i++) //for every index in the loop
		{
			if (i>index)//what happpens after you reach it.
			{
				candidates2[i-1]=candidates[i]; //add the values in the indexs ahead of the target back 
			}
			if (i == index)//what happens when you reach it.
			{
				continue; //"Bye, Felicia" (Skip over the target index)
			}
			if (i < index)//what happens before you reach it.
			{
				candidates2[i]=candidates[i]; //copy over the values in the indexes preceeding the target index.
			}
		}

		this.candidates = candidates2; //Overwrite the existing class instance of candidates with new list
		return removed_candidate; //returns the name of the removed candidate.
	}
	
	@Override public String toString() //string representation of the CandidateList object. *COMPLETE*
	{
		String representation = "{"; //creating and initializing the return var as "{"
		for(int i = 0; i <= this.candidates.length - 1; i++) //for each index of the array:
		{
			representation += candidates[i]; //adds the string of the candidate at the index 
			if (i == this.candidates.length - 1)//checks if it reaches the end of the loop.
			{
				representation += "}"; //adds the ending parentheses. 
				break; //breaks out of the current iteration
			}
			if (i < this.candidates.length) //if there are still indexs ahead of this index
			{
				representation += ", "; //adds a comma and a space in between 
			}
		}
		if (representation.equals("{")) //if the representation is supposed to be empty.
		{
			representation += "}"; //add the end cap to it.
		}
		return representation; //returns the string representation of the class object.
	}
}