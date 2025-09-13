//Necessary Imports:
import java.util.*;

public class ArraySet<T extends Comparable<T>>
{
// A simple implementation of a Set backed by an array.  As a Set,
// instances track *unique* items so that no duplicates occur.  This
// implementation should keep the underlying array sorted and use
// binary search to quickly identify if items are present or absent to
// maintain uniqueness.  To maintain this, items that go into the set
// must implement the Comparable interface so that they have a
// compareTo(..) method and are compatible with library search and
// sort methods.
// 
// Good choices for private fields are an ArrayList which will manage
// the underlying array size automatically.

	private List<T> data; //creates an internal List type, named data, to keep track of the data...

	public ArraySet() //constructor Type 0: Creates and empty ArraySet (COMPLETE)
	{
		this.data = new ArrayList<T>(); //data is made to be an ArrayList of object T.
	}
	// Create an empty ArraySet

	public int size() // (COMPLETE)
	{
		return (this.data).size(); //returns the ArrayList's size.
	}
	// Return the size of the set which is the number of unique items in
	// it.

	public List<T> asList() //(this method is a getter method) (COMPLETE)
	{
		return this.data; //return's the List data. 
	}
	// Return the contents of the set as a list. The return list does
	// not have to be distinct from the lists pointed to by internal
	// fields of the set (no deep copies need to be made).

	public boolean contains(T query) // (COMPLETE)
	{
		int i = Arrays.binarySearch(data.toArray(), query); //does a binary search for the query and stores the return in int i.
		if(i < 0) //if the return is less than 1:
		{
			return false; //return false.
		}
		else //otherwise:
		{
			return true; //return true.
		}
	}
	// Return true if the query item is present in the set and false
	// otherwise. This method should use binary search to efficiently
	// determine presence or absence.

	public boolean add(T item) // (COMPLETE)
	{
		if(item == null) // if the item is null:
		{
			throw new RuntimeException("Eww, gross. Encountered a null, I can't handle null things, human. Q__Q..."); //throw a RuntimeException with an appropriate message...
		}
		if(data.contains(item)) //if the item is already in the ArraySet:
		{
			return false; //return false.
		}
		
		if(data.isEmpty()) //if the item is the first to be added to the ArraySet(If the ArraySet is empty): 
		{
			data.add(item); //#first
		}
		else
		{
			int index = Math.abs(Arrays.binarySearch(data.toArray() ,item)) - 1; // retrieve the index of where the item should be inserted in data.
			if(index < data.size()) //if the index is less than the size of data:
			{
				data.add(index, item); //add it at the specified index.
			}
			if(index >= data.size()) //if the index is greater than or equal to the size of data:
			{
				data.add(item); //add it to the end.
			}
		}
		return true; //return true.
	}
	// Ensure the specified item is present in the set.  Maintain the
	// uniqueness of items in the set: do not add duplicates which
	// would be equal to one another.  If the given item is added to
	// the set, return true.  If the item is already present so that the
	// set does not change size, return false.  Throw a RuntimeException
	// in the event a item is null: ArraySet does not support null
	// items.

	public T get(T query) //(item getter method) (COMPLETE)
	{
		if(this.contains(query)) //if the item is in the ArraySet:
		{
			int index = Math.abs(Arrays.binarySearch(data.toArray(), query)); //retrieve the index of where the query is in data.
			return data.get(index); //returns the item at that index.
		}
		else //otherwise:
		{
			return null; //return null.
		}
	}
	// Retrieve an item in the set that is equal to the query item.  If
	// no item in the set is equal to the query, return null.

	public String toString() // (COMPLETE)
	{
		return data.toString(); //return the toString of the ArrayList data.
	}
	// Return a string representation of the set and its contents. The
	// string should be identical in format to Lists making use the
	// toString() method of a List field the easiest way to implement
	// this method. Examples:
	//
	// [1, 3, 5, 9, 20, 27]
	// ["A", "B", "F", "R", "V"]

} 