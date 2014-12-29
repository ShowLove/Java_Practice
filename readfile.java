/*
	reading from text file to string array where the array holds the words in each line
	then put each of those string arrays into a list. In this way we can access any line 
	and any word within each line
import javax.swing.JOptionPane;
	public static List<String> sentences = new ArrayList<String>();
	public static ArrayList<String[]> wordsInLines = new ArrayList<String[]>();	
	public static void main(String[] args){
	    String fileName = JOptionPane.showInputDialog(null, "Enter file name and path");
	    testHNL(new File(fileName));
  
	    for(int i = 0; i < sentences.size(); i++)
	    {
	    	//System.out.println( sentences.get(i) );
	    	String[] words = sentences.get(i).replaceAll("(^|\\s)'([\\w']+)'(\\s|$)", "$1$2$3").split("[^\\w']+");
	    	wordsInLines.add( words );
	    	System.out.println(Arrays.toString(wordsInLines.get(i)));
	    }
	}
*/
	// /Users/carlosgarzon/Desktop/bereshit.txt
	public static void testHNL(File f){
			
	    try {
	        Scanner s = new Scanner(new FileInputStream(f));
	        while(s.hasNextLine()){
	           //System.out.println("There is another line!  :: "+s.nextLine());
	           sentences.add(s.nextLine());
	        }
	        //System.out.println("There are no more lines :'(");
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	   
	}
	

	//read in a file of numbers integer by integer into a list
   //static List<Integer> m_integers = new ArrayList<Integer>();
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

   //gets input byte per byte into a byte array called bytes
      public static byte[] getBytesFromFile(File file) throws IOException 
   {
        InputStream is = new FileInputStream(file);
    
		// Get the size of the file
        long length = file.length();
    
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
    
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];
    
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead = is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
    
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
    
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }

    //static List<Integer> m_integers = new ArrayList<Integer>();
    /******************************************************* Twocolouring.java
    * Read info into 	veticies: tells you how many nodes are in each test case
    * 					m_integers: actual values with new lines represented with -1s
    * @throws IOException
    * Reads a text file that has a line a number that represents numVertecies and other 
    * lines full of numbers of nodes that connect to respective node. when we have  a new
    * line we have a -1 all this goes into a list called m_integers. All this is to set 
    * up an adjacency matrix
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