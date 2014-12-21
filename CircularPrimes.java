package entropy;

import java.lang.Math;
import java.lang.reflect.Array;
import java.lang.String;

import java.util.Arrays;

public class CircularPrimes 
{
    
    private static int maxCheck;
    private static int totalOfCircularPrimes = 0;
    
    public static void main( String[] args )
    {
    	CircularPrimes cp = new CircularPrimes( 1000000 );
    	cp.findCirclePrimes();
    	cp.printAnswer();
    }
    
    public CircularPrimes(int maxCheck) {
        this.maxCheck = maxCheck;
    }
    
    public void findCirclePrimes() {
        
        totalOfCircularPrimes = 0;
        
        for(int i = 2; i <= maxCheck; i++) {
            if(checkPrime(i)) {
                if(checkCircular(i)) {
                    totalOfCircularPrimes++;
                }
            }
        }
    }
    
    public void printAnswer() {
        System.out.printf("The number of circular primes under %d is %d\n", 
                            maxCheck, totalOfCircularPrimes);
    }
    
    private boolean checkCircular(int checkNum) {
    
        char[] circleArray = Integer.toString(checkNum).toCharArray();
        
        int arrayLength = Array.getLength(circleArray);
        
        char[] tempArray = new char[arrayLength];
        String test;
        
        for(int i = 0; i < arrayLength; i++) {
          
            for(char c = 1; c < arrayLength; c++) {
                tempArray[c - 1] = circleArray[c]; 
            }
            tempArray[arrayLength - 1] = circleArray[0];
            test = new String(tempArray);
            if(checkPrime(Integer.valueOf(test)) == false) {
                return false;
            }
            circleArray = tempArray;
            tempArray = new char[arrayLength];
        }
        return true;
    }
    
    private boolean checkPrime(int checkNum) {
	    int i = 2;
	    double maxCheck;

	    maxCheck = Math.sqrt((double) checkNum);

	    while(i <= maxCheck) {
	        if(checkNum % i == 0) {
	            return false;
	        }
	        i++;
	    }
	    return true;
	}
}
