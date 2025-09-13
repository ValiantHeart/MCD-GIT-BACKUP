/*
   ********************************************************
    GateNot class for Project 4 of CS211-220 (Spring 2017)
    Coded by Jeffrey Wilhite 
   ********************************************************
*/

//Necessary Imports:
import java.util.*;

public class GateNot extends Gate
{
	//Constructors:
	public GateNot(Wire input, Wire output) //Constructor Type 0: input and output provided. (COMPLETE)
	{
		super("NOT", work_around(input), output); //calls parent constructor with name = "NOT" , ins = the output of work_around(input), and output = output.
	}
	
	//Methods:
	
		//unique class methods:
		@Override public boolean propagate() //This method applies the Gate-specific logic on the input signals, returns true if the signal propagates properly, false otherwise: (COMPLETE)
		{
			//creates a copt of the old output for testing later...
			Wire old_output = this.getOutput(); //creates a copt of the old output for testing later...
			Signal old_out = old_output.getSignal(); //retrieve's the siganl on the old output wire and stores it in Signal old_out.
			
			Wire input = (this.getInputs()).get(0); //retrieves the single input wire from the list of inputs of length 1 and assigns it to variable input.
			Signal sig = input.getSignal(); //retrives the signal on the input wire.
			(this.getOutput()).setSignal(sig.invert()); //assigns the signal on the output wire to the inverted signal of the input wire.
			
			if (old_out == Signal.LO && (this.getOutput()).getSignal() == Signal.LO) //if the current signal is LO and the old signal was also LO (if there was no inverting performed):
			{
				return false; //return false, as the signal didn't propagate properly.
			}
			else if (old_out == Signal.HI && (this.getOutput()).getSignal() == Signal.HI) //if the current signal is HI and the old signal was also HI (if there was no inverting performed):
			{
				return false; //return false, as the signal didn't propagate properly.
			}
			else //otherwise:
			{
				return true; //return true
			}
			

		}
		
		public static <T> ArrayList<T> work_around(T x) //work around method => creates a generic ArrayList of one containing the input provided in the constructor:
		{
			ArrayList<T> a = new ArrayList<T>(); //creates a generic ArrayList a.
			a.add(x); //adds the variable provided to a.
			return a; //returns a.
		}

}