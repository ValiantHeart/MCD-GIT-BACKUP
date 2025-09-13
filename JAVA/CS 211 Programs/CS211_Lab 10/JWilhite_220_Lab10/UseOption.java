public class UseOption{
  // print argument string
  public static void print(Object s){
    System.out.println(s);
  }
  // main method demonstrating generic Option
  public static void main(String args[]){
    Option<String> so1 =
      new Option<String>("Bob"); // Some
    print(so1.hasSome());        // true
    print(so1.toString());       // Some(Bob)
    String s = so1.getSome();    // "Bob"
    print(s);                    // Bob

    Option<String> so2 =
      new Option<String>();     // None
    print(so2.hasSome());       // false
    print(so2.toString());      // None

    Option<Integer> io1 =
      new Option<Integer>(42);  // Some
    print(io1.hasSome());       // true
    print(io1.toString());      // Some(42)
    Integer i = io1.getSome();  // 42
    print(i);                   // Bob
    
    Option<Integer> io2 =
      new Option<Integer>();    // None
    print(io2.hasSome());       // false
    print(io2.toString());      // None
    Integer i2 = io2.getSome(); // RuntimeException(
                                // "Option has None")
  }
}