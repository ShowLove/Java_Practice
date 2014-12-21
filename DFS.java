/* DFS Assignment
 * Oct. 23, 2014
 * CS2
 * Carlos Garzon
 * Mauricio Diaz
 */


import java.util.*;
import java.io.*;



public class DFS
{
	static List<Integer> m_integers = new ArrayList<Integer>();
	static List<Integer> verticies = new ArrayList<Integer>();
		
	static boolean [][] matrix;
	static int [][] paths;
	
	// Initialize 'matrix' from input file
	public DFS(String filename) throws Exception
	{
		readTxtFile();
				
		setUpMatrix(0);
		
		//print2DArray();
		
		setUpPaths(1);
		
		//print2DArrayPaths();
		
		checkPath( 2 );
		for(int i = 0; i < verticies.get(1); i++)
		{
			if( checkPath(i) )
				System.out.println("CORRECT!\n");
			else
				System.out.println("SORRY, NOT A VALID SEARCH!\n");
		}
		
	}
	
	private static boolean checkPath(int path)
	{
		boolean weGood = true;
		//the 'value' at "pahts[path][i]" corresponds to the vertex we are currently testing
		//the 'value' at "pahts[path][i+1]" corresponds to the where we want to go
		for(int i = 0; i < verticies.get(0) - 1; i++)
		{
			if( ( matrix[ paths[path][i] - 1][ paths[path][i+1] ]) == false )
				return false;			
		}
		return weGood;
	}
	
	private static void setUpPaths(int choice)
	{
		paths = new int[ verticies.get(choice) ][ verticies.get(choice-1) ];
		int testCaseIndex = 0;
		
		testCaseIndex = getIndex(choice);
						
		for( int i = 0; i < verticies.get(choice); i++)
		{
			//get to first index of this line
			while( m_integers.get(testCaseIndex) != -1 && (testCaseIndex+1) < m_integers.size() )
			{
				for(int j = 0; j < verticies.get(choice - 1); j++)
				{
					paths[i][j] = m_integers.get(testCaseIndex);
					testCaseIndex++;
				}
			}
			
			//get past the -1's
			while( m_integers.get(testCaseIndex) == -1 && (testCaseIndex+1) < m_integers.size() )
			{
				testCaseIndex++;
			}
		}		
	}
	
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
				
		matrix = new boolean[ verticies.get(choice)+1 ][verticies.get(choice)+1];
		
