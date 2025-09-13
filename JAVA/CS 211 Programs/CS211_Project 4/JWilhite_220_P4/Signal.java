/*
   *******************************************************
    Signal class for Project 4 of CS211-220 (Spring 2017)
    Coded by Jeffrey Wilhite 
   *******************************************************
*/

//Necessary Imports:
import java.util.*;

public enum Signal
{
	//Enums:
	HI, 
	LO, 
	X;
	
	//Methods:
		//getters:
		public Signal getSignal() //gets the signal on the wire. (COMPLETE)
		{
			return this; //returns itself.
		}
 		
		//setters:
		//NONE
	
		//unique class methods:
		public Signal invert() //this method inverts the value of the signal. [X|x => X|x, HI => LO, LO => HI]. (COMPLETE)
		{
			switch(this) //switch case attached to the object itself:
			{
				case HI: //if object is HI:
					return LO; //return LO.
				case LO: //if object is LO:
					return HI; //return HI.
				case X: //if object is X (unknown):
					return X;//return X.
			}
			return X; //default return of X(unknown).
		}
		
		public static Signal fromString (char c) //this method returns a signal from a porvided character c. (COMPLETE)
		{
			String accepted_inputs =  "01xX"; //reference string of acceptable inputs.
			String a = Character.toString(c); //converts the char provided to a sing character String.
			
			switch (a) //attaches a switch to the string of the char:
			{
				case "1": //if the string is "1":
					return HI; //return HI
				case "0": //if the string is "0":
					return LO; //return LO.
				case "X": //if the string is "X":
					return X; //return unknown.
				case "x": //if the string is "x":
					return X; //return unknown.
			}
			
			String msg = "UNRECOGNIZED SIGNAL DETECTED: [ " + c + " ]"; //if it is not one of the prodefined cases, it is an unidentified signal, thus raises an excetion with the created message.
			throw new ExceptionLogicMalformedSignal(c, msg); //throws new exception with the char that caused the error and the message defined on the line above.

		}
		
		public static List<Signal> fromString(String inps) //creates a list of signals from a provided string. (COMPLETE)
		{
			String accepted_inputs =  "01xX /t"; //String of acceptable inputs created as accepted_inputs, used for checking purposes.
			ArrayList<Signal> bucket = new ArrayList<Signal>(); //creates an arraylist for values named bucket.

			for (int i = 0; i<inps.length(); i++) //for each character in the provided string: 
			{
				char c = inps.charAt(i); //render a character object of the character at the index of the loop.
				if (accepted_inputs.indexOf(c) == -1 && Character.isWhitespace(c) != true) //if it either not in the list of acceptable strings (indexOf = -1 if character is not present) or if it's not a Whitespace: 
				{
					String msg = "UNRECOGNIZED SIGNAL DETECTED: [ " + c + " ]"; //creates an error message for the defined exception.
					throw new ExceptionLogicMalformedSignal(c, msg); //throws a new ExceptionLogicMalformedSignal error with the message defined on the line above and with the char which caused said error.
				}
				if (Character.isWhitespace(c) != true) //if the character is not a Whitespace and passed the in the list of accepted inputs check:
				{
					bucket.add(Signal.fromString(c)); //add it to the bucket of signals.
				}
				
			}
			return bucket; //return the arraylist of values named bucket.
		}
		
		@Override public String toString() //this method returns a string representation of the signal: (COMPLETE)
		{
			switch(this) //attaches a switch to the object itself:
			{
				case HI: //if its HI:
					return "1"; //return 1.
				case LO: //if its LO:
					return "0"; //return 0.
				default: //if it is neither HI nor LO:
					return "X"; //return X.
			}
		}
		
		public static String toString(List<Signal> sig) //creates a string representation of the values of signals in a provided list. <--overloads toString method above> (COMPLETE)
		{
			String signals = ""; //creates an empty return string called signals.
			for (int i = 0; i < sig.size(); i++) //for each value in provided list:
			{
				signals += sig.get(i); //add it to the string signals.
			}
			return signals; //return signals.
		}
}