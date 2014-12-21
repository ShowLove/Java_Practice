package entropy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;





/****************************************
 * determines if a graph is two colorable
 ****************************************/
public class TwoColouring
{
	static List<Integer> m_integers = new ArrayList<Integer>();
	static List<Integer> verticies = new ArrayList<Integer>();

	
    enum Color {
        UNKNOWN, BLACK, WHITE
    };

    public static Color[] color;
    public static int[][] adjacencyMatrix;
    public static boolean possible;

    public static void main(String[] args)
    {
    	//Stores m_integes and verticies
    	try {
			readTxtFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	for(int choice = 0; choice < verticies.get(0); choice++)
    	{
        	setUpMatrix(choice);
        	
        	findAnswer(choice);
    	}

    	//debug();
    }
    

    
    public static void findAnswer(int choice)
    {
/*  
        int numNodes = 3;
        adjacencyMatrix = new int[numNodes][numNodes];

        adjacencyMatrix[0][1] = 1; adjacencyMatrix[0][2] = 1;
        adjacencyMatrix[1][0] = 1; adjacencyMatrix[1][2] = 1;
        adjacencyMatrix[2][0] = 1; adjacencyMatrix[2][1] = 1;
*/
    	int numNodes = verticies.get(choice + 1 );
    	//System.out.println("\nNumNodes:"+numNodes);
        

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            // System.out.println(Arrays.toString(adjacencyMatrix[i]));
        }

        possible = true;
        color = new Color[numNodes];

        for (int V = 0; V < color.length; V++) {
            color[V] = Color.UNKNOWN;
        }

        for (int V = 0; V < color.length; V++) {
            if (color[V].equals(Color.UNKNOWN)) {
                colorify(V, Color.BLACK, Color.WHITE);
            }
        }

        //System.out.println(Arrays.toString(color));
        if (possible)
            System.out.printf("\nGraph #%d can be colored with two colors.\n", (choice+1) );
        else
            System.out.printf("\nGraph #%d is NOT two-colorable.\n", (choice+1) );
    }

    /**
     * do a DFS on the graph, coloring alternating black and white nodes.
     * If a conflict is found, then the graph is not 2-colorable, and
     * the scheduling is impossible.
     * 
     * @param V
     * @param C1
     * @param C2
     */
    public static void colorify(int V, Color C1, Color C2)
    {
        color[V] = C1;
        for (int V2 = 0; V2 < adjacencyMatrix[0].length; V2++) {
            if (adjacencyMatrix[V][V2] > 0) {
                if (color[V2].equals(C1)) {
                    possible = false;
                    return;
                }
                if (color[V2].equals(Color.UNKNOWN)) {
                    colorify(V2, C2, C1);
                }
            }
        }
    }
    
   ///////////////////////////I/O Stuff//////////////////////////////////////////
    
   /*******************************************************
    * Read info into 	veticies: tells you how many nodes are in each test case
    * 					m_integers: actual values with new lines represented with -1s
    * @throws IOException
    ***************************************************/
   private static void readTxtFile() throws IOException
   {
	   BufferedReader br = new BufferedReader(new FileReader("input.txt"));
	   String line;
	   
	   while ((line = br.readLine()) != null) 
	   {
		   //Parse through all the characters in this particular line
		   for(int i = 0; i < line.length(); i++)
		   {
			   //we found an empty line
			   if( line.length() == 0 )
			   {
				   ;
			   }
			   //We are reading a number that represents numVertecies
			   else if( line.length() == 1 )
			   {
				   verticies.add( Character.getNumericValue(line.charAt(i) ) );
			   }
			   //We are reading the nodes that connect to respective node
			   else
			   {
				   //we are reading a number
				   if( line.charAt(i) != ' ' )
				   {
					   //The "-1" is there because they start at 1 and I want to start at 0
					   m_integers.add( Character.getNumericValue(line.charAt(i) ) -1 );
				   }
			   }
				   
			   //System.out.printf("%c", line.charAt(i));
		   }
		   
		   //Represents the end of a line
		   if( !m_integers.isEmpty() )
		   {
			   if( m_integers.get( m_integers.size() - 1 ) != -1 )
				   m_integers.add(-1);
		   }
		   
	   }
	   br.close();	 
   }
   
   /************************************************
    * Sets up the adjacency matrix
    * @param choice
    ***********************************************/
	private static void setUpMatrix(int choice)
	{
		try {
			readTxtFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int testCaseIndex = 0;
		
		testCaseIndex = getIndex(choice);
		
		//System.out.println("\nDebug_TestCaseIndex-->"+ testCaseIndex + " Value-->" + m_integers.get(testCaseIndex) );	
				
		adjacencyMatrix = new int[ verticies.get(choice + 1 ) ][verticies.get(choice + 1 )];
		
		for( int i = 0; i < verticies.get(choice+1); i++)
		{
			//get to first index of this line
			while( m_integers.get(testCaseIndex) != -1 && (testCaseIndex+1) < m_integers.size() )
			{
				adjacencyMatrix[i][m_integers.get(testCaseIndex)] = 1;
				//System.out.printf(", "+m_integers.get(testCaseIndex) );
				testCaseIndex++;			
			}
			
			//get past the -1's
			testCaseIndex++;
		}
	}
	
	public static int getIndex(int testCase)
	{
		
		int index = 0;
		
		//test case 1 will loop 1 time, 
		//first through the numbers representing the first test case then through the negative
		//It will end up on the first index of test case 1
		for(int i = 0; i < testCase; i++)
		{
			for(int j = 0; j < verticies.get(i+1); j++)
			{
				//Loop through values of previous test case
				while( m_integers.get(index) != -1 && (index+1) < m_integers.size() )
				{
					++index;
				}
				//loop through the negatives to arrive at first index of this test cas
				++index;
			}
		}
		return index;	
	}
	

	public static void debug()
	{
		System.out.println("values");
		for(int i = 0; i < m_integers.size(); i++ )
		{
			System.out.print( m_integers.get(i) + "("+i+")" );
			if( m_integers.get(i) == -1 )
				System.out.println();
		}
		System.out.println( "\nVerticies" );
		for(int j = 0; j < verticies.size(); j++ )
		{
			System.out.print( verticies.get(j) );
		}
		System.out.println("\nAdjacency Array");
		print2DArray();
	}
	  
   private static void print2DArray( )
   {
	   for(int i = 0; i < adjacencyMatrix.length; i++)
	   {
	       for(int j = 0; j < adjacencyMatrix[i].length; j++)
	       {
	           System.out.print(adjacencyMatrix[i][j]);
	           if(j < adjacencyMatrix[i].length - 1) System.out.print(" ");
	       }
	       System.out.println();
	   }
   }
	
	   
}
