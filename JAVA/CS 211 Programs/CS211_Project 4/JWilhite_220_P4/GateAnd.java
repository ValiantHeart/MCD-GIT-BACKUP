/*
   ********************************************************
    GateAnd class for Project 4 of CS211-220 (Spring 2017)
    Coded by Jeffrey Wilhite 
   ********************************************************
*/

//Necessary Imports:
import java.util.*;

public class GateAnd extends Gate
{
	//Constructors:
	public GateAnd(List<Wire> ins, Wire output) //Constructor Type 0: input and output provided. (COMPLETE)
	{
		super("AND", ins, output); //calls parent constructor with name = "AND" , ins = ins, and output = output.
	}
	
	//Methods:
	@Override public boolean propagate() //This method applies the Gate-specific logic on the input signals, returns true if the signal propagates properly, false otherwise: (COMPLETE)
	{
		Wire old_output = this.getOutput(); //creates a copt of the old output for testing later...
		List<Wire> inputs= this.getInputs(); //retrieves a copy of the inputs list
		
		boolean has_highs = false; //flag to indicate whether there were HI signals detected.
		boolean has_lows = false; //flag to indicate whether there were LO signals detected.
		boolean has_unknowns = false; //flag to indicate whether there were X signals detected.
		boolean props = false; //flag to indicate whether the signal propagates.
		
		for(int i = 0; i < inputs.size(); i++) //for each index in the list of inputs:
		{
			if((inputs.get(i)).getSignal() == Signal.LO) //if the wire's signal is LO:
			{
				if (((this.getOutput()).getSignal()) == Signal.LO) //if the output siganl is already LO:
				{
					props = false; //prop = flase as the signal didn't propagate.
				}
				else //otherwise:
				{
					props = true; //prop = true as the signal propagated.
				}
				(this.getOutput()).setSignal(Signal.LO); //set the output wire's signal to LO
				return props; //retrun prop.
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
		
		if(has_highs == true && has_lows == false && has_unknowns == false) //if there were only HI signals on the wire
		{
			(this.getOutput()).setSignal(Signal.HI); //set the output signal to HI
			return true; //return true.
		}
		
		if(has_highs == true && has_lows == false && has_unknowns == true) //if there were HI's and X'S, but no LO's:
		{
			(this.getOutput()).setSignal(Signal.X); //sets the output signal to unknown (X).
			return true; //return true
		}
		
		return props; //default return props.
	}
	
}