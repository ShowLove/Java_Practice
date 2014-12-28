package myInheritance;

public class TexasInstrumentsI extends CompanyI{
	
	@Override
	public int interviewSkills(int experience, int articulate, int onTheSpotSkills){
		
		sTotal = experience*3 + articulate*3 + onTheSpotSkills*3; 
		
		return sTotal; 
	}
	
	@Override
	public int entryDifficulty(){
		
		int minimum = 0, maximum = 5, luck = 0; 
		luck = minimum + (int)(Math.random()*maximum); 
		
		dTotal = 5*3 + 5*3 + 5*3 - luck; 
		
		return dTotal + 3; 
	}

}
