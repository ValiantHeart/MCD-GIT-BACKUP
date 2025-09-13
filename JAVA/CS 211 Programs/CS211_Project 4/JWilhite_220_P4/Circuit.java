/*
   ********************************************************
    Circuit class for Project 4 of CS211-220 (Spring 2017)
    Coded by Jeffrey Wilhite 
   ********************************************************
*/

//Necessary Imports:
import java.util.*;
import java.io.*;

public class Circuit implements Logic
{
	//Fields:
	private List<Logic> components; //The list of logical blocks that are wired together in this circuit.
	private List<Contact> inputs, outputs; //The connections to the outside world (in and out).
	private List<Wire> innerWires; //A convenient place to store all known wires while constructing a circuit.
	private List<String> importables; //the names of circuits that were announced on the IMPORT line, if any.
	private String name; //the circuit's name (part of the file name).
	
	//Constructors:
	public Circuit(String circuitName, List<Logic> components, List<Contact> inputs, List<Contact> outputs, List<Wire> innerWires, List<String> importables) //Constructor Type 0: all variables provided. (COMPLETE)
	{
		this.name = circuitName;
		this.components = components;
		this.inputs = inputs;
		this.outputs = outputs;
		this.innerWires = innerWires;
		this.importables = importables;
	}
	
	public Circuit(String circuitName) throws IOException //Constructor Type 1: only circuitName variable provided, has to build the rest from the file with the matching name using helper methods.
	{
		//initialize all fields:
		this.name = circuitName;
		ArrayList<Logic> components = new ArrayList<Logic>();
		ArrayList<Contact> inputs = new ArrayList<Contact>();
		ArrayList<Contact> outputs = new ArrayList<Contact>();
		ArrayList<Wire> innerWires = new ArrayList<Wire>();
		ArrayList<String> importables = new ArrayList<String>();
		
		this.components = new ArrayList<Logic>();
		this.inputs = new ArrayList<Contact>();
		this.outputs = new ArrayList<Contact>();
		this.innerWires = new ArrayList<Wire>();
		this.importables = new ArrayList<String>();
		
		//retrieves necessary information for the created lists.
		Scanner CScan = this.getCircuitScanner(circuitName);// retrieves a circuit scanner using the helper method: getCircuitScanner.
		ArrayList<String> contents = new ArrayList<>();
		
		while(CScan.hasNextLine())
		{
			String line = CScan.nextLine();
			
			if (!line.equals(""))
			{
				contents.add(line);
			}
		}
		
		if((contents.get(0)).contains("IMPORT")) //if the first line in contents contains the tag IMPORT:
		{
			this.parseImportLine(contents.get(0));
			this.parseContactsLine(contents.get(1));
			for (int i = 2; i < contents.size(); i++)
			{
				this.parseComponentLine(contents.get(i));
			}
		}
		else
		{
			this.parseContactsLine(contents.get(0));
			for (int i = 1; i < contents.size(); i++)
			{
				this.parseComponentLine(contents.get(i));
			}
		}
	}
	
	//Methods:
		//getters:
		public List<Logic> getComponents() //gets the list of components in the circuit: (COMPLETE)
		{
			return this.components; //returns the object's components list.
		}
		
		public List<Contact> getInputs() //gets the list of inputs in the circuit: (COMPLETE)
		{
			return this.inputs; //returns the object's inputs list.
		}
		
		public List<Contact> getOutputs() //gets the list of outputs in the circuit: (COMPLETE)
		{
			return this.outputs; //returns the object's outputs list.
		}
		
		public List<Wire> getInnerWires() //gets the list of InnerWires in the circuit: (COMPLETE)
		{
			return this.innerWires; //returns the object's InnerWire's list.
		}
		
		public List<String> getImportables() //gets the list of importables in the circuit: (COMPLETE)
		{
			return this.importables; //returns the object's importables list.
		}
		
		public String getName() //gets the String name of the circuit: (COMPLETE)
		{
			return this.name; ////returns the object's name.
		}
		
		
		//setters:
		public void setComponents(List<Logic> components) //set's the object's components list to the one provided: (COMPLETE)
		{
			this.components = components; //assign's the object's list of components to the one provided.
		}
		
		public void setInputs(List<Contact> inputs) //set's the object's inputs list to the one provided: (COMPLETE)
		{
			this.inputs = inputs; //assign's the object's list of inputs to the one provided.
		}
		
