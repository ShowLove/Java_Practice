package basics1;

public class fistTestMain {
	
	public static void main(String[] args){
		
		firstTestClass obj1 = new firstTestClass(); 
		
		//obj1.strings();
		//obj1.scanner();
		obj1.math();
		//obj1.tester = 0; //this was a public var in testOne
		
		//keep in mind
		/* static methods
		 * Declaration:	public static returnedType foo(parameters){..}
		 * use in main:	returnedValue = MyClass.foo(arguments); 
		 * as opposed2:	MyClass objName = new MyClass();  
		 * 
		 * public String toString(){ return "wht method does"; }
		 * 
		 * int y = randGen.nextInt(100); //generates random #'s from 0 to 100 
		*/
		
	}
	
}
