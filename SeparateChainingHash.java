package hashTables;

public class SeparateChainingHash {
	
	int m_nTableSize = 10000;
	DataObject[] m_ObjectArray;
	
	//Constructor
	public SeparateChainingHash()
	{
		m_ObjectArray = new DataObject[m_nTableSize];
	} 
	
	//Constructor with size parameter
	public SeparateChainingHash(int nTableSize)
	{
		m_ObjectArray = new DataObject[nTableSize];

	}
	
	public void put( String strKey, DataObject objData)
	{
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		DataObject[] tmp =  m_ObjectArray;
		
		//If the element is null we have never initialized it
		if (tmp[(int)(lHash)] == null)
		{
			tmp[(int)(lHash)] = objData;
		}
		//We have already initialized this implement the linked list
		else if(tmp[(int)(lHash)] != null)
		{
			//While we haven't reached the end of the LL
			while(tmp[(int)(lHash)] != null)
			{
				//If teh next element is null we insert our object there
				if( tmp[(int)(lHash)].next == null )
				{
					tmp[(int)(lHash)].setNextNode(objData);
					break;
				}
				//Traverse the linked list
				tmp[(int)(lHash)] = tmp[(int)(lHash)].getNextNode();
			}
			
		}	
	}

	public DataObject get( String strKey )
	{
		//Use the hash function to get our hash
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		long index = lHash;
		DataObject[] tmp =  m_ObjectArray;
		
		//Traverse the object until we find the key
		while( tmp[(int)(index)].GetKey()  != (strKey)  )
		{		
			tmp[(int)(lHash)] = tmp[(int)(lHash)].next;
		}	
		
		return( tmp[(int)(lHash)] );
	}
	
	void expand_table()
	{
		int i, new_size, index;
		
		new_size = next_prime(m_nTableSize * 2 + 1);
		
		//DataObject[] new_ObjectArray = new DataObject[new_size];
		DataObject[] old_ObjectArray = m_ObjectArray;
		m_ObjectArray = new DataObject[new_size];
		
		// rehash elements from the old table into the new table
		for (i = 0; i < m_nTableSize; i++)
		{
			if (old_ObjectArray[i] != null)
			{
				index = old_ObjectArray[(int)(i)].getIndex();
				m_ObjectArray[(int)index] = old_ObjectArray[i];					
			}
		}
		m_nTableSize = new_size;		
	}
	
	// determines whether an integer is prime
	private int is_prime(int n)
	{
		int i;
		
		for (i = 3; i * i <= n; i++)
			if (n % i == 0)
				return 0;
		
		return 1;
	}

	// a function to find the next prime number, beginning at n
	private int next_prime(int n)
	{
		if (n % 2 == 0)
			n++;
		
		while ( is_prime(n) == 0 )
			n += 2;
		
		return n;
	}
/* 	//for debugging
	public void printTable()
	{
		int i = 0;	 
		DataObject[] tmpObject =  m_ObjectArray;
		
		for (i = 0; i < m_nTableSize; i++)
		{	
			if( m_ObjectArray[(int)(i)] == null )
				System.out.printf("%02d: %s\n", i,"(null)" );
			else
			{				
				System.out.println( i + ": " +  tmpObject[i].GetKey() );
				while(tmpObject[(int)(i)].next != null)
				{
					tmpObject[(int)(i)] = tmpObject[(int)(i)].next;
					System.out.println( i + "-->" + tmpObject[i].GetKey() );
					//tmpObject[(int)(i)] = tmpObject[(int)(i)].next;
				}
			}
		}		
	}
*/
}
