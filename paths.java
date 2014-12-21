/*Paths assignment
 * Oct. 19, 2014
 * CSII
 * Mauricio Diaz
 * Carlos Garzon
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class paths {
	
	//Stores every value of the .txt file
	static List<Integer> m_integers = new ArrayList<Integer>();
	//A List holding the number of intersections that nick must avoid
	static ArrayList<Integer> M_List = new ArrayList<Integer>();
	//A List holding the number of paths we must attempt
	static ArrayList<Integer> P_List = new ArrayList<Integer>();
	//A Hash Table that holds the M index values of each test case
	static Hashtable<Integer, Integer> M_Table = new Hashtable<Integer, Integer>();
	//A Hash Table that holds the P index values of each test case
	static Hashtable<Integer, Integer> P_Table = new Hashtable<Integer, Integer>();
	
	static int nTestCases;					// Holds the number of test cases
	static int[] mStartToEnd = new int[4]; 	// Holds star and end locations
	
    public static final int N = 0;
    public static final int NE = 1;
    public static final int E = 2;
    public static final int SE = 3;
    public static final int S = 4;
    public static final int SW = 5;
    public static final int W = 6;
    public static final int NW = 7;
	
   
    public static void main(String[] args)
   {	   
	   readTxtFile();
	   	
	   parseTxtFile();
	   
	   //Checks each Data Set
	   dataSet();
	   
   }//End of main
   
    
    
   private static void dataSet()
   {
	   //Loop that goes up until nTestCases
	   for(int i = 0; i < nTestCases; i++)
	   {	   
		   System.out.println("Data Set "+(i+1)+":\n");
		   
		   //Contains intersections that must be avoided
		   ArrayList<Integer> valuesM = getMList(i);
		   
		   //Contains Start and End coordinates
		   ArrayList<Integer> valuesP = getPList(i);
		   
		   //Finds perfect paths
		   findPaths(valuesM, valuesP);
		   
		   System.out.println("");
	   }
   }
   
   //Prints out each Test case and the # of perfect paths found
   private static void findPaths(ArrayList M, ArrayList P)
   {
	   int Direction = 0;
	   int numPerfectPathsA = 0;
	   int numPerfectPathsB = 0;
	   int numPerfectPaths = 0;
	   
	   int[][] myCity = new int[10][10];
	   
	   //Loop that goes up until P values
	   for(int j = 0; j < P.size()/4; j++)
	   {
		   //Fills array that holds start and end locations
		   fillStart2EndArray(j, P);
		   
		   //Sets up myCity array of Nick neighborhood
		   setUpArray( myCity, M);
		   
		   //Finds Direction we must travel in
		   Direction = findDirection();
		   
		   if( mStartToEnd[0] == mStartToEnd[2] && mStartToEnd[1] == mStartToEnd[3] )
		   {
			   numPerfectPaths = 1;
			   System.out.println("  Test Case "+(j+1)+": Nick can take "+ numPerfectPaths + " perfect path." );
		   }
		   else if( Direction == N )
		   {
			   numPerfectPaths = perfectPathsN(myCity, mStartToEnd[0], mStartToEnd[1], 0);
			   System.out.println("  Test Case "+(j+1)+": Nick can take "+ numPerfectPaths + " perfect paths." );;
		   }
		   else if( Direction == NE )
		   {
			   numPerfectPathsA = perfectPathsNE(myCity, mStartToEnd[0], mStartToEnd[1], 0);
			   numPerfectPathsB = perfectPathsEN(myCity, mStartToEnd[0], mStartToEnd[1], 0);
			   numPerfectPaths = ( numPerfectPathsA > numPerfectPathsB ) ? numPerfectPathsA : numPerfectPathsB;
			   System.out.println("  Test Case "+(j+1)+": Nick can take "+ numPerfectPaths + " perfect paths." );
		   }
		   else if( Direction == S )
		   {
			   numPerfectPaths = perfectPathsS(myCity, mStartToEnd[0], mStartToEnd[1], 0);
			   System.out.println("  Test Case "+(j+1)+": Nick can take "+ numPerfectPaths + " perfect paths." );
		   }
		   else if( Direction == SE )
		   {
			   numPerfectPathsA = perfectPathsSE(myCity, mStartToEnd[0], mStartToEnd[1], 0);
			   numPerfectPathsB = perfectPathsES(myCity, mStartToEnd[0], mStartToEnd[1], 0);
			   numPerfectPaths = ( numPerfectPathsA > numPerfectPathsB ) ? numPerfectPathsA : numPerfectPathsB;
			   System.out.println("  Test Case "+(j+1)+": Nick can take "+ numPerfectPaths + " perfect paths." );
		   }
		   else if( Direction == E )
		   {
			   numPerfectPaths = perfectPathsE(myCity, mStartToEnd[0], mStartToEnd[1], 0);
			   System.out.println("  Test Case "+(j+1)+": Nick can take "+ numPerfectPaths + " perfect paths." );
		   }
		   else if( Direction == W )
		   {
			   numPerfectPaths = perfectPathsW(myCity, mStartToEnd[0], mStartToEnd[1], 0);
			   System.out.println("  Test Case "+(j+1)+": Nick can take "+ numPerfectPaths + " perfect paths." );
		   }
		   else if( Direction == NW )
		   {
			   numPerfectPathsA = perfectPathsNW(myCity, mStartToEnd[0], mStartToEnd[1], 0);
			   numPerfectPathsB = perfectPathsWN(myCity, mStartToEnd[0], mStartToEnd[1], 0);
			   numPerfectPaths = ( numPerfectPathsA > numPerfectPathsB ) ? numPerfectPathsA : numPerfectPathsB;
			   System.out.println("  Test Case "+(j+1)+": Nick can take "+ numPerfectPaths + " perfect paths." );
		   }
		   else if( Direction == SW )
		   {
			   numPerfectPathsA = perfectPathsSW(myCity, mStartToEnd[0], mStartToEnd[1], 0);
			   numPerfectPathsB = perfectPathsWS(myCity, mStartToEnd[0], mStartToEnd[1], 0);
			   numPerfectPaths = ( numPerfectPathsA > numPerfectPathsB ) ? numPerfectPathsA : numPerfectPathsB;
			   System.out.println("  Test Case "+(j+1)+": Nick can take "+ numPerfectPaths + " perfect paths." ); 
		   }
	   }// End of P for loop
   }
    
   private static int perfectPathsN( int[][] myCity, int x, int y, int perfectPaths )
   {
	   //We have gone beyond our boundaries
	   if( x > 9 || x < 0 || y > 9 || y < 0)
		   return perfectPaths;
	   //We found a perfect Path
	   if( myCity[x][y] == 3 )
		  return ++perfectPaths;
	   //We hit a road block don't go this way
	   if( myCity[x][y] == 1 )
		   return perfectPaths;
	   
	   perfectPaths = perfectPathsN( myCity, (x-1), y, perfectPaths);
	   
	   return perfectPaths;
   }
   
   private static int perfectPathsS( int[][] myCity, int x, int y, int perfectPaths )
   {
	   //We have gone beyond our boundaries
	   if( x > 9 || x < 0 || y > 9 || y < 0)
		   return perfectPaths;
	   //We found a perfect Path
	   if( myCity[x][y] == 3 )
		  return ++perfectPaths;
	   //We hit a road block don't go this way
	   if( myCity[x][y] == 1 )
		   return perfectPaths;
	   
	   perfectPaths = perfectPathsS( myCity, (x+1), y, perfectPaths);
	   
	   return perfectPaths;
   }
   
   private static int perfectPathsE( int[][] myCity, int x, int y, int perfectPaths )
   {
	   //We have gone beyond our boundaries
	   if( x > 9 || x < 0 || y > 9 || y < 0)
		   return perfectPaths;
	   //We found a perfect Path
	   if( myCity[x][y] == 3 )
		  return ++perfectPaths;
	   //We hit a road block don't go this way
	   if( myCity[x][y] == 1 )
		   return perfectPaths;
	   
	   perfectPaths = perfectPathsE( myCity, x, (y+1), perfectPaths);
	   
	   return perfectPaths;
   }
   
   private static int perfectPathsW( int[][] myCity, int x, int y, int perfectPaths )
   {
	   //We have gone beyond our boundaries
	   if( x > 9 || x < 0 || y > 9 || y < 0)
		   return perfectPaths;
	   //We found a perfect Path
	   if( myCity[x][y] == 3 )
		  return ++perfectPaths;
	   //We hit a road block don't go this way
	   if( myCity[x][y] == 1 )
		   return perfectPaths;
	   
	   perfectPaths = perfectPathsW( myCity, x, (y-1), perfectPaths);
	   
	   return perfectPaths;
   }
   
   //can only move South or West to reach destination: first try S then W
   private static int perfectPathsSW( int[][] myCity, int x, int y, int perfectPaths )
   {
	   //We found a perfect Path
	   if( myCity[x][y] == 3 )
		  return ++perfectPaths;
	   //We have gone beyond our boundaries
	   if( x > 9 || x < 0 || y > 9 || y < 0)
		   return perfectPaths;
	   //We have gone above our endPoint no need to continue
	   if( x > mStartToEnd[2] )
		   return perfectPaths;
	   //We have gone to far left no need to continue
	   if( y < mStartToEnd[3])
		   return perfectPaths;
	   //We hit a road block don't go this way
	   if( myCity[x][y] == 1 )
		   return perfectPaths;
	   
	   //Move recursively South
	   if( x + 1 < 0 ) return perfectPaths;
	   perfectPaths = perfectPathsSW( myCity, (x+1), y, perfectPaths);
	   //Move recursively West
	   if( y - 1 < 0 ) return perfectPaths;
	   perfectPaths = perfectPathsSW( myCity, x, (y-1), perfectPaths );
	   		   
	   return perfectPaths;
   }
   
   //can only move South or West to reach destination: first try W then S
   private static int perfectPathsWS( int[][] myCity, int x, int y, int perfectPaths )
   {
	   //We found a perfect Path
	   if( myCity[x][y] == 3 )
		  return ++perfectPaths;
	   //We have gone beyond our boundaries
	   if( x > 9 || x < 0 || y > 9 || y < 0)
		   return perfectPaths;
	   //We have gone above our endPoint no need to continue
	   if( x > mStartToEnd[2] )
		   return perfectPaths;
	   //We have gone to far left no need to continue
	   if( y < mStartToEnd[3])
		   return perfectPaths;
	   //We hit a road block don't go this way
	   if( myCity[x][y] == 1 )
		   return perfectPaths;

	   //Move recursively West
	   if( y - 1 < 0 ) return perfectPaths;
	   perfectPaths = perfectPathsWS( myCity, x, (y-1), perfectPaths );
	   //Move recursively South
	   if( x + 1 < 0 ) return perfectPaths;
	   perfectPaths = perfectPathsWS( myCity, (x+1), y, perfectPaths);
	   		   
	   return perfectPaths;
   }
   
   //can only move North or West to reach destination: first try N then W
   private static int perfectPathsNW( int[][] myCity, int x, int y, int perfectPaths )
   {
	   //We found a perfect Path
	   if( myCity[x][y] == 3 )
		  return ++perfectPaths;
	   //We have gone beyond our boundaries
	   if( x > 9 || x < 0 || y > 9 || y < 0)
		   return perfectPaths;
	   //We have gone above our endPoint no need to continue
	   if( x < mStartToEnd[2] )
		   return perfectPaths;
	   //We have gone to far left no need to continue
	   if( y < mStartToEnd[3])
		   return perfectPaths;
	   //We hit a road block don't go this way
	   if( myCity[x][y] == 1 )
		   return perfectPaths;
	   
	   //Move recursively North
	   if( x - 1 < 0 ) return perfectPaths;
	   perfectPaths = perfectPathsNW( myCity, (x-1), y, perfectPaths);
	   //Move recursively West
	   if( y - 1 < 0 ) return perfectPaths;
	   perfectPaths = perfectPathsNW( myCity, x, (y-1), perfectPaths );
	   		   
	   return perfectPaths;
   }
   
   //can only move North or West to reach destination: first try W then N
   private static int perfectPathsWN( int[][] myCity, int x, int y, int perfectPaths )
   {
	   //We found a perfect Path
	   if( myCity[x][y] == 3 )
		  return ++perfectPaths;
	   //We have gone beyond our boundaries
	   if( x > 9 || x < 0 || y > 9 || y < 0)
		   return perfectPaths;
	   //We have gone above our endPoint no need to continue
	   if( x < mStartToEnd[2] )
		   return perfectPaths;
	   //We have gone to far left no need to continue
	   if( y < mStartToEnd[3])
		   return perfectPaths;
	   //We hit a road block don't go this way
	   if( myCity[x][y] == 1 )
		   return perfectPaths;
	   
	   //Move recursively West
	   if( y - 1 < 0 ) return perfectPaths;
	   perfectPaths = perfectPathsWN( myCity, x, (y-1), perfectPaths );
	   //Move recursively North
	   if( x - 1 < 0 ) return perfectPaths;
	   perfectPaths = perfectPathsWN( myCity, (x-1), y, perfectPaths);
	   		   
	   return perfectPaths;
   }
   
   //South East can only move North or East to reach destination: first try N then E
   private static int perfectPathsNE( int[][] myCity, int x, int y, int perfectPaths )
   {
	   //We found a perfect Path
	   if( myCity[x][y] == 3 )
		  return ++perfectPaths;
	   //We have gone beyond our boundaries
	   if( x > 9 || x < 0 || y > 9 || y < 0)
		   return perfectPaths;
	   //We have gone above our endPoint no need to continue
	   if( x < mStartToEnd[2] )
		   return perfectPaths;
	   //We have gone to far right no need to continue
	   if( y > mStartToEnd[3])
		   return perfectPaths;
	   //We hit a road block don't go this way
	   if( myCity[x][y] == 1 )
		   return perfectPaths;
	   
	   //Move recursively North
	   if( x - 1 < 0 ) return perfectPaths;
	   perfectPaths = perfectPathsNE( myCity, (x-1), y, perfectPaths);
	   //Move recursively East
	   if( y + 1 > 9 ) return perfectPaths;
	   perfectPaths = perfectPathsNE( myCity, x, (y+1), perfectPaths );
	   		   
	   return perfectPaths;
   }
   
   //South East can only move North or East to reach destination: first try E then N
   private static int perfectPathsEN( int[][] myCity, int x, int y, int perfectPaths )
   {
	   //We found a perfect Path
	   if( myCity[x][y] == 3 )
		  return ++perfectPaths;
	   //We have gone beyond our boundaries
	   if( x > 9 || x < 0 || y > 9 || y < 0)
		   return perfectPaths;
	   //We have gone above our endPoint no need to continue
	   if( x < mStartToEnd[2] )
		   return perfectPaths;
	   //We have gone to far right no need to continue
	   if( y > mStartToEnd[3])
		   return perfectPaths;
	   //We hit a road block don't go this way
	   if( myCity[x][y] == 1 )
		   return perfectPaths;
	   
	   //Move recursively East
	   if( y + 1 > 9 ) return perfectPaths;
	   perfectPaths = perfectPathsEN( myCity, x, (y+1), perfectPaths );
	   //Move recursively North
	   if( x - 1 < 0 ) return perfectPaths;
	   perfectPaths = perfectPathsEN( myCity, (x-1), y, perfectPaths);
	   		   
	   return perfectPaths;
   }
   
   //South East can only move South or East to reach destination: first try S then E
   private static int perfectPathsSE( int[][] myCity, int x, int y, int perfectPaths )
   {
	   //We found a perfect Path
	   if( myCity[x][y] == 3 )
		  return ++perfectPaths;
	   //We have gone beyond our boundaries
	   if( x > 9 || x < 0 || y > 9 || y < 0)
		   return perfectPaths;
	   //We have gone below our endPoint no need to continue
	   if( x > mStartToEnd[2] )
		   return perfectPaths;
	   //We have gone to far right no need to continue
	   if( y > mStartToEnd[3])
		   return perfectPaths;
	   //We hit a road block don't go this way
	   if( myCity[x][y] == 1 )
		   return perfectPaths;
	   
	   //Move recursively South
	   if( x + 1 > 9 ) return perfectPaths;
	   perfectPaths = perfectPathsSE( myCity, (x+1), y, perfectPaths);
	   //Move recursively East
	   if( y + 1 > 9 ) return perfectPaths;
	   perfectPaths = perfectPathsSE( myCity, x, (y+1), perfectPaths );
	   		   
	   return perfectPaths;
   }
   
   //South East can only move South or East to reach destination: first try E then S
   private static int perfectPathsES( int[][] myCity, int x, int y, int perfectPaths )
   {
	   //We found a perfect Path
	   if( myCity[x][y] == 3 )
		  return ++perfectPaths;
	   //We have gone beyond our boundaries
	   if( x > 9 || x < 0 || y > 9 || y < 0)
		   return perfectPaths;
	   //We have gone below our endPoint no need to continue
	   if( x > mStartToEnd[2] )
		   return perfectPaths;
	   //We have gone to far right no need to continue
	   if( y > mStartToEnd[3])
		   return perfectPaths;
	   //We hit a road block don't go this way
	   if( myCity[x][y] == 1 )
		   return perfectPaths;
	   
	   //Move recursively East
	   if( y + 1 > 9 ) return perfectPaths;
	   perfectPaths = perfectPathsES( myCity, x, (y+1), perfectPaths );
	   //Move recursively South
	   if( x + 1 > 9 ) return perfectPaths;
	   perfectPaths = perfectPathsES( myCity, (x+1), y, perfectPaths);
	   		   
	   return perfectPaths;
   }	   
   
   /***********************
    * DIRECTIONS
    * 
    * NORTH = 0
    * NE = 1
    * E = 2
    * SE = 3
    * S = 4
    * SW = 5
    * W = 6
    * NW = 7
    **********************/
   private static int findDirection()
   {
	   int Direction = 0;
	   
	   // 0, 2 = x coordinates
	   // 1, 3 = y coordinates
	   
	   //check if NORTH, SOUTH EAST OR WEST
	   if( mStartToEnd[1] == mStartToEnd[3] && mStartToEnd[0] > mStartToEnd[2] ) //North
		   Direction = N;
	   if( mStartToEnd[1] == mStartToEnd[3] && mStartToEnd[0] < mStartToEnd[2] ) //South
		   Direction = S;
	   if( mStartToEnd[0] == mStartToEnd[2] && mStartToEnd[1] < mStartToEnd[3] ) //East
		   Direction = E;
	   if( mStartToEnd[0] == mStartToEnd[2] && mStartToEnd[1] > mStartToEnd[3] ) //West
		   Direction = W;
	   if( mStartToEnd[0] > mStartToEnd[2] && mStartToEnd[1] < mStartToEnd[3] ) //North East
		   Direction = NE;
	   if( mStartToEnd[0] > mStartToEnd[2] && mStartToEnd[1] > mStartToEnd[3] ) //North West
		   Direction = NW;
	   if( mStartToEnd[0] < mStartToEnd[2] && mStartToEnd[1] < mStartToEnd[3] ) //South East
		   Direction = SE;
	   if(mStartToEnd[0] < mStartToEnd[2] && mStartToEnd[1] > mStartToEnd[3] ) //South West
		   Direction = SW;
		   
	   return Direction;
   }
   
   private static ArrayList<Integer> getMList( int testCase )
   {
	   ArrayList<Integer> valuesP = new ArrayList<Integer>();
	   
	   //Gets the index just after the M value
	   int n1 = M_Table.get(testCase) + 1;
	   
	   //gets the index at next P value
	   int n2 = P_Table.get(testCase);
	   
	   //Add all the M values to the list
	   for(int i = n1; i < n2; i++)
		   valuesP.add( m_integers.get(i) );
	   
	   return valuesP;
   }
   
   private static ArrayList<Integer> getPList( int testCase )
   {
	   ArrayList<Integer> valuesM = new ArrayList<Integer>();
	   
	   //gets the index just after P value
	   int n1 =  P_Table.get(testCase) + 1;

	   //gets the index at the next M value or last index
	   int n2 = ( (testCase + 1) < nTestCases ) ? M_Table.get(testCase + 1) : m_integers.size();
		   
	  //Add all the P values to the list
	  for(int i = n1; i < n2; i++)
		  valuesM.add( m_integers.get(i) );
	   
	   return valuesM;
   }
   
   private static void readTxtFile()
   {
	   //Get all the values of the .txt file and put in m_integers ArrayList
		try 
		{
			Scanner fileScanner = new Scanner(new File("paths.txt"));
		
		   while (fileScanner.hasNextInt())
		   {
		      m_integers.add(fileScanner.nextInt());
		   }
		   
		   fileScanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
   }

   private static void parseTxtFile()
   {
	   int N = 0; //NUMBER OF TEST CASES. First test case
	   int currentTestCase = 0; //represents the test case we are currently on
	   
	   	for(int i = 0; i < m_integers.size(); i++)
	   	{
	   		if( i == 0 )
	   		{
	   			N = m_integers.get(i);
	   			i++;
	   		}
	   		
			//Add M value to list. Represents the spots that need to be avoided
			M_List.add(m_integers.get(i));
			
			//Store the M index in a hash table
			M_Table.put(currentTestCase, i);
			
			//Put i at P value
			i += M_List.get(currentTestCase)*2+1;
			
			//Add P value to P_List. Represents the paths that need to be taken from point a to b
			P_List.add(m_integers.get(i));
			
			//Store the P index in a hash table
			P_Table.put(currentTestCase, i);
			
			//System.out.println("\ni-"+ i + "  P-"+P_List.get(currentTestCase) );	//DEBUG
			//should put i at value just before the next M
			i += P_List.get(currentTestCase)*4;
			
			//System.out.println("i_at_endOfLoop-"+i + " currentValue-" + m_integers.get(i));	//DEBUG
			//We are now at the next test case
			currentTestCase++;
	   	}
	   	
	   	nTestCases = N;
   }
   
   private static void print2DArray( int[][] myCity )
   {
	   for(int i = 0; i < myCity.length; i++)
	   {
	       for(int j = 0; j < myCity[i].length; j++)
	       {
	           System.out.print(myCity[i][j]);
	           if(j < myCity[i].length - 1) System.out.print(" ");
	       }
	       System.out.println();
	   }
   }
   
   private static void setUpArray( int[][] myCity, ArrayList<Integer> valuesM)
   {
	   //Reset array values to zero
	   for(int i = 0; i < 10; i++)
	   {
		   for(int j = 0; j < 10; j++)
		   {
			   myCity[i][j] = 0;
		   }
	   }
	   //Set all values that Nick has to avoid to 1
	   for( int i = 0; i < valuesM.size(); i++)
	   {
		   myCity[valuesM.get(i)][valuesM.get(i+1)] = 1;
		   i++;
	   }
	   //Set start value to 2 and end value to 3
	   myCity[ mStartToEnd[0] ][ mStartToEnd[1] ] = 2;
	   myCity[ mStartToEnd[2] ][ mStartToEnd[3] ] = 3;
   }
   
   private static void fillStart2EndArray( int testP,  ArrayList<Integer> valuesP)
   {
	   //testP represents which of the respective start/end P
	   //locations within a given test case we are using
	   
	   //Each start and end coordinate has 4 values
		   int firstCoordianate = testP*4;
		   
		   for(int i = 0; i < 4; i++)
		   {
			   mStartToEnd[i] = valuesP.get(firstCoordianate + i);
		   }
	   }
}