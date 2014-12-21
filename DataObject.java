public class DataObject 
{
	public DataObject()
	{
		m_strKey = "";
		//for separate chaining
		next = null;
	}
	
	public DataObject(String strKey)
	{
		m_strKey = strKey;
		//for separate chaining
		next = null;
	}
	
	//constructor for separate chaining
	public DataObject(String strKey, DataObject object)
	{
		next = object;
		m_strKey = strKey;
	}
	
	
	String m_strKey = "";
	//for separate chaining
	DataObject next;
	
	
	int Index;
	
	public void setIndex(int theIndex)
	{
		Index = theIndex;
	}
	
	public int getIndex()
	{
		return Index;
	}
	
	public String GetKey() 
	{
		return( m_strKey );
	}
	
	//for separate chaining
	public DataObject getNextNode()
	{
		return next;
	}
	
	//for separate chaining
	public void setNextNode(DataObject nextObject)
	{
		next = nextObject;
	}
	
}
