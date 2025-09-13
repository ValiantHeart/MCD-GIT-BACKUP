/*
 ****************************************************************************************
  ExceptionLogicMalformedSignal exception class for Project 4 of CS211-220 (Spring 2017)
  Coded by Jeffrey Wilhite 
 ****************************************************************************************
*/

public class ExceptionLogicMalformedSignal extends RuntimeException
{
	//Fields:
	private char bad; //character which caused trouble.
	private String msg; //message for user relating to error.
	
	//Constructors:
	public ExceptionLogicMalformedSignal(char gross_af, String mgs) //Constructor Type 0: character reference and message. (COMPLETE)
	{
		super(mgs); //calls parent constructor for msg.
		this.bad = gross_af; //assigns gross_af to the character which is not nice.
	}
	
	//Methods:
	
		//getters:
		public char getBad() //This method gets the char stored in bad within the class: (COMPLETE)
		{
			return this.bad; //returns the class's bad.
		}
		
		public String getMsg() //This method gets the message stored within the class: (COMPLETE)
		{
			return this.msg; //returns the class's msg.
		}
	
		//setters:
		public void setBad(char bad) //This method sets bad to the provided char: (COMPLETE)
		{
			this.bad = bad; //assigns the class's bad to the char provided.
		}
		
		public void setMsg(String msg) //This method sets msg to the provided String: (COMPLETE)
		{
			this.msg = msg; //assigns the class's msg to the String provided.
		}
	
		//unique class methods:
		@Override public String toString() //This method returns the class's message: (COMPLETE)
		{
			return this.msg; //returns the class's msg value.
		}
}