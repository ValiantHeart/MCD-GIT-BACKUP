/*
   ********************************************************
    GateSim class for Project 4 of CS211-220 (Spring 2017)
    Coded by Jeffrey Wilhite 
   ********************************************************
*/

public class GateSim
{
	//Methods:
	public static void main(String[] args) //main method:
	{
		try
		{
			String circuit_name = args[0]; //Assigns the first argument to the String circuit_name.
			String inputs_str = args[1]; //Assigns the second argument to the String inputs_str.
			Circuit breadboard = new Circuit(circuit_name); //creates a Circuit object called breadboard from the provided circuit_name.
			breadboard.feed(inputs_str); //feeds breadboard the inputs provided.
			breadboard.propagate(); //propagates the signals through breadboard.
			System.out.println("\n" + breadboard.read()); //prints a new line containing the outputs of the circuit.
			//System.out.println("Computer: Program Complete. You may enter when ready.");
			
		}
		catch (Exception e) //catches any exceptions:
		{
			System.out.println("couldn't perform simulation"); //printing the String: "couldn't perform simulation"
		}
	}
}