/*Mauricio Diaz
 *Carlos Garzon
 *
 *CeasarCrack_Main
 */

import java.util.ArrayList;
import java.util.List;

public class CeasarCrack_Main 
{
	// Here we are converting a char to int
	public int let2nat(char var)
	{
		// Here we check that the input is within our program parameters
		if (var < 'a' || var > 'z')
		{
			System.out.println("Your input is invalid!");
			return -1;
		}
		return var - 'a';
	}
	
	// Here we are converting an int to char
	public char nat2let(int num)
	{
		// Here we check that the input is within our program parameters
		if (num < 0 || num > 25)
		{
			System.out.println("Invalid input!");
			return '0';
		}
		return (char)(num + 'a');
	}
	
	// Here we shift our char by the shift amount
	public char shift(int shift, char var)
	{
		// We leave capital letters unchanged
		if (var < 'a' || var > 'z')
		{
			return var;
		}
		
		// If shift amount is negative, convert to positive
		while (shift < 0)
		{
			shift += 26;
		}
		
		// We use mod to make sure we are within the alphabet limits
		shift %= 26;
		
		// Apply our shift factor
		if (var + shift > 'z')
		{
			return (char)(var + shift - 26);
		}
		return (char)(var + shift);
	}
	
	// Here we encode using our shift factor
	public String encode(int shift, final String str)
	{
		char[] updatedString = new char[str.length()];
		for (int i = 0; i < updatedString.length; i++)
		{
			updatedString[i] = shift(shift, str.charAt(i));
		}
		return String.valueOf(updatedString);
	}
	
	// Here we use shift to decode by applying the shift amount
	public String decode(int shift, final String str)
	{
		char[] updatedString = new char[str.length()];
		for (int i = 0; i < updatedString.length; i++)
		{
			updatedString[i] = shift(-shift, str.charAt(i));
		}
		return String.valueOf(updatedString);
	}
	
	// Here we count the amount of lowercase letters in string
	public int lowers(final String str)
	{
		int count = 0;
		for (int i = 0; i < str.length(); i++)
		{
			if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z')
			{
				count++;
			}
		}
		return count;
	}
	
	// Count occurrences of var in str
	public int count(char var, final String str)
	{
		int count = 0;
		for (int i = 0; i < str.length(); i++)
		{
			if (str.charAt(i) == var)
			{
				count++;
			}
		}
		return count;
	}
	
	// Here we calculate the percentage of number1 with respect to number2
	public float percent(int number1, int number2)
	{
		return (float) number1 / number2;
	}
	
	// Lower case percentage frequencies in str is returned as a list 
	public List<Float> frequency(final String str)
	{
		int letterCount = lowers(str);
		List<Float> list = new ArrayList<Float>();
		
		for (int i = 0; i < 26; i++)
		{
			list.add((float) count(nat2let(i), str) / letterCount * 100);
		}
		return list;
	}
	
	// Shift the list around
	public <T> List<T> rotate(int rotation, List<T> list)
	{
		for (int i = 0; i < rotation; i++)
			list.add(list.remove(0));
		return list;
	}
	
	public float chisqr(List<Float> os)
	{
		float result = 0;

		double[] values = 
		{
			8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0,
			0.2, 0.8, 4.0, 2.4, 6.7, 7.5, 1.9, 0.1, 6.0,
			6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1
		};

		for (int i = 0; i < values.length; i++)
			result += Math.pow((os.get(i) - values[i]), 2) / values[i];

		return result;
	}
	
	// when we see the key for the first time return this
	public int position(float key, List<Float> list)
	{
		for (int i = 0; i < list.size(); i++)
			if(list.get(i) == key)
				return i;
		return -1;
	}
	
	// This actually attempts to decode the string
	public String crack(final String myStr)
	{	
		List<Float> chisqrs = new ArrayList<Float>();
		List<Float> frequency = frequency( myStr );
		float Num;
		Num = chisqr( frequency );
		chisqrs.add(Num);
		float smallest = -1;
		smallest = Num;

		for (int i = 0; i < 26; i++)
		{
			Num = chisqr(rotate(1, frequency ));
			chisqrs.add(Num);
			if (Num < smallest)
				smallest = Num;

		}
		return decode(position(smallest, chisqrs), myStr);
	}
	
	public static void main(String[] args) 
	{
		CeasarCrack_Main program = new CeasarCrack_Main();

		System.out.printf("%s", program.crack(program.encode(3, "myxqbkdevkdsyxc yx mywzvodsxq dro ohkw!")));
	}
}