		for( int i = 0; i < verticies.get(0); i++)
		{
			//get to first index of this line
			while( m_integers.get(testCaseIndex) != -1 && (testCaseIndex+1) < m_integers.size() )
			{
				matrix[i][m_integers.get(testCaseIndex)] = true;
				testCaseIndex++;			
			}
			
			//get past the -1's
			while( m_integers.get(testCaseIndex) == -1 && (testCaseIndex+1) < m_integers.size() )
			{
				testCaseIndex++;
			}
		}
	}
	
	//Will get the index of the first number in this test case
	public static int getIndex( int testCase )
	{
		int index = 0;
		
		//We want to test the first test case
		if(testCase == 0)
		{
			//get to first index of this line
			while( m_integers.get(index) == -1 && (index+1) < m_integers.size() )
			{
				index++;
			}
			return index;
		}
		
		//we are assuming test case indexing starts at 0
		for(int i = 0; i < testCase; i++ )
		{//Iterating through each test case
			for(int j = 0; j < verticies.get(i) + 1; j++)
			{//Iterating through each line
				
				//get to first index of this line
				while( m_integers.get(index) == -1 && (index+1) < m_integers.size() )
				{
					index++;
				}
				
				if( j == (verticies.get(i)) && i == (testCase - 1))
				{				
					break;
				}
				
				//Move to the next line
				while( m_integers.get(index) != -1 && (index+1) < m_integers.size() )
				{
					index++;
				}
			}
		}
	
		
		//get to first index of this line
		while( m_integers.get(index) == -1 && (index+1) < m_integers.size() )
		{
			++index;
		}
		
			
		return index;
	}
	
	   private static void readTxtFile() throws IOException
	   {
		   BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		   String line;
		   char number = '0';
		   
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
						   m_integers.add( Character.getNumericValue(line.charAt(i) ) );
					   }
				   }
			   }
			   
			   //Represents the end of a line
			   m_integers.add(-1);
		   }
		   br.close();	 
	   }
	   
	   private static void print2DArray( )
	   {
		   for(int i = 0; i < matrix.length; i++)
		   {
		       for(int j = 0; j < matrix[i].length; j++)
		       {
		           //System.out.print(matrix[i][j]);
		           if(j < matrix[i].length - 1) System.out.print(" ");
		       }
		   }
	   }
	   
	   private static void print2DArrayPaths()
	   {
		   for(int i = 0; i < paths.length; i++)
		   {
		       for(int j = 0; j < paths[i].length; j++)
		       {
		           //System.out.print(paths[i][j]);
		           if(j < paths[i].length - 1) System.out.print(" ");
		       }
		   }
	   }
	
	// Iterative BFS method.
	public void BFS(int start)
	{
		// Iterative BFS places vertices in a queue. When we pull a vertex out of
		// the queue, we process it (in this case, print it to the screen), then
		// place all its unvisited neighbors in the queue. We mark each vertex as
		// visited as it goes into the queue, because that ensures we never place
		// a vertex into the queue more than once. (That can significantly reduce
		// the space complexity of this algorithm when dealing with a large, dense
		// graph.)
		
		// A queue is an abstract data type. We need to decide what underlying
		// data structure to use to implement it. Java will not allow you to do,
		// e.g.: Queue<Integer> q = new Queue<Integer>();
		Queue<Integer> q = new LinkedList<Integer>();
		boolean [] visited = new boolean[matrix.length];
		
		// Start by adding the start vertex to the queue. It will be the first
		// thing dequeued, at which point we'll print it and add its neighbors
		// to the queue.
		q.add(start);
		visited[start] = true;
		
		while (!q.isEmpty())
		{
			// Remove a node from the queue and process it. If we were searching
			// for a particular node (a "goal"), this is where we would check
			// whether we had found it. If so, we would terminate the BFS. However,
			// since the goal of this BFS method is simply to print all nodes of a
			// graph in BFS order, we simply print this node and move on.
			int node = q.remove();
			//System.out.println(node);
			
			// Add all neighbors of 'node' to the queue (as long as they haven't
			// been visited already).
			for (int i = 0; i < matrix.length; i++)
				if (matrix[node][i] && !visited[i])
				{
					visited[i] = true;
					q.add(i);
				}
		}
	}
	
	// Wrapper to our recursive DFS method. This one sets up the 'visited' array.
	public void DFS(int start)
	{
		// Recall that I mentioned the Arrays.fill() method here. If we want to
		// fill a boolean array with all 'true' values, we could use, e.g.,
		// Arrays.fill(myArray, true).
		boolean [] visited = new boolean[5];
		DFS(start, visited);
	}
	
	private void DFS(int node, boolean [] visited)
	{
		// As soon as we encounter a node in our DFS traversal, we mark it as
		// visited. This ensures that we won't get stuck in an infinite loop if
		// our graph has a cycle.
		visited[node] = true;
		
		// If we were searching for a particular vertex (a "goal"), this is where we
		// would check whether we have found the goal. If so, we would terminate the
		// DFS. However, since the purpose of this particular method is simply to
		// print all vertices in DFS order, we just print this vertex and move on.
		//System.out.println(node);
		
		// Now call DFS() on all of this node's neighbors that haven't already
		// been visited.
		for (int i = 0; i < matrix.length; i++)
			if (matrix[node][i] && !visited[i])
				DFS(i, visited);
	}
	
	public static void main(String [] args) throws Exception
	{
		DFS g = new DFS("input.txt");
	}
}