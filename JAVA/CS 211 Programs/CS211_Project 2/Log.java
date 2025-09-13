/*
	**************************************************************************
	 Log class for Project 2 of CS211-220 (Spring 2017)
	 Coded by Jeffrey Wilhite	
	**************************************************************************
*/
//necessary imports:
import java.util.Scanner;
import java.io.*; // File FileNotFoundException PrintWriter

public class Log
{
	//initializing instance variables
	private String msg; //should be private to prevent any other class from accessing this directly.
	private PrintWriter logwriter ;
	private File log;
	
	public Log() throws FileNotFoundException
	{
		File log = new File("logs.txt");
		PrintWriter logwriter = new PrintWriter("log.txt");
	}
	
	public Log copy() throws FileNotFoundException
	{
		Log log_copy = new Log();
		return log_copy;
	}
	
	public String[] read() throws FileNotFoundException
	{
		String[] msg_list = new String[0];
		Scanner sc = new Scanner(this.log);
		while (sc.hasNextLine())
		{
			msg += sc.nextLine()+"\n";
		} 
		return msg_list;
	}
	
	public void record(String msg) throws FileNotFoundException
	{
		PrintWriter log = new PrintWriter("log.txt");
		log.println(msg);
		log.close();
	}
}