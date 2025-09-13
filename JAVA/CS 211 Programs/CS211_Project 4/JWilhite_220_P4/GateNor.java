/*
   ********************************************************
    GateNor class for Project 4 of CS211-220 (Spring 2017)
    Coded by Jeffrey Wilhite 
   ********************************************************
*/

//Necessary Imports:
import java.util.*;

public class GateNor extends Gate
{
	//Constructors:
	public GateNor(List<Wire> ins, Wire output) //Constructor Type 0: input and output provided. (COMPLETE)
	{
		super("NOR", ins, output); //calls parent constructor with name = "NOR" , ins = ins, and output = output.
	}
	
	//Methods:
	@Override public boolean propagate() //This method applies the Gate-specific logic on the input signals, returns true if the signal propagates properly, false otherwise: (COMPLETE)
	{
		Wire old_output = this.getOutput(); //creates a copt of the old output for testing later...
		List<Wire> inputs= this.getInputs(); //retrieves a copy of the inputs list
		
		boolean has_highs = false; //flag to indicate whether there were HI signals detected.
		boolean has_lows = false; //flag to indicate whether there were LO signals detected.
		boolean has_unknowns = false; //flag to indicate whether there were X signals detected.
		
		for(int i = 0; i < inputs.size(); i++) //for each index in the list of inputs:
		{
			if((inputs.get(i)).getSignal() == Signal.LO) //if the wire's signal is LO:
			{
				has_lows = true; //has_lows = true.
			}
			if((inputs.get(i)).getSignal() == Signal.HI) //if the signal is HI:
			{
				has_highs = true; //has_highs = true.
			}			
			if((inputs.get(i)).getSignal() == Signal.X) //if the signal is unknown (X):
			{
				has_unknowns = true; //has_unknowns = true.
			}
		}
		
		if(has_highs == true) //if any of the inputs are HI:
		{
			(this.getOutput()).setSignal(Signal.LO); //output LO.
			return true; //return true.
		}
		
		if(has_highs == false && has_lows == true && has_unknowns == true) // if only LO's and X's were detected.
		{
			(this.getOutput()).setSignal(Signal.X); //set the output signal to X.
			return true; //return true.
		}
		
		if(has_lows == true && has_highs == false && has_unknowns == false) //if there were LO's and no HI's and X's:
		{
			if(this.getOutput().getSignal() == Signal.HI) //if the signal on the output was already LO, at this point:
			{
				return false; //return false, as the signal didn't propagate.
			}
			else //otherwise:
			{
				(this.getOutput()).setSignal(Signal.HI); //set the output signal to LO.
				return true; //return true.
			}
		}
		
		return false; //default return of false.
	}
	
}