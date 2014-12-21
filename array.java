import java.io.BufferedReader;
import java.io.FileReader;

public class array 
{
	
	public static void main(String[] args) 
	{
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader("array.in"));

			// Read the line with the number of cases.
	        String line = br.readLine();
	        // Parse the text to an integer.
	        int nNumberOfTestCases = Integer.parseInt(line);
	        
	        // Loop through the cases.
	        for( int i=0; i<nNumberOfTestCases; i++ )
	        {
	        	// Read the line with the data.
	        	line = br.readLine();
	        	
	        	// Split into numbers.
	        	String Tokens[] = line.split(" ");

	        	// First number is the number of numbers in the data.
	        	int nSizeOfData = Integer.parseInt(Tokens[0]);
	        	
	        	// Create an array to hold the integer data.
	        	int nData[] = new int[nSizeOfData];

	        	// Loop through and parse the numbers.
	        	//   Then store in the array.
	        	for( int j=0; j<nData.length; j++ )
	        	{
	        		nData[j] = Integer.parseInt(Tokens[j+1]);
	        	}
	        	
	        	// Read the line with the target.
	        	line = br.readLine();
	        	int nTargetNumber = Integer.parseInt(line);

				
	        	// Worst case scenario low = (n-1) and High = n or high = (n - (n+2) ) 
	        	//and low = (n - (n+1) ). There fore we will in the worst case traverse the array less than n.
				boolean foundTarget = false;
				int low = 0;
				int high = (nData.length) - 1;
				int x = 0;
				
				
				while(!foundTarget)
				{
					x = nData[low] + nData[high];
					if(low == high)
					{
						foundTarget = true;
						System.out.printf("Test case #%d: The target of %d is NOT achievable.\n\n", (i+1), nTargetNumber);
					}
					
					else if(x == nTargetNumber)
					{
						foundTarget = true;
						System.out.printf("Test case #%d: The target of %d is achievable.\n\n", (i+1), nTargetNumber);
					}
					
					else if(x < nTargetNumber)
					{
						low++;
					}
					
					else
					{
						high--;
					}
				}
	        }
		}
		catch(Exception ex)
		{
			System.out.println( ex.getMessage());
		}
		finally
		{
			try
			{
				br.close();
			}
			catch(Exception ex2)
			{
			}
		}		
	}
}
