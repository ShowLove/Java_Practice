package mapsListsAndStuff;

import java.util.TreeMap;
import java.util.*;



			//must use the interface Comparable for treeMaps
public class collections { 
	
	public static void main(String args[] ){
		
		//create a string array and a TreeSet
		String[] strArray = new String[]{"and","because","car","car","aba"}; 
		TreeSet<String> myTree = new TreeSet<String>(); 
		
		//add all the elements of the string ot the TreeSet		
		for(int i = 0; i < strArray.length; i++ ){
			myTree.add(strArray[i]); 
		}
		//add all the elements of the TreeSet back into the 
		//string lexiographically ordered: no repeats 
		int i = 0; 
		for(String w: myTree){
			strArray[i++] = w; 
		}
		//print strArray
		for(i = 0; i < strArray.length; i++){
			System.out.println("strArray("+i+"-->"+strArray[i]+")");
		}
		
		String strTmp; 
		//transfer the array to a List 
		ArrayList<String> myList = new ArrayList<String>(); 
	
		for(i = 0; i < strArray.length; i++){
			myList.add(strArray[i]); //add elements of the array to the list 
			strTmp = myList.get(i);  //
			System.out.println(i+"List: -->"+strTmp+","
					+myList.contains("aba") ); //will mark true all 4 times 
			
		}
		
		myList.remove(1); // remove third index then print again 
		System.out.println("after remove"); 
		for(i = 0; i < strArray.length; i++){
			myList.add(strArray[i]); //add elements of the array to the list 
			strTmp = myList.get(i);  //
			System.out.println(i+"List: -->"+strTmp+","
					+myList.contains("aba") ); //will mark true all 4 times 
			
		}
			
	}

}
