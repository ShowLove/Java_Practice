public class HashExperiments 
{
	public static void main(String[] args) 
	{
		
		//Hashtable<String,DataObject> lh = new Hashtable<String,DataObject>();
		//LinearHash lh = new LinearHash();
		//QuadraticHash qh = new QuadraticHash();
		SeparateChainingHash sch = new SeparateChainingHash();
		
		
		// Start the clock.	
		long start = System.currentTimeMillis();
		
		for( int i=0; i<Lists.ListOne.length; i++ )
		{
			//sch.put( Lists.ListOne[i], new DataObject( Lists.ListOne[i] ) );
		}	
		
		//sch.printTable();
		//sch.expand_table();
		//sch.printTable();
		
		//DataObject tmpData = sch.get( Lists.ListOne[3] );	
		//System.out.println("Your get retrieved -->" + tmpData.GetKey() );
		
		
		long end = System.currentTimeMillis();
		// Print out the time it took.
		System.out.println("Took "+(end-start)+" ms.");
	}

}
