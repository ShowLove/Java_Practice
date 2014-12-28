package myInterface;

public interface Company {
	
	//i can declare fields for an inter face in any of the following ways 
	int a = 0; 
	public int b = 1; 
	public static int c = 2; 
	public final int e = 3; 
	public static final int f = 3; 
	
	//this however will give me an error 
	//public static transient int d = 3;
	
	/*
	Data members declared in an interface are by default public static final. In 
	another word they are constants.
	"transient", "private" and "protected" modifiers are not accepted when 
	marking data members in an interface.
	For methods in an interface, only "public" and "abstract" are allowed
	 */
	
	
	int interviewSkills(int experience, int articulate, int onTheSpotSkills); 
	int entryDifficulty(); 
	public int finalDecision(); 
	public int compareTo(Company other);

	
}

/* All methods in an interface are public by default, so you don't need to mark them as public. 
 * They're also abstract, so you don't have to provide any method bodies. Doing that is the 
 * responsibility of any class that implements the interface. */
/*any class that implements an interface must provide bodies for the methods of the interface.*/

//child classes cannot have constructors 
/*
 * A problem that you get when you allow constructors in interfaces comes from the possibility to implement several 
 * interfaces at the same time. When a class implements several interfaces that define different constructors, the class 
 * would have to implement several constructors, each one satisfying only one interface, but not the others. It will be 
 * impossible to construct an object that calls each of these constructors.
 * 
 * What you have described ("So you could be sure that some fields in a class are defined for every implementaiton of this 
 * interface.", "If a define a Interface for this class so that i can have more classes which implement the message 
 * interface, i can only define the send method and not the constructor") is exactly what abstract classes are for.
 */
