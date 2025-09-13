/*
   *****************************************************
    Gate class for Project 4 of CS211-220 (Spring 2017)
    Coded by Jeffrey Wilhite 
   *****************************************************
*/

//Necessary Imports:
import java.util.*;

public abstract class Gate implements Logic
{
	//Fields:
	private List<Wire> inputs; //a list of wire inputs.
	private Wire output; //a single wire output. 
	private String name; //a name, useful for humans, used only in the get/set methods for name and the .equals method.
	
	//Constructors:
	public Gate(String name, List<Wire> ins, Wire out) //Constructor Type 0: name, inputs, and output provided. (COMPLETE)
	{
		if(ins.isEmpty() == true) //checks if ins is an empty list:
		{
			throw new ExceptionLogicParameters(true, 1, 0); //raises ExceptionLogicParameters exception if true with inputsRelated = true, expected = 1, and found = 0 values.
		}
		
		this.name = name; //assigns provided name to name
		this.inputs = ins; //assigns the non-empty list Wires to inputs.
		this.output = out; //assigns Wire out to output.
	}
	
	//Methods:
	
		//getters:
		public List<Wire> getInputs() //gets the inputs of the object stored in the class: (COMPLETE)
		{
			return this.inputs; //returns the class's inputs.
		}
		
		public Wire getOutput() //gets the output of the object stored in the class: (COMPLETE)
		{
			return this.output; //returns the class's outputs.
		}
		
		public String getName() //gets the name of the object stored in the class: (COMPLETE)
		{
			return this.name; //returns the class's name.
		}
	
		//setters:
		public void setInputs(List<Wire> inputs) //sets the class's inputs list equal to the list provided: (COMPLETE)
		{
			this.inputs = inputs; //overwrites the list of inputs stored in the class to the one provided.
		}
		
		public void setOutput(Wire output) //sets the class's output equal to the Wire object provided. (COMPLETE)
		{
			this.output = output; //overwrites the output stored in the class to the one provided
		}
		
		public void setName(String name) //sets the class's name String equal to the String provided. (COMPLETE)
		{
		this.name = name; //overwrites the class's name with the one provided.
		}
	
		
		//Logic interface methods:
		@Override public void feed(List<Signal> inputSignals) //this method takes a list of signals and attaches them to the signals on the wires in the list of inputs: (COMPLETE)
		{
			if (inputSignals.size() != inputs.size()) //if the lengths of the provided list of Signals doesn't equal the length of the inputs in the class:
			{
				throw new ExceptionLogicParameters(true, inputs.size(), inputSignals.size()); //raises ExceptionLogicParameters exception if true with inputsRelated = true, expected = inputs.length(), and found = inputSignals.length().
			}
			
			for (int i = 0; i < inputSignals.size(); i++) //for each signal in the List inputSignals:
			{
				inputs.get(i).setSignal(inputSignals.get(i)); //set the signal on each corresponding wire in inputs to the signal of inputSignals list. 
			}
			
		}
		
		@Override public void feed(String inputSignals) //this method takes a string of inputs and feeds them to the signals on the wires in the list of inputs: (COMPLETE)
		{
			if (inputSignals.length() != inputs.size()) //if the lengths of the provided list of Signals doesn't equal the length of the inputs in the class:
			{
				throw new ExceptionLogicParameters(true, inputs.size(), inputSignals.length()); //raises ExceptionLogicParameters exception if true with inputsRelated = true, expected = inputs.length(), and found = inputSignals.length().
			}
			
			List<Signal> signals = Signal.fromString(inputSignals); //creates a list of signals. called signals, from the fromString method in the Signal class.
			feed(signals);//uses the List<Signal> version of feed to complete the feeding of the signals.
		}
		
		@Override public List<Signal> read() //this method returns a Signal list containing the signal on the output wire: (COMPLETE)
		{
			ArrayList<Signal> outputSig = new ArrayList<Signal>(); //creates a new Signal ArrayList called outputSig.
			outputSig.add((this.output).getSignal()); //adds the output wire's Signal to the list.
			return outputSig; //returns outputSig.
		}
		
		@Override public List<Signal> inspect(List<Signal> inSigs) //this method provides inputs to a gate and returns the output: (COMPLETE)
		{
			feed(inSigs); //uses the feed method to plug in the signals contained within the list to the gate. 
			return this.read(); //returns the current output of the read method.
		}
		
		@Override public String inspect(String inStr) //this method does inspect with a string instead of list: (COMPLETE)
		{
			feed(inStr); //uses the String version of feed to provide the imputs to the gate. 
			String repr = ""; //creates an empty String named repr.
			repr += (this.output).toString(); //adds the string of output wire to repr.
			return repr; //returns repr.
		}
		
		@Override public abstract boolean propagate(); //propagate left abstract for children to override. (COMPLETE)
		
	
		//unique class work methods:
		@Override public String toString() //creates a string representation of the object. (COMPLETE)
		{
			String repr = ""; //creates an empty String called repr.
			repr += this.name; //adds the gate's name to repr.
			repr += "( " + inputs.toString() + " | "; //adds "( " and the string representation of the inputs List to repr, followed by " | ".
			repr += this.output + " )"; // adds the output wire to repr.
			return repr; //returns repr.
		}
		@Override public boolean equals(Object other) //casts Gate to Object other and compares the name, outputs, and inputs to the class's: and returns true if they match, false otherwise. (COMPLETE)
		{
			if((((Gate)other).getName()).equals(this.getName()) && (((Gate)other).getOutput()).equals(this.getOutput()) && (((Gate)other).getInputs()).equals(this.getInputs())) //if the casted Gate object's name, outputs, and inputs matches the class's: #Robin: "Holy Long-if-condition, Batman...."
			{
				return true; //returns true;
			}
			else //if the above is not true:
			{
				return false; //returns false.
			}
		}
}