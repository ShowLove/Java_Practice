public class QuadraticHash 
{
	int m_nTableSize = 10000;
	DataObject[] m_ObjectArray;
	
	public QuadraticHash()
	{
		m_ObjectArray = new DataObject[m_nTableSize];
	} 
	
	public QuadraticHash(int nTableSize)
	{
		m_nTableSize = nTableSize;
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	public void put( String strKey, DataObject objData )
	{
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		long i = 1, index = lHash;

		while( m_ObjectArray[(int)(index)] != null)
		{
			//Not allowing duplicates
			if( m_ObjectArray[(int)(index)].GetKey() == strKey )
				return;
			
			index = (lHash + i * i);
			//make certain our index is not outside the capacity of the hash table
			index = index %m_nTableSize;
			i++;
		}
		m_ObjectArray[(int)index] = objData;
		m_ObjectArray[(int)index].setIndex((int)index);
	}

	public DataObject get( String strKey )
	{
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		long i = 1, index = lHash;

		while( m_ObjectArray[(int)(index)].GetKey() != strKey )
		{		
			index = (lHash + i * i);
			//make certain our index is not outside the capacity of the hash table
			index = index %m_nTableSize;
			i++;
		}	

		return( m_ObjectArray[(int)(index)] );
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

	public void printTable()
	{
		for (int i = 0; i < m_nTableSize; i++)
		{	
			if( m_ObjectArray[(int)(i)] == null )
				System.out.printf("%02d: %s\n", i,"(null)" );
			else
				System.out.println( i + ": " +  m_ObjectArray[(int)(i)].GetKey() );
		}		
	}
}

