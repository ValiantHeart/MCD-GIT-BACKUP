// A simple implementation of a Set of unique elements, none of which
// are .equals(..) to any other in the set.  Provides add(), remove(),
// and contains() methods.
public class ArraySet<T> {
  // Default internal capacity on creation
  private static int defaultSize = 10;

  private T [] data;            // Internal store of objects
  private int size;             // Virtual size

  // Make a default capacity set
  public ArraySet(){
    this(defaultSize);
  }

  // Create a list with indicated initial capacity
  @SuppressWarnings("unchecked") 
  public ArraySet(int n){
    this.data = (T []) new Object[n];
    this.size = 0;
  }

  // Virtual size of the set: returns the number of unique elements in
  // the set
  public int size(){
    return this.size;
  }
    
  // Helper to ensure that the data array has space for n elements
  @SuppressWarnings("unchecked") 
  private void ensureCapacity(int n){
    if(this.data.length < n){
      // Must increase capacity: double in size
      T b[] =  (T []) new Object[2 * this.data.length + 1];
      for(int i=0; i<this.data.length; i++){
  	b[i] = data[i];
      }
      this.data = b;
    }
  }

  // Determine if the argument x is present in the set
  public boolean contains(T x){
    for(int i=0; i<this.size(); i++){
      if(this.data[i].equals(x)){
        return true;
      }
    }
    return false;
  }

  // Ensure that the given x is part of the set by adding it to the
  // iternal array expanding if needed. Return true if the element is
  // added and the size increases. If the elemnt is already present,
  // do not modify the set and return false.
  public boolean add(T x){
    if(this.contains(x)){
      return false;             // Already present, don't change
    }
    // New element
    this.ensureCapacity(this.size+1);
    data[this.size] = x;
    this.size++;
    return true;
  }

  // Ensure that the given x is NOT part of the set by eliminating it
  // from the internal array. Return true if an element is removed
  // from the set and the size decreases. If the elemnt is not
  // present, return false.
  public boolean remove(T x){
    if(!this.contains(x)){
      return false;             // Not present, don't change
    }
    // Find the element and shift over elements to overwrite it
    int xdx = 0;
    while(xdx < this.size() && !this.data[xdx].equals(x)){
      xdx++;
    }
    // Located the index of x, shift elements over to overwrite it
    for(int i=xdx+1; i<this.size(); i++){
      this.data[i-1] = this.data[i];
    }
    this.size--;
    return true;
  }

  // Pretty print the set
  public String toString(){
    if(this.size()==0){
      return "[]";
    }
    StringBuffer sb = new StringBuffer();
    sb.append("[");
    for(int i=0; i<this.size-1; i++){
      sb.append(this.data[i].toString());
      sb.append(", ");
    }
    sb.append(this.data[this.size-1]);
    sb.append("]");
    return sb.toString();
  }
}

