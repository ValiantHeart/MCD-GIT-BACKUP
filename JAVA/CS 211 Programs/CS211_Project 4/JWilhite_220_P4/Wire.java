/*
   *****************************************************
    Wire class for Project 4 of CS211-220 (Spring 2017)
    Coded by Jeffrey Wilhite 
   *****************************************************
*/

public class Wire
{
	//Fields:
	private Signal signal; //Signal carried by the wire.
	private String name; //Wire name, useful for humans, unused by program.
	
	//Constructors:
	public Wire(String name) //constructor type 0: name only
	{
		this.signal = Signal.X; //sets the wire signal value equal to X.
		this.name = name; //sets the wire's name to the provided String.
	}
	
	//Methods:
		//getters
		public Signal getSignal() //gets the signal stored on the wire. (COMPLETE)
		{
			return this.signal; //returns the class's signal value.
		}
		public String getName() //gets the name attached to the wire. (COMPLETE)
		{
			return this.name; //returns the class's name value.
		}
		
		//setters
		public void setSignal(Signal signal) //sets the signal of the wire to value provided. (COMPLETE)
		{
			this.signal = signal; //overwrites the signal value stored in the object to the provided.
		}
		public void setName(String name) //sets the name of the wire to the String provided (COMPLETE)
		{
			this.name = name; //overwrites object's name to the one provided.
		}
	
		//unique class methods:
		@Override public String toString() //returns a string representation of the class. in form "name:value". (COMPLETE)
		{
			String retrn_str = ""; //creates empty String named retrn_str.
			retrn_str += this.name + ":" + this.signal; //adds the name of the object and a colon and the value for signal stored within the class.
			return retrn_str; //returns retrn_str.
		}
		
		@Override public boolean equals(Object other) //compares the provided object and checks if it's equal to this class. (COMPLETE)
		{
			
			if (((Wire)other).signal == this.signal && ((Wire)other).name.equals(this.name)) //if the casted Wire of the other Object provided's name and signal match that of the class: 
			{
				return true; //returns true.
			 } 
			else //if it doesn't match:
			{
				return false; //return false.
			} 
		}
}