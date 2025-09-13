/*
	**************************************************************************
	 Vote class for Project 2 of CS211-220 (Spring 2017)
	 Coded by Jeffrey Wilhite	
	**************************************************************************
*/
public class Vote
{
	//initializing instance variables.
	private CandidateList candidates; 
	private int[] selections; 
	
	public Vote(CandidateList candidates) //Constructor type 1 (CandidatesList only). *COMPLETE*
	{
		this.candidates = candidates; //creates a CandidatesList object for the class.
		this.selections = new int[candidates.size() - 1]; //creates a selection full of 0's witha length corresponding to the amount of candidates present in CandidateList. 
	}
	
	public Vote(CandidateList candidates, int[] selections) //Constructor type 2 (CandidateList and selections specifed). *COMPLETE*
	{
		this.candidates = candidates; //assigns the CandidateList object candidates to be class's CandidateList candidates.
		this.selections = selections; // assigns the provided selections array to the class.
	}
	
	public int indexOfRank(int rank) //returns index value for the specified rank from the selections array. *COMPLETE*
	{
		int index = -1; //initializes value index as -1 (for defaultive purposes).
		for (int i = 0; i < selections.length; i++) // for each value in the array:
		{
			if (selections[i] == rank) // checks if the value of the index is equal to the rank provided.
			{
				index = i; //index becomes the index value of the rank's slot in the array.
				return index; //returns the index value in selctions.
			}
		}
		
		if (index == -1) //checks if the value is the default
		{
			throw new RuntimeException("Rank not found, index == null"); //throws exception if true
		}
		return index; //returns the default value for index.
	}
	
	public String getRankedCandidate(int rank) //retrieves the cvandidate's name with the rank in selections provided. *COMPLETE*
	{
		String selection = "";
		for (int i = 0; i<selections.length; i++) //for each value in selection:
		{
			if(selections[i] == rank) //if the value for the index equals the rank provided:
			{
				selection += candidates.get(i); //add the candidate's name to the empty string.
				return selection; //returns that candidate's name.
			}
		}	
		if (selection == "") //if selection is still the default:
		{
			throw new RuntimeException("No rank found of that number."); //throw RuntimeException.
		}
		return selection; //default return statement
	}
	
	public void recordChoice(int rank, int index) //records a selection given a rank and a candidate. *COMPLETE*
	{
		if (index > candidates.size() || index < 0) //Checks if index is outside the range of index variables in selections.
		{
			throw new RuntimeException("Index Invalid"); //throws RuntimeException if the above is true.
		}
		if (rank > selections.length) //Checks if rank is greater than selections' index range.
		{
			throw new RuntimeException("Rank Invalid"); //throws RuntimeException if the above is true.
		}
		if (rank < 0) //Checks if rank is less than 0.
		{
			throw new RuntimeException("Rank Invalid"); //throws RuntimeException if the above is true.
		}
		for (int i = 0; i < selections.length;i++) //checks if rank is already present in selections
		{
			if (selections[i] == rank) //if the rank is already present in selections.
			{
				throw new RuntimeException("Rank Invalid"); //throws RuntimeException if the above is true.
			}
		}
		
		selections[index] = rank; //inputs the rank at the provided index, if it passes the above checks.
	}
	
	public void recordChoice(int rank, String candidate) //records a selection given a rank and a candidate. *COMPLETE*
	{
		boolean candidate_found = false; //boolean flag variable to indicate whether a candidate with matching name has been found, default is false.
		
		if (rank > selections.length) //Checks if rank is greater than selections' index range.
		{
			throw new RuntimeException("Rank Invalid"); //throws RuntimeException if the above is true.
		}
		if (rank < 0) //Checks if rank is less than 0.
		{
			throw new RuntimeException("Rank Invalid"); //throws RuntimeException if the above is true.
		}
		for (int i = 0; i < selections.length;i++) //checks if rank is already present in selections
		{
			if (selections[i] == rank) //if the rank is already present in selections.
			{
				throw new RuntimeException("Rank Invalid"); //throws RuntimeException if the above is true.
			}
		}
		for (int i = 0; i < this.candidates.size(); i++) //for every candidate in the class's Candidate list object:
		{
			if (candidates.get(i) == candidate) //if the candidates name is at the index:
			{
				candidate_found = true; //flips the flag to true.
				selections[i] = rank; //updates the candidate's rank to the provided rank.
			}
		}
		if (candidate_found == false) //Checks if the flag is false:
		{
			throw new RuntimeException("Candidate not found"); //throws a RuntimeException if true
		}
		
	}
	
 	public String bestChoice(CandidateList candidates)
	{
		String best_choice = "";
		for (int i = 0; i < selections.length; i++)
		{
			if (selections[i] == (selections.length - 2))
			{
				best_choice = getRankedCandidate(selections[i]);
			}
		}
		if (best_choice == "")
		{
			return null; //returns null
		}
		if (this.candidates.candidates != candidates.candidates) //if comparing two different candidates sets
		{
			return null; //returns null
		}
		
		return best_choice;
		
	} 
	
	public CandidateList copyOfCandidateList() //creates a copy of the class's CandidateList object. *COMPLETE*
	{
		CandidateList Copycat = candidates.copy(); //uses the candidate's internal copy method to create a copy of the object.
		return Copycat; //returns the object Copycat.
	}
	
	public boolean validateVote() //checks to make sure if the vote casted is valid and correct.
	{
		boolean VoteValid = true; //boolean flag set to default of true.
		
		if (candidates == null) //if the candidates list is equal to null
		{
			VoteValid = false; //flips the flag to false, if check is true.
			return VoteValid; //returns the flag.
		}
		if (selections == null || selections.length != candidates.size())//checks if the selections array is null or if the lengths don't match:
		{
			VoteValid = false; //flips the flag to false, if check is true.
			return VoteValid; //returns the flag.
		}
		for (int i = 0; i < selections.length; i++) //for each of the vote's ranks in it's selection:
		{
			if (selections[i] > selections.length) //Checks if rank is greater than selections' index range.
			{
				VoteValid = false; //flips the flag to false, if check is true.
				return VoteValid; //returns the flag.
			}
			if (selections[i] <= 0) //Checks if rank is less than 0.
			{
				VoteValid = false; //flips the flag to false, if check is true.
				return VoteValid; //returns the flag.
			}
			for (int j = 0; j < selections.length; j++)
			{
				if (selections[i] == selections[j] && i != j) //if the rank is already present in selections.
				{
					VoteValid = false; //flips the flag to false, if check is true.
					return VoteValid; //returns the flag.
				}
			}
		}
		
		return VoteValid; //default return.
	}
	
	@Override public String toString() //Returns the string representation of the Vote. *COMPLETE*
	{
		String representation = "{"; //creating and initializing the return var as "{"
		for (int i = 0; i < selections.length;i++) //for each value in selections array:
		{
			representation += candidates.get(i) + ":" + selections[i]; // adds the string of the form "'A':'#'" to the string representation
			
			if (i != selections.length - 1) // if it's not at the last index value:
			{
				representation += ", "; //adds the seperator to the string
			}
		}
		
		representation += "}"; //adds the end cap to the string representation.
		return representation; //returns the string representation.
		
	}
	
 	@Override public boolean equals(Object other) //compares one instance of a vote to another to see if they are the same vote. *COMPLETE*
	{
		if ( !(other instanceof Vote)) 
		{
			return false;
		}
		else
		{
			return true;
		}
	} 
	
}