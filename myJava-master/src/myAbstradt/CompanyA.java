package myAbstradt;

import myInterface.Company;

public abstract class CompanyA {

	//private int pointsNeeded = 23; 
	protected int finalD; 
	protected int dTotal = 23; 
	protected int sTotal = 0; 
	
	public abstract int interviewSkills(int experience, int articulate, int onTheSpotSkills);
	
	public abstract int entryDifficulty();
	
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
