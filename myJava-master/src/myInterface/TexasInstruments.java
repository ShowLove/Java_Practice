package myInterface;

public class TexasInstruments implements Company, Comparable<Company>{
	
	//private int pointsNeeded = 53; 
	private int finalD = 0; 
	private int dTotal = 53; 
	private int sTotal = 0; 
	
	public int interviewSkills(int experience, int articulate, int onTheSpotSkills){
		
		sTotal = experience*3 + articulate*3 + onTheSpotSkills*3; 
		
		return sTotal; 
	}
	
	
	public int entryDifficulty(){
		
		int minimum = 0, maximum = 5, luck = 0; 
		luck = minimum + (int)(Math.random()*maximum); 
		
		dTotal = 5*3 + 5*3 + 5*3 - luck; 
		
		return dTotal + 3; 
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

	public int compareTo(Company other ){
		return other.entryDifficulty() - dTotal; 
	}
	
	@Override 
	public String toString(){
		String decision; 
		if(finalD == 1){
			decision = " Texas Instruments has decided to give you a chance"; 
		}
		else{
			decision = " Texas Instruments has rejected you"; 
		}
	
	return "\nyou need "+ dTotal +" points to get an internship with TI\n"
			+ " you have " + sTotal + decision; 	}
	
	

}
