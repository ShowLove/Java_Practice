package myInterface;


public class Harris implements Company, Comparable<Company>{
		
		//private int pointsNeeded = 23; 
		private int finalD; 
		private int dTotal = 23; 
		private int sTotal = 0; 
		
	public int interviewSkills(int experience, int articulate, int onTheSpotSkills){
			
			int total; 
			
			sTotal = experience*3 + articulate*2 + onTheSpotSkills; 
			
		return sTotal; 
	}
	
	public int entryDifficulty(){
		
		int minimum = 0, maximum = 5, luck = 0; 
		luck = minimum + (int)(Math.random()*maximum); 
		
		dTotal = 5*3 + 5*2 + 5 - luck; 
		
		return dTotal + 2; 
	}
	
	public int finalDecision(){
		
		if( sTotal >= dTotal ){
			finalD = 1; 
			return 1; 
		}
		else if( sTotal <= dTotal ){
			finalD = 0;
			return 0; 
		}
		else 
			return 0; 
		
	}
	
	public void noDeclaredInInterface(){
		System.out.printf("this was not declared in the interface"); 
	}
	
	public int compareTo(Company other){
		return other.entryDifficulty() - dTotal; 
	}
	
	
	@Override 
	public String toString(){
		String decision; 
			if(finalD == 1){
				decision = " Harris has decided to give you a chance"; 
			}
			else{
				decision = " Harris has rejected you"; 
			}
		
		return "\nyou need "+ dTotal +" points to get an internship with Harris\n"
				+ " you have " + sTotal + decision; 
	}



}
