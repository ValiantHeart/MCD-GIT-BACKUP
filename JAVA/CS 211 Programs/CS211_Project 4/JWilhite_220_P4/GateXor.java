/*
   ********************************************************
    GateXor class for Project 4 of CS211-220 (Spring 2017)
    Coded by Jeffrey Wilhite 
   ********************************************************
*/

//Necessary Imports:
import java.util.*;

public class GateXor extends Gate
{
	//Constructors:
	public GateXor(List<Wire> ins, Wire output) //Constructor Type 0: input and output provided. (COMPLETE)
	{
		super("XOR", ins, output); //calls parent constructor with name = "XOR" , ins = ins, and output = output.
	}
	
	//Methods:
	@Override public boolean propagate() //This method applies the Gate-specific logic on the input signals, returns true if the signal propagates properly, false otherwise: (COMPLETE)
	{
		Wire old_output = this.getOutput(); //creates a copt of the old output for testing later...
		List<Wire> inputs= this.getInputs(); //retrieves a copy of the inputs list
		
		boolean has_highs = false; //flag to indicate whether there were HI signals detected.
		boolean has_lows = false; //flag to indicate whether there were LO signals detected.
		boolean has_unknowns = false; //flag to indicate whether there were X signals detected.
		
		int HI_count = 0; //int to indicate how many HI's were in the inputs.
		int LO_count = 0; //int to indicate how many LO's were on the wire.
		int X_count = 0; //int to indicate how many X's were on the wire.
		
		for(int i = 0; i < inputs.size(); i++) //for each index in the list of inputs:
		{
			if((inputs.get(i)).getSignal() == Signal.LO) //if the wire's signal is LO:
			{
				LO_count++; //increase the LO_count by 1
			}
			if((inputs.get(i)).getSignal() == Signal.HI) //if the wire's signal is HI:
			{
				HI_count++;  //increase the HI_count by 1
			}			
			if((inputs.get(i)).getSignal() == Signal.X) //if the wire's signal is X:
			{
				X_count++;  //increase the X_count by 1
			}
		}
		
		if(HI_count == 1 && X_count == 0) //if the HI count is exactly 1 and there were no X's counted:
		{
			(this.getOutput()).setSignal(Signal.HI); ////set the output signal to HI.
			return true; //return true.
		}

		if(HI_count > 1 || (HI_count == 0 && LO_count > 0 && X_count == 0)) //if the HI count is greater than 1 or HI count = 0, LO_count is greater than 0, and X_count = 0:
		{
			if(this.getOutput().getSignal() == Signal.LO) //if the signal on the output was already LO, at this point:
			{
				return false; //return false, as the signal didn't propagate.
			}
			else //otherwise:
			{
				(this.getOutput()).setSignal(Signal.LO); //set the output signal to LO.
				return true; //return true.
			}
		}
		
		if(HI_count == 1 && X_count > 0) //if the HI_count is exactly 1 and the X_count is greater than 1:
		{
			(this.getOutput()).setSignal(Signal.X); //set the output signal to X.
			return true; //return true.
		}
		
		return false; //default return is false.

	}
	
}