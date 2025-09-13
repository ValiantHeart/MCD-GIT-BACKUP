/*
   ******************************************************
    Logic class for Project 4 of CS211-220 (Spring 2017)
    Coded by Jeffrey Wilhite 
   ******************************************************
*/

//Necessary Imports:
import java.util.*;

public interface Logic
{
	//Fields:
	//NONE
	
	//constructors:
	//NONE
	
	//methods:
	public abstract void feed(List<Signal> inSignals);
	public abstract void feed(String inSignals);
	public abstract boolean propagate();
	public abstract List<Signal> read();
	public abstract List<Signal> inspect(List<Signal> inputs);
	public abstract String inspect(String inputs);
}