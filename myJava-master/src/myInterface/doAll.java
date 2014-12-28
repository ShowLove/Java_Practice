package myInterface;

import java.util.Arrays;
import java.util.Scanner;


public class doAll {

	public static void main(String args[]){
		
		 int experience, articulate, onTheSpotSkills; 


			
		System.out.printf("enter a number 1-5 inheritance Version\nfor experience, articulate, onTheSpotSkills: "); 
		Scanner scanner = new Scanner(System.in);
		articulate = scanner.nextInt(); experience = scanner.nextInt(); 
		onTheSpotSkills = scanner.nextInt();  	
		
		//polymorphism //cant do the following:      Company harris = new Company(); 
		Harris harris =  new Harris(); 
		Company ti = new TexasInstruments(); 
		Google google = new Google(); 
		
		//set up what i needed for Harris 
		harris.interviewSkills(experience, articulate, onTheSpotSkills); 
		harris.entryDifficulty(); 
		harris.finalDecision();  
		//set up what i needed for TI 
		ti.interviewSkills(experience, articulate, onTheSpotSkills); 
		ti.entryDifficulty(); 
		ti.finalDecision();
		//set up what i needed for TI 
		google.interviewSkills(experience, articulate, onTheSpotSkills); 
		google.entryDifficulty(); 
		google.finalDecision();
		
		//this is an array of companys that will be sorted acording to difficulty 
		//the sorting will be possible because of implementation of Comparable in these classses 
		Company[] companyList = new Company[] { harris, google, ti }; 
		Arrays.sort(companyList);
		System.out.println("The following is sorted starting from the most difficult company to get into"); 
		System.out.println("After: " + Arrays.toString(companyList) ); 
		
		//harris.noDeclaredInInterface(); //since its in Harris but not in Company  it will throw error
		
		//this method was not declared in the interface. However, the compiler will not 
		//complain. This is because we stored google in a type google and not in a type 
		//company 
		google.testInterfaceFields();

	}
	
}
