//Necessary imports:
import java.util.*;

public class IndexEntry implements Comparable<IndexEntry>
{
// Entries in the PageIndex: Represents a single term (word) found and
// tracks the pages that have that word associated with them.
// IndexEntries are created by specificying a term in the constructor
// and then adding pages to the entry.  The class implements
// Comparable so it can be stored in a set such as ArraySet.

	protected String term;
	// A search term which is found on some pages. Examples: "computer"
	// "acrobat" or "electronica".  Terms are kepty in lower case only.

	protected ArraySet<String> pages;
	// The set of pages which contain the given term

	public IndexEntry(String term) //Constructor type 0: only term provided (COMPLETE)
	{
		this.term = term.toLowerCase(); //assigns the all lowercase version of the term to the class's var of term.
		this.pages = new ArraySet<String>(); //assigns and empty Arrayset of string's to class's var of pages. 
	}
	// Create an empty IndexEntry associated with the given term

	public String getTerm() // (COMPLETE)
	{
		return this.term; //return term
	}
	// Return a the term associated associated with this entry

	public List<String> getPages() // (COMPLETE)
	{
		return (this.pages).asList(); //return's pages as a list.
	}
	// Return a list of pages associated with this entry

	public boolean containsPage(String pageFileName) // (COMPLETE)
	{
		return pages.contains(pageFileName); // using pages contain's method, checks if the string is within the set.
	}
	// Return true if the the given page is already present in this
	// entry and false otherwise.

	public boolean addPage(String filename) // (COMPLETE)
	{
		return pages.add(filename); // returns the result of calling pages add method on the filename.
	}
	// Add the given page to this entry returning true if it was not
	// present and is therefore a new addition; return false if it is
	// already present

	public int compareTo(IndexEntry that) // (COMPLETE)
	{
		return (this.term).compareTo(that.getTerm()); //returns the result of comparing the terms of this to that.
	}
	// Compare the entry to that entry. The comparison is based entirely
	// on the term field which should use the built-in String comparison
	// methods to generate the difference.

	public boolean equals(Object other) // (COMPLETE)
	{
		try
		{
			if((((IndexEntry)other).getTerm()).equals(this.term)) //if the casted IndexEntry other's term is the same as this IndexEntry's term:
			{
				return true; //return true.
			}
			return false; //return false.
		}
		catch(Exception e) //catch any exception
		{
			return false; //return false.
		}
	}
	// Return whether the other object is equal to this IndexEntry.
	// This is only the case when other is also an IndexEntry and has an
	// equal term field.

	public String toString() // (COMPLETE)
	{
		String rtrn_str = "@ " + (this.term) + "\n"; // initialize the String rtrn_str as "@ term \n"
		for(String page: pages.asList()) //for each page in pages list:
		{
			rtrn_str += page + "\n"; //add the page and a newline marker to rtrn_str.
		}
		return rtrn_str; //return rtrn_str.
	}
	// Return a string representation of this entry. The format of the string is
	//
	// @ term
	// file1
	// file2
	// file3
	// ...
	//
	// Example 1: 1 page associated with "bored"
	// @ bored
	// crawls/small-site/B.html
	// 
	// Example 2: 6 pages associated with "lisp"
	// @ lisp
	// ../crawls/cs.gmu.edu/~sean/lisp/LispTutorial.html
	// ../crawls/cs.gmu.edu/~sean/lisp/cons.html
	// ../crawls/cs.gmu.edu/~sean/lisp/index.html
	// ../crawls/cs.gmu.edu/~sean/papers/index.html
	// ../crawls/cs.gmu.edu/~sean/research.1.html
	// ../crawls/cs.gmu.edu/~sean/research/lil-gp-patch/index.html

}