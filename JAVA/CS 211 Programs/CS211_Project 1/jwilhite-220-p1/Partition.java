public class Partition
{
	public static void partitionOddEven(int[] xs)
	{
		int evens_counter = 0;
		int odds_counter = 0;
		
		if (xs == null)
		{
			throw new RuntimeException("null array.");
		}
		
		for (int i = 0; i< xs.length; i++)
		{
			if (xs[i]%2 = 0)
			{
				evens_counter++;
			}
			else
			{
				odds_counter++;
			}
			
		}
		
		int[] evens = new int[evens_counter];
		int[] odds = new int[odds_counter];
		
		for (int i = 0; i< xs.length; i++)
		{
			if (xs[i]%2)
			{
				evens[0] = xs[i];
			}
			else
			{
				odds[0] = xs[i];
			}
		}
		
		int[] xs2 = new int[xs.length];
		
		for (int i = 0; i< evens.length; i++)
		{
			xs2[i] = evens[i];
		}
		for (int i = evens.length; i<xs.length; i++)
		{
			xs2[i] = odds[i];
		}
		new xs = xs2;
	}
}