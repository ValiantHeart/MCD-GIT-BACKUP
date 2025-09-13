/*
 ***********************************************************************************
  ExceptionLogicParameters exception class for Project 4 of CS211-220 (Spring 2017)
  Coded by Jeffrey Wilhite 
 ***********************************************************************************
*/

public class ExceptionLogicParameters extends RuntimeException
{
	//Fields:
	private boolean inputsRelated; //boolean indicating whether the error came from inputs.
	private int expected, found; //ints representing the number of inputs and the number of outputs. 
	
	//Constructors:
	public ExceptionLogicParameters(boolean inputsRelated, int expected, int found) //Constructor Type 0: inputsRelated, expected, and found provided. (COMPLETE)
	{
		this.inputsRelated = inputsRelated; //assigns provided value for inputsRelated.
		this.expected = expected; //assigns provided value for expected.
		this.found = found; //assigns provided value for found.
	}
	
	//Methods:
	
		//getters
		public boolean getInputsRelated() //gets the boolean value stored in inputsRelated within the class: (COMPLETE)
		{
			return this.inputsRelated;
		}
		
		public int getExpected() //gets the expected nuumber of inputs stored within the class: (COMPLETE)
		{
			return this.expected;
		}	
		
		public int getFound() //gets the number of inputs found stored within the class: (COMPLETE)
		{
			return this.found;
		}
	
		//setters
		public void setInputsRelated(boolean inputsRelated) //sets the boolean value stored in inputsRelated within the class: (COMPLETE)
		{
			this.inputsRelated = inputsRelated;
		}
		
		public void setExpected(int expected) //sets the expected number of inputs stored within the class: (COMPLETE)
		{
			this.expected = expected;
		}	
		
		public void setFound(int found) //sets the number of inputs found stored within the class: (COMPLETE) 
		{
			this.found = found;
		}
	
		//unique class nethods:
		@Override public String toString() //This method creates and returns an error message with relevant information on the error: (COMPLETE)
		{
		String msg = "\n"+ " ******  ERROR DETECTED  ******" + "\n";
			msg += ">>>    " + "Input related: " + this.inputsRelated + "    <<<" + "\n"; //line containing whether the problem is input related added to msg. 
			msg += ">>>    " + this.found + " inputs found" + "    <<<" + "\n"; //line containing number of found inputs added to msg.
			msg += ">>>    " + this.expected + " inputs expected" + "    <<<" + "\n"; //line containing number of expected inputs added to msg.
			return msg; //return msg.
		}
}