/*
	**************************************************************************
	 VoteList class for Project 2 of CS211-220 (Spring 2017)
	 Coded by Jeffrey Wilhite	
	**************************************************************************
*/
public class VoteList
{
	//initializing instance variables
	public Vote[] votes;
		
	public VoteList() //Default Constructor. *COMPLETE*
	{
	this.votes = new Vote[0]; //assigning votes a String array of length 0 = "{}".
	}
	
	public VoteList(Vote[] votes) //Constructor for providing a list of votes.*COMPLETE*
	{
		this.votes = votes; //assigns var votes to the instance variable
	}
	
	public VoteList copy() //Makes a new VoteList Object with the same Votes list and returns it.*COMPLETE*
	{
		VoteList Copycat = new VoteList(this.votes); //should call constructor type 2 and creates new instances of class object.
		return Copycat; //returns object reference.
	}
	
	public int size() //Returns a count of votes on the list.*COMPLETE*
	{
		int counter = 0; //initializes and sets the value for the counter at 0.
		for (int i = 0; i < this.votes.length; i++) //explicitly scans the class's votes list.
		{
			counter++; //ups the counter for every index.
		}
		return counter; //returns the count of every index.
	
	}
	
	public Vote get(int index) //retrieves the vote at the provided index value.*COMPLETE*
	{
		if (index > this.votes.length) //checks if the index is greater than the length of the array.
		{
			throw new RuntimeException("Index out of bounds"); //throws runtime exception if true
		}
		
		return this.votes[index]; //returns the value for the location provided.
	}
	
	public void set(int index, Vote vote) //sets the value at index provided to the Vote provided.*COMPLETE*
	{
		if (index > this.votes.length) //checks if the index is greater than the length of the array.
		{
			throw new RuntimeException("Index out of bounds"); //throws runtime exception if true
		}
		
		votes[index] = vote; //sets the index to the vote provided.
		
	}
	
	public int indexOf(Vote vote) //finds the index of the vote provided.*COMPLETE*
	{
		int ErrNum = -1; //initializes and sets the value for the error number at -1
		for (int i = 0; i<this.votes.length; i++) //for every index in the array
		{
			if(votes[i] == vote) //if the value at the index matches the vote provided.
			{
				return i; //returns the vote's index.
			}
		}
		
		return ErrNum; //default returns ErrNum
	}
	
	public void add(Vote vote) //adds a vote to the end of the list.*COMPLETE*
	{
		if (vote == null) //checks if vote is null
		{
			throw new RuntimeException("vote is null"); //throws runtime exception if true
		}
		
		Vote[] votes2 = new Vote[votes.length + 1]; //creates a new array one bigger in size than the current one in the class.
		for (int i = 0; i < votes.length; i++) //for every index in the arrray
		{
			votes2[i] = votes[i]; //copys the contents before the end of the list into thier corresponding slots
		}
		votes2[votes2.length - 1] = vote; //adds the vote to the end of the new array.
		this.votes = votes2; //Overwrite the existing class instance of votes with new list
	}
	
	public void addAt(int index, Vote vote) //adds the Vote provided at the index provided. *COMPLETE*
	{
		if (vote == null) //checks if vote is null
		{
			throw new RuntimeException("Vote is null"); //throws runtime exception if true
		}
		if (index > this.votes.length + 1) //checks if the index is greater than the length of the array.
		{
			throw new RuntimeException("Index out of bounds"); //throws runtime exception if true
		}

		Vote[] votes2 = new Vote[votes.length + 1]; //creates a new array one bigger in size than the current one in the class.
		for (int i = 0; i<votes.length; i++) //looks through each index of the old array
		{
			if (i>index) //after the target index is reach:
			{
				votes2[i+1] = votes[i]; //adds the Vote that were in front of the target index slot to the next slot ahead.
			}
			if (i == index) //when the target index is reached:
			{
				votes2[i] = vote; //the current slot is filled with the vote provided.
				votes2[i+1] = votes[i]; //adds the slot ahead of this one with the next vote that would have been in the slot.
				continue; //continues onto the next iteration.
			}
			if (i < index) //before it reaches the target index:
			{
				votes2[i] = votes[i]; //copys slots before the target straight over
			}
		}
		if (votes2[votes2.length - 1] == null) //if the vote at the end is null:
		{
			votes2[votes2.length - 1] = vote; //overwrites the last slot with the vote provided.
		}
		
		this.votes = votes2; //Overwrite the existing class instance of votes with new list
	}
	
	public Vote removeAt(int index) //removes the vote at the provided index, shifts every value over to the left.*COMPLETE*
	{
		Vote removed_vote = null; //creating and initializing the return var as Empty String
		if (index > this.votes.length - 1) //checks if the index is greater than the length of the array.
		{
			throw new RuntimeException("Index out of bounds"); //throws runtime exception if true
		}		
		if (index < 0) //checks if the index is less than 0.
		{
			throw new RuntimeException("Index out of bounds"); //throws runtime exception if true
		}
		
		Vote[] votes2 = new Vote[this.votes.length - 1]; //creates a new array one smaller in size than the current one in the class.
		removed_vote = votes[index]; //retrieves the name of the weakest link (IE the name of the vote to be axed... I mean removed from the race...)
		for (int i = 0; i < votes.length; i++) //for every index in the loop
		{
			if (i > index) //what happpens after you reach it.
			{
				votes2[i-1] = votes[i]; //add the values in the indexs ahead of the target back 
			}
			if (i == index) //what happens when you reach it.
			{
				continue; //"Bye, Felicia's Vote" (Skip over the target index)
			}
			if (i < index) //what happens before you reach it.
			{
				votes2[i] = votes[i]; //copy over the values in the indexes preceeding the target index.
			}
		}

		this.votes = votes2; //Overwrite the existing class instance of votes with new list
		return removed_vote; //returns the name of the removed vote.
	}
	
	@Override public String toString() //string representation of the VoteList object. *COMPLETE*
	{
		String representation = "{"; //creating and initializing the return var as "{"
		for(int i = 0; i <= this.votes.length - 1; i++) //for each index of the array:
		{
			representation += votes[i]; //adds the string of the Vote at the index 
			if (i == this.votes.length - 1)//checks if it reaches the end of the loop.
			{
				representation += "}"; //adds the ending parentheses. 
				break; //breaks out of the current iteration
			}
			if (i < this.votes.length) //if there are still indexs ahead of this index
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