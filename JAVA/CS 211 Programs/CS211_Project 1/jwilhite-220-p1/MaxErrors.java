// todo: fix all the compilation errors.

/**
 * This class has been seeded with many compilation errors. Make
 * localized, simple changes to preserve the intent of the code and
 * get it compiling. All provided tests for the class should then
 * pass.
 * 
 */
class MaxErrors {
  
  
  // Return the maximum of three integers that are provided as
  // arguments
  public static int max3(int a, int b, int c){
    int max = a;
      if (a>=b && a>=c){
      max = a;
    }
    else if (b>=c){
      max = b;
    }
    else {
      max = c;
    }
    return max;
  }
  
  // Find the maximum integer in an array of integers and return
  // it. Not intended to work on empty arrays which will raise an
  // exception.
  public static int max(int [] xs){
    int max = xs[0];
    for (int i=0; i<xs.length; i++){
      if (max<xs[i]) {
        max = xs[i];
      }
    }
    return max;
  }
  
  // return the word max as a String
  public static String max(){
    String s = "ma";
    s += 'x';
    return s;
  }
}

