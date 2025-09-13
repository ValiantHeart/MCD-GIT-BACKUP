/*
 **************************************************************************
  CensoredWriter class for Lab 5 of CS211-220 (Spring 2017)
  Coded by Jeffrey Wilhite 
 **************************************************************************
*/


//Necessary Imports:
import java.io.*;

public class CensoredWriter extends PrintWriter
{
	//instance variables
	public final String F451 = "%!^*#@"; //censor mark stored perminantly as string F451.
	public String c; //censor target stored locally.
	
	public CensoredWriter(OutputStream o, String c) //constructor type 1. (OutputStream and censor target string)
	{
		super(o); //calling parent for input
		this.c = c; //censor string stored locally
	}	
	
	public CensoredWriter(File f, String c) throws Exception //constructor type 2. (File and censor target string)
	{
		super(f); //calling parent for input
		this.c = c; //censor string stored locally
	}
	
	public CensoredWriter(String s, String c) throws Exception //constructor type 3. (Input string and censor target string)
	{
		super(s); //calling parent for input
		this.c = c; //censor string stored locally
	}
	
	public String transform(String censoree) //replaces all instances of c in censoree with F451.
	{
		String censored = censoree.replaceAll(this.c, this.F451);
		return censored;
	}
	
	@Override public void print(String s) //censored print method.
	{
		String p = transform(s); //"transforms" input and stores it as p.
		super.print(p); //calls parent print with p as arg.
	}
	
	@Override public void println(String s) //censored println method.
	{
		String p = transform(s); //"transforms" input and stores it as p.
		super.println(p); //calls parent println with p as arg.
	}
}