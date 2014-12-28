package myAbstradt;

public class HarrisA extends CompanyA{
	
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
	
}
