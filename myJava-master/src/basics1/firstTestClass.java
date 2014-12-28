package basics1;

import java.util.Scanner;

public class firstTestClass {
	
	public int tester; 
	
	//System.out.print(""+""+"");
	//System.out.print(""+ +""); 
	
	public void strings(){
		String myString = "s1(this is a literal)"; 
		String myString2 = new String("s2(creating a new object in memory)"); 
		String myString3 = "123456789";
		System.out.print(myString+myString2+"\n"); 
		System.out.print("s1Lenght("+myString.length()+")");
		System.out.print("substring29("+myString3.substring(2,9)+")");

		
	}
	//output
	/*s1(this is a literal)s2(creating a new object in memory)
s1Lenght(21)substring29(3456789)*/
	
	public void scanner(){
		Scanner sc = new Scanner(System.in);
		int in; 
		double  doub; 
		String myString = new String(); 
		
		in = sc.nextInt(); doub = sc.nextDouble(); 
		System.out.print("intDouble("+ in + doub +")\n");
		
		
		System.out.print("intDouble("+ in + doub +")\n");

	}
	//output
	/*7 7.8
intDouble(77.8)*/
	
	public void math(){
		int x =1, y = 2; 
		
		int min = Math.min(x, y); 		
		double rand = 5*Math.random() + 10; //this ranges from 10->15
		
		System.out.print("min("+ min +")"+"rand("+ rand +")"); 
		rand = Math.round(rand); 
		System.out.print("rounded("+rand+")\n"); 
	}
	//output
	//min(1)rand(13.480873671708817)rounded(13.0)
	//keep in mind//
	/*break, continue(skips current iteration)

	 */

}

