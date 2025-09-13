public class SecondSmallest
{
 public static int secondSmallest(int[] xs)
 {
  int x = -5000000;
  int y = 0; 
  
  if (xs == null) 
  {
   throw new RuntimeException("array is null.");
  }
  
  if (xs.length <= 1) 
  {
   throw new RuntimeException("array is too short.");
  }
  
  if (xs.length < 1)
  {
   for (int i = 0; i < xs.length; i++)
   {
    if (xs[i] > x)
    {
      y = xs[i];
     break;
    }
   }
   for (int i = 0; i < xs.length; i++)
   {
    if (xs[i] > x && y < xs[i])
    {
     y = xs[i];
    }
   }
  }
  
  return y;
 }
}
