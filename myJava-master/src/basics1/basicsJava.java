package basics1;
import java.util.Arrays;
import java.util.Scanner;

public class basicsJava {
	
	public static String stringObjectA = new String("hello"); 
	public static String stringObjectB = new String("hello"); 

		
	public static void main(String args[]){
		
		//find the length of a string 
		String stringArray1 =  "ABCDEFG";
		System.out.println("LenghtOfString("+stringArray1.length() + ")");
		
		//reading in stuff 
		System.out.println("give it some values like: num(7)Double(7.7)word(hello)"); 
		Scanner sc = new Scanner(System.in);
		int scNum1 = sc.nextInt(); 				//suppose we enter 7
		double scNum2 = sc.nextDouble(); 		//suppose we enter 7.5
		String readInString = sc.next();	 	//suppose we say hello
		System.out.println("num("+scNum1+")"+"Double("+scNum2+")"
							+"word("+readInString+")"); 
		
		
		//find substring of a string
		System.out.println("Substring_from2to5("+stringArray1.substring(0,3)+")"); 
		System.out.println("Substring_from2to5("+stringArray1.substring(2,5)+")"); 
		
		//find the number of array elements 
		int[] numArray = new int[] { 1,2,3,4,5,6,7,8 }; 
		char[] charArray = new char[] {'a','b','c','d','e','f','g'}; 
		String[] stringArray = new String[] { "hello","how","are","you","doing" }; 
		String[] stringArray2 = new String[3]; 
		System.out.println("enter 3 words into the string array"); 
		int[] tstArray = new int[3]; 
		for(int i = 0; i < 3; i ++){
			tstArray[i] = i; 
			stringArray2[i] = sc.next(); 
		}
		System.out.println("charArray(" + Arrays.toString(charArray) + ")"); 
		System.out.println("stringArray(" + Arrays.toString(stringArray) + ")"); 
		System.out.println("stringArray2(" + Arrays.toString(stringArray2) + ")"); 
		System.out.println("tstArray(" + Arrays.toString(tstArray) + ")"); 
		System.out.println("lengthOfSingleArray(" + numArray.length + ")"); 
		
		//difference between equality and identity 
		if( stringObjectA == stringObjectB )  		
			System.out.println("This shouldn't print the contents are the same but the objects are not"); 
		else if( stringObjectA != stringObjectB )
			System.out.println("this prints because the objects are different"); 
		if(stringObjectA.equals(stringObjectA) )
			System.out.println("this should print and it does "); 
		
		//threads 
		Thread myThread = new Thread(){
			//it will run the print then sleep for a 100 mili seconds 
			public void run(){
				for(int i = 0; i < 10; i++){
					System.out.println("This is myThread"); 
					try {
						sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}
		}; //this is end of thread
		myThread.start();
		Thread myThreadB = new Thread(){
			//it will run the print then sleep for a 100 mili seconds 
			public void run(){
				for(int i = 0; i < 10; i++){
					System.out.println("This is myThreadB"); 
					try {
						sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}
		}; //this is end of thread
		myThreadB.start();
		
		
		
	}//end of main

}
