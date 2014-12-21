package dP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class duel {
	
	//Stores every value of the .txt file
	static List<Integer> m_integers = new ArrayList<Integer>();
	//Stores current keys
	static List<Integer> m_keys = new ArrayList<Integer>();
	//Stores current values
	static List<Integer> m_valuesL = new ArrayList<Integer>();
	//Gets the values of each test case
	static Hashtable<Integer, Integer> m_values = new Hashtable<Integer, Integer>();
	//Stores the index we are on
	static int m_index;
	//Additional paths due to repeats
	static int m_ePaths = 0;
	//Current amount of value pairs we have
	static int m_valuePairs = -1;
	//Current amount of things to arrange M
	static int m_M = -1;
	//Check if we have finished our search
	static boolean we_Done = false;
	//Check if we have no valid paths
	static boolean m_noPaths = false;
	
	public static void main(String[] args)
	{
		m_index = 0;
		
		readTxtFile();
		
		//Check for case where everything is empty
		if( m_integers.get(m_index) == 0 && m_integers.get(m_index+1) == 0 )
		{
			System.out.println("0");
		}
		else
			while( !weDone() )
			{
				getValues();
				//System.out.println( m_values );	//DEBUG
				extraPathsDueToRepeats();
				if( !validPaths() )
				{
					System.out.println("0");
				}
				else
				{
					if( m_ePaths > 1 )
						System.out.println("2");
					else
						System.out.printf("%d\n", (m_ePaths + 1) );
				}
					
				
				//System.out.println("We have valid paths -->" + validPaths() + "m/nValue"+m_M+m_valuePairs+"\n" );	//DEBUG 
				
				m_index++;
				m_index++;
				
				if( weDone() )
					break;
			}
		
	}
	
	private static boolean weDone()
	{
		if( m_integers.get(m_index) == 0 && m_integers.get(m_index+1) == 0 )
			we_Done = true;
		
		return we_Done;
	}
	
	private static boolean validPaths()
	{
		return ( m_M > m_valuePairs ) ? true : false;
	}
	
	private static void extraPathsDueToRepeats()
	{
		 //Values are our keys
		 Hashtable<Integer, Integer> testTable = new Hashtable<Integer, Integer>();
		 //Current values
		 
		 for(int i = 0; i < m_valuePairs; i++)
		 {
			 //System.out.printf("chk(%d)",  m_keys.get(i) );	//DEBUG
			 //If a certain value (which is our key) appears twice that means we add a path
			 if( testTable.containsKey( m_values.get( m_keys.get(i) )   ) )
			 {
				 m_ePaths++;
				 //System.out.printf("WeFoundKey(%d)", m_keys.get(i) ); //Debug
			 }
			 else	//Put all values as our keys
				 testTable.put( m_values.get( m_keys.get(i) ), 0);		 	
		 }
		 //System.out.println( "testTable"+ testTable + "m_ePaths->"+m_ePaths);	//DEBUG
/*		 
		 //Check if there is a key that doesn't map to a value
		 for(int i = 0; i < m_valuePairs; i++)
		 {
			 	for(int j = 0; j < m_valuePairs; j++)
			 	{
					 //we found an inner dependent key and value
				 	//first: check if they the key  is a value
				 	//second check if the value is a key
					 if( testTable.containsKey( m_keys.get(i) )  &&  m_keys.contains( m_valuesL.get(i)   ))
					 {
						 System.out.printf("chk(%d)", m_keys.get(i));
						 m_noPaths = true;
					 }
			 	}
		 }
	*/	 
		 //System.out.println("m_noPaths-->"+m_noPaths);
		 testTable.clear();
	}
	
	private static void getValues()
	{
		//Reset values for new test case
		m_ePaths = 0;
		m_keys.clear();
		m_valuesL.clear();
		m_values.clear();
		m_valuePairs = 0;
		m_M = 0;
		m_noPaths = false;
		
		//Our m_index should be at "n" (n+1 = m)
		m_M = m_integers.get(m_index);
		m_valuePairs = m_integers.get(m_index+1);
		
		
		for(int i = 0; i < m_valuePairs; i++)
		{
			m_index++;
			m_index++;	//index is at next pair
			
			//add keys to list
			m_keys.add( m_integers.get(m_index) );
			//add values to list
			m_valuesL.add( m_integers.get(m_index +1));

			m_values.put( m_integers.get(m_index) , m_integers.get(m_index+1));
		}	//after for loop index should be at n
	}
	
	private static void readTxtFile()
	{
		   //Get all the values of the .txt file and put in m_integers ArrayList
		try 
		{///Users/carlosgarzon/Desktop/duel.txt
			Scanner fileScanner = new Scanner(new File("input.txt"));
		
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
}
