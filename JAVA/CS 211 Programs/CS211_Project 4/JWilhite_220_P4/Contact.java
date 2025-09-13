/*
   ********************************************************
    Contact class for Project 4 of CS211-220 (Spring 2017)
    Coded by Jeffrey Wilhite 
   ********************************************************
*/

//Necessary Imports:
import java.util.*;

public class Contact implements Logic
{
	//Fields:
	private Wire in, out; //two wires, one in, one out.
	private boolean inbound; //indicates whether the wire in is inbound or outbound => used in the toString and equals methods.
	
	//Constructors:
	public Contact(Wire in, Wire out, boolean inbound) //Constructor Type 0: Wires in and out, and boolean inbound provided. (COMPLETE)
	{
		this.in = in; //assigns in to the class's in.
		this.out = out; //assigns out to the class's out.
		this.inbound = inbound; //assigns inbound to the class's inbound.
	}
	
	//Methods:
	
		//getters
		public Wire getIn() //gets the class's in variable: (COMPLETE)
		{
			return this.in; //returns the class's in value.
		}
		
		public Wire getOut() //gets the class's out variable: (COMPLETE)
		{
			return this.out; //returns the class's out value.
		}
		
		public boolean getInbound() //gets the class's inbound variable: (COMPLETE)
		{
			return this.inbound; //returns the class's inbound value.
		}
	
		//setters
		public void setIn(Wire in) //sets the class's in value: (COMPLETE)
		{
			this.in = in; //sets the class's in value to the one provided.
		}
		
		public void setOut(Wire out) //sets the class's out value: (COMPLETE)
		{
			this.out = out; //sets the class's out value to the one provided.
		}
		
		public void setInbound(boolean inbound) //sets the class's Inbound value: (COMPLETE)
		{
			this.inbound = inbound; //sets the class's inbound value to the one provided.
		}
	
		//Logic interface methods:
		@Override public void feed(List<Signal> inputSignals) //this method takes a list of signals and attaches them to the signals on the wires in the list of inputs: (COMPLETE)
		{
			if (inputSignals.size() != 1) //if the lengths of the provided list of Signals doesn't equal the expected length of 1:
			{
				throw new ExceptionLogicParameters(true, 1, inputSignals.size()); //raises ExceptionLogicParameters exception if true with inputsRelated = true, expected = 1, and found = inputSignals.size().
			}
			
			for (int i = 0; i < 1; i++) //for each signal in the List inputSignals:
			{
				in.setSignal(inputSignals.get(i)); //set the signal on the in wire to the signal in inputSignals list. 
			}
			
		}
		
		@Override public void feed(String inputSignals) //this method takes a string of inputs and feeds them to the signals on the wires in the list of inputs: (COMPLETE)
		{
			if (inputSignals.length() != 1) //if the lengths of the provided String of Signals doesn't equal the expected length of 1:
			{
				throw new ExceptionLogicParameters(true, 1, inputSignals.length()); //raises ExceptionLogicParameters exception if true with inputsRelated = true, expected = 1, and found = inputSignals.length().
			}
			
			List<Signal> signals = Signal.fromString(inputSignals); //creates a list of signals. called signals, from the fromString method in the Signal class.
			feed(signals);//uses the List<Signal> version of feed to complete the feeding of the signals.
		}
		
		@Override public List<Signal> read() //this method returns a Signal list containing the signal on the output wire: (COMPLETE)
		{
			ArrayList<Signal> outputSig = new ArrayList<Signal>(); 
			outputSig.add((this.out).getSignal()); 
			return outputSig; 
		}
		
		@Override public List<Signal> inspect(List<Signal> inSigs) //this method provides inputs to a gate and returns the output: (COMPLETE)
		{
			feed(inSigs); //uses the feed method to plug in the signals contained within the list to the gate. 
			this.propagate(); //propagates the signals across the contact.
			return this.read(); //returns the current output of the read method.
		}
		
		@Override public String inspect(String inStr) //this method does inspect with a string instead of list: (COMPLETE)
		{
			feed(inStr); //uses the String version of feed to provide the imputs to the gate. 
			String repr = ""; //creates an empty String named repr.
			repr += (this.out).toString(); //adds the string of output wire to repr.
			return repr; //returns repr.
		}
		
		@Override public boolean propagate() //this method sets the incoming signal on the in wire to the one going out on the out wire, then checks if the intended signal is going across the contact: (COMPLETE)
		{
			switch((this.in).getSignal())
			{
				case HI:
					(this.out).setSignal(Signal.HI);
					break;
				case LO:
					(this.out).setSignal(Signal.LO);
					break;
				case X:
					(this.out).setSignal(Signal.X);
					break;
			}
			
			if(in.getSignal() != out.getSignal()) //if the in sig is not equal to the out sig:
			{
				return false; //return false
			}
			else //otherwise:
			{
				return true; //return true:
			}
		}
	
		
		//unique class methods:
		@Override public String toString() //This method returns a string representation of the object: (COMPLETE)
		{
			String repr = ""; //creates an empty string called repr.
			
			if(in.getName() == out.getName()) //if the in and out wire have the same name:
			{
				repr += in.getName() + ":" + out.getSignal(); //adds the name of the in wire and the outgoing signal seperated by a colon to the string.
				return repr; //then returns said string.
			}
			if(inbound != true) //if the inbound flag is not true (false):
			{
				repr += "(" + in.getName() + ")" + out.getName() + ":" + out.getSignal(); //adds the in Wire's name surrounded by parentheses to the repr string, followed by the out Wire's name, a colon, andthe outgoing signal on the wire.
			}
			else //if the contact is outgoing (not inbound):
			{
				repr += in.getName() + "(" + out.getName() + ")" + ":" + out.getSignal(); //adds the in Wire's name and the out Wire's name surrounded by parentheses to the repr string, followed by a colon and the out going signal on the out wire.
			}
			
			return repr; //return the built up repr string.
		}
		
		@Override public boolean equals(Object o) //This method returns true if object o is equal to the object calling the method, returns false otherwise: (COMPLETE)
		{
			boolean is_equals = true; //sets the return bool to true before running checks.
			
			if(!((Contact)o).getIn().equals(this.getIn())) //if the casted contact object's in value is not equal to this object's in:
			{
				return is_equals = false; //returns false.
			}		
			if( !((Contact)o).getOut().equals(this.getOut())) //if the casted contact object's out value is not equal to this object's out:
			{
				return is_equals = false; //returns false.
			}		
			if(((Contact)o).getInbound() != this.inbound) //if the casted contact object's inbound value is not equal to this object's inbound:
			{
				return is_equals = false; //returns false.
			}
			
			return is_equals; //if it reached this point and past all checks, the object is equal to the object calling the method.

		}
}