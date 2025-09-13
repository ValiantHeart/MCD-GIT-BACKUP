public class HighlyComposite
{
	public static int numDivisors(int n)
	{
	int counter = 0;
		for (int i = 1; i<= n; i++)
		{
			if (n%i == 0)
			{
				counter++;
			}
		}
	return counter;
	}
	public static boolean highlyComposite(int n)
	{
	int[] divisors = new int[n];
	if (n == 0)
	{
		throw new RuntimeException("no zeroes allowed");
	}    
	if (n < 0)
	{
		throw new RuntimeException("no negatives allowed");
	}
	for (int i = n-1; i > 0; i--)
	{
		divisors[i] = numDivisors(i);
	}
	int a = 0;
	for (int i = 0; i < divisors.length; i++)
	{

		if (divisors[i] > a)
		{
			a = divisors[i];
		}

	} 
	
	if (a >= numDivisors(n))
	{
		return false;
	}
	return true;

	}
}