		public void setOutputs(List<Contact> outputs) //set's the object's list of outputs to the one provided: (COMPLETE)
		{
			this.outputs = outputs; //assign's the object's list of outputs to the one provided.
		}
		
		public void setInnerWires(List<Wire> innerWires) //set's the object's list of InnerWires to the one provided: (COMPLETE)
		{
			this.innerWires = innerWires; //assign's the object's list of importables to the one provided.
		}
		
		public void setImportables(List<String> importables) //set's the object's list of importables to the one provided: (COMPLETE)
		{
			this.importables = importables; //assign's the object's list of importables to the one provided.
		}
		
		public void setName(String name) //set's the object's name to the one provided: (COMPLETE)
		{
			this.name = name; //assign's the name of the object to the one provided.
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
				Contact c = inputs.get(i); //retrieve the contact stored in the slot, and assigns it to variable c.
				Wire w = c.getIn(); //retrieve the contact's in wire and stores it in variable w.
				w.setSignal(inputSignals.get(i)); //set the signal on the in contact to the signal in the associated slot.
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
		
		@Override public List<Signal> read() //this method returns a Signal list containing the signal on the output contacts: (COMPLETELY IMPLEMENTED, BUT FAILING TEST 8 ON ACCOUNT OF PROPAGATE)
		{
			ArrayList<Signal> outputSigs = new ArrayList<Signal>(); //creates a new Signal ArrayList called outputSigs.
			
			for (int i = 0; i < outputs.size(); i++) //for each signal in the outputs list:
			{
				Contact c = outputs.get(i); //retrieve the contact stored in the slot, and assigns it to variable c.
				Wire w = c.getOut(); //retrieve the contact's out wire and stores it in variable w.
				outputSigs.add(w.getSignal()); //retrieves the signal on the wire and adds it to the outputSigs List.
			}
			
			return outputSigs; //returns outputSig.
		}
		
		@Override public List<Signal> inspect(List<Signal> inSigs) //this method provides inputs to a circuit and returns the output: (INCOMPLETE)
		{
			this.feed(inSigs); //uses the feed method to plug in the signals contained within the list to the gate. 
			this.propagate();
			return this.read(); //returns the current output of the read method.
		}
		
		@Override public String inspect(String inStr) //this method does inspect with a string instead of list: (INCOMPLETE)
		{
			feed(inStr); //uses the String version of feed to provide the imputs to the circuit. 
			String out = ""; //creates an empty String named out.
			out += (this.outputs).toString(); //adds the string of output wire to out.
			return out; //returns out.
		}
		
		@Override public boolean propagate() //calls propogate on all Logic implementers throughout the circuit, returns True if none of them return false, returns false otherwise: (INCOMPLETE)
		{
			//propagate input contacts first, components, then lastly outer contacts:
			
			boolean rtn = false;
			for(Contact ins : inputs) //for each input in the inputs List:
			{
				if(ins.propagate()) //calls propagate on it and if it returns true:
				{
					rtn = true; //rtn is assigned true.
				}
			}
			for(Logic component : components) //for each component in the components List:
			{
				if(!component.propagate()) //calls propagate on it and if it returns false:
				{
					rtn = true; //rtn is assigned true.
				}
			}
			for(Contact outs : outputs) //for each output in the outputs List:
			{
				if(!outs.propagate()) //calls propagate on it and if it returns false:
				{
					rtn = true; //rtn is assigned true.
				}
			}
			
			return true; //assuming all the signals propagated properly (meaning if all the Logic overrides of Propagate are implemented correctly), return true.
		}
		
		
		//unique class methods:
			//helper methods:
			public Scanner getCircuitScanner(String circuitName) throws IOException //this helper method creates a scanner attached to the circuit text file with the given name and returns it: (COMPLETE) 
			{
				File circuit_file = new File("samples/"+ circuitName +".txt"); //creates a File object of the file in the samples folder containing the name provided.
				Scanner CScan = new Scanner(circuit_file); //creates and attaches the Scanner CScan to the File object circuit_file.
				return CScan; //returns the Scanner CScan.
			}
			
			public void parseImportLine(String line) //this helper method adds the name of subcircuits to be imported into the circuit to the importables list: (COMPLETE)
			{
				ArrayList<String> imports = new ArrayList<String>(); //creates a new ArrayList of type String called imports.
				String[] line_parts = line.split(" "); //creates a new String Array called line_parts from the splitting of line at spaces.
				for (String parts : line_parts) //for each part of line_parts:
				{
					imports.add(parts); //add each part to the imports list.
				}
				imports.remove(0); //since it added the entire contents of the line to the ArrayList, the first item added will be the String "IMPORT". This is not a valid importable circuit and must be removed from the list. :)
				this.importables = imports; //overwrites the objects existing importables list with the created imports list.
			}
			
			public void parseContactsLine(String line) //this helper method creates the circuit's outermost contacts, with wire's attached, updates the innerWire's list accordingly: (COMPLETE)
			{
				String[] line_parts = line.split(" "); //creates a new String Array called line_parts from the splitting of line at spaces.
				boolean inbound = true; //boolean flag indicating whether the contact being created is inbound or outbound.
				
				for (String parts : line_parts) //for each part of line_parts:
				{
					if (parts.equals("->")) //if the part retrieved from line_parts is the directionality indcator:
					{
						inbound = false; //set inbound to false.
					}
					else //otherwise:
					{
						if (inbound) //if the inbound flag is true:
						{
							Wire w = new Wire(parts); //create a new wire from the parts String provided.
							(this.innerWires).add(w); //add that wire to the list of inner wires.
							Contact c = new Contact(w, w, true); //create a new Contact c with the same wires in and out and with inbound being true.
							(this.inputs).add(c); //adds that contact to the in_contacts list.
						}
						else
						{
							Wire w = new Wire(parts); //create a new wire from the parts String provided.
							(this.innerWires).add(w); //add that wire to the list of inner wires.
							Contact c = new Contact(w, w, false); //create a new Contact c with the same wires in and out and with inbound being false.
							(this.outputs).add(c); //adds that contact to the out_contacts list.
						}
					}
				}
			}
			
			public Wire findWire(String name) //this helper method finds the wire in the list of innerWires with the given name (will pull the first wire in the list). (COMPLETE)
			{
				for (int i = 0; i < innerWires.size(); i++) //for each Wire in innerWires:
				{
					if (((innerWires.get(i)).getName()).equals(name)) //if the name of the wire in list of innerWires equals the name provided:
					{
						return innerWires.get(i); //then return wire_ref
					}
				}
				
				Wire wire_ref = null; //initializes the Wire wire_ref as null.
				return wire_ref; //return wire_ref.
			}
			
			public void hookUp(List<Wire> inWires, List<Wire> outWires) //this helper method tells the contacts of a subcircuit to link up to the in and out wires provided: (COMPLETE)
			{
				if((this.inputs).size() != inWires.size()) //if the size of the provided inputs doesn't match the size of inWires:
				{
					throw new ExceptionLogicParameters(true, (this.inputs).size(), inWires.size()); //throw an appropriate exception.
				}				
				if((this.outputs).size() != outWires.size()) //if the size of the provided outputs doesn't match the size of outWires:
				{
					throw new ExceptionLogicParameters(false, (this.outputs).size(), outWires.size()); //throw an appropriate exception.
				}
				
				int count = 0;
				for (Contact ins : inputs) //for each of the intput contacts:
				{
					ins.setIn(inWires.get(count)); //set the contact's appropriate wire to the one provided. 
					count++; //advances the counter by one.
				}				
				count = 0; //resets counter to 0.
				for (Contact outs : outputs) //for each of the output contacts:
				{
					outs.setOut(outWires.get(count)); //set the contact's appropriate wire to the one provided.
					count++; //advances the counter by one.
				}
			}
			
			public void parseComponentLine(String line) throws IOException //this helper method takes the line which contains component details: creates them, and adds them to the component list: <Modify to componsate for additional custom components> (INCOMPLETE)
			{
				ArrayList<Wire> in_wires = new ArrayList<Wire>(); //creates a new list named in_wires for parsed input wires.
				ArrayList<Wire> out_wire = new ArrayList<Wire>(); //creates a new list named out_wire for the parsed output wire.
				String[] line_parts = line.split(" "); //creates a new String Array called line_parts from the splitting of line at spaces.
				boolean is_inputs = true; //bool flag for telling whether the wires are inputs or outputs.
				
				//must retrieve inputs first before we can make a new anything... (Why isn't newOUT getting added.... CAUSE: UNKNOWN)
				for(int i = 1; i < line_parts.length; i++)
				{
					if((line_parts[i]).equals("->"))
					{
						is_inputs = false;
						continue;
					}
					
					if(is_inputs)
					{
						if(findWire(line_parts[i]) != null)
						{
							in_wires.add(findWire(line_parts[i]));
						}
						else
						{
							Wire w = new Wire(line_parts[i]);
							in_wires.add(w);
							
						}
					}
					else
					{
						Wire w = new Wire(line_parts[i]);
						out_wire.add(w);
					}
				}
				
				if(importables.contains(line_parts[0]))// if the object being described is in the list of imported circuits:
				{
					Circuit subcircuit = new Circuit(line_parts[0]); //create a new circuit from that object
					subcircuit.hookUp(in_wires, out_wire); //hook it up to the inputs provided.
				}
				else //if not an imported circuit:
				{
					switch(line_parts[0]) //attach a switch to the first item in the array:
					{
						case "NOT": //should line_parts[0] be NOT:
							GateNot NOT = new GateNot(in_wires.get(0), out_wire.get(0)); //create and appropriate gate using the gathered inputs and outputs.
							this.components.add(NOT); //add the gate to the list of components.
							break; //break out of switch.
						case "AND": //should line_parts[0] be AND:
							GateAnd AND = new GateAnd(in_wires, out_wire.get(0)); //create and appropriate gate using the gathered inputs and outputs.
							this.components.add(AND); //add the gate to the list of components.
							break; //break out of switch.
						case "NAND": //should line_parts[0] be NAND:
							GateNand NAND = new GateNand(in_wires, out_wire.get(0)); //create and appropriate gate using the gathered inputs and outputs.
							this.components.add(NAND); //add the gate to the list of components.
							break; //break out of switch.
						case "OR": //should line_parts[0] be OR:
							GateOr OR = new GateOr(in_wires, out_wire.get(0)); //create and appropriate gate using the gathered inputs and outputs.
							this.components.add(OR); //add the gate to the list of components.
							break; //break out of switch.
						case "NOR": //should line_parts[0] be NOR:
							GateNor NOR = new GateNor(in_wires, out_wire.get(0)); //create and appropriate gate using the gathered inputs and outputs.
							this.components.add(NOR); //add the gate to the list of components.
							break; //break out of switch.
						case "XOR": //should line_parts[0] be XOR:
							GateXor XOR = new GateXor(in_wires, out_wire.get(0)); //create and appropriate gate using the gathered inputs and outputs.
							this.components.add(XOR); //add the gate to the list of components.
							break; //break out of switch.
						default: //should line_parts[0] be none of the above:
							break; //break out and do nothing furhter.
					}
					
					for (int i = 0; i < in_wires.size(); i++) //for each slot in the in_wires list:
					{
						if(!(this.innerWires).contains(in_wires.get(i))) //if the wire is not already in the list of innerWires:
						{
							innerWires.add(in_wires.get(i)); //add it.
						}
					}
					if (!(this.innerWires).contains(out_wire.get(0))) //if the wire is not already in the list of innerWires:
					{
						(this.innerWires).add(out_wire.get(0)); //add it.
					}
				}
			
			}
			
			public static String indent(String s) //this helper method takes a string with multiple lines and indents each line with two spaces and returns it: (COMPLETE)
			{
				String indented_S = ""; //creats an empty list called indented_S.
				String[] newline_split = s.split("\n"); //splits the incoming string at the newline markers.
				for (String line : newline_split) //for each line in newline_split:
				{
					indented_S += "  " + line + "\n"; //adds exactly two spaces plus the line plus a replacement newline marker to indented_S.
				}
				return indented_S; //return indented_S.
			}
		
		@Override public String toString() //this method returns a string representation of the Circuit object, indents the contents of it's components: (INCOMPLETE)
		{
			String repr = ""; //creates an empty string named repr.
			repr += this.name + ":" + inputs.toString() + " -> " + outputs.toString() + "\n"; //adds a line containing the circuits name, a colon, it's inputs, an arrow, and it's output. Format : (NAME:INPUTS -> OUTPUTS)
			for (int i = 0; i < components.size(); i++) //for each component in components:
			{
				repr += indent((components.get(i)).toString()); //get each component's toString and indent it, then add it 
			}
			return repr; //returns repr.
		}
}