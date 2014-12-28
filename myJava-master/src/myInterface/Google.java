package myInterface;

public class Google implements Company, Comparable<Company>{
	
	//private int pointsNeeded = 55; 
	private int finalD = 0; 
	private int dTotal = 55; 
	private int sTotal = 0; 
	
	public int interviewSkills(int experience, int articulate, int onTheSpotSkills){
		
		sTotal = experience*3 + articulate*3 + onTheSpotSkills*3; 
		
		return sTotal; 
	}
	
	
	public int entryDifficulty(){
		
		int minimum = 0, maximum = 5, luck = 0; 
		luck = minimum + (int)(Math.random()*maximum); 
		
		dTotal = 5*3 + 5*3 + 5*3 - luck; 
		
		return dTotal + 5; 
	}
	
	public int finalDecision(){
		
		if((sTotal - dTotal) >= 0){
			finalD = 1; 
			return 1; 
		}
		else{
			finalD = 0;
			return 0; 
		}
		
	}
	
	public void testInterfaceFields(){
		System.out.printf("\n Static Fields: %d,%d,%d,%d,%d", a,b,c,e,f );
		int testInt;
		testInt = a +3; //this does not complain 
		//a = 5; // this complains because the field cannot be changed. 
		
	}
	
	public int compareTo(Company other ){
		return other.entryDifficulty() - dTotal; 
	}
	
	@Override 
	public String toString(){
		String decision; 
		if(finalD == 1){
			decision = " Google has decided to give you a chance"; 
		}
		else{
			decision = " Google has rejected you"; 
		}
	
	return "\nyou need "+ dTotal +" points to get an internship with Google\n"
			+ " you have " + sTotal + decision; 
	}




}
