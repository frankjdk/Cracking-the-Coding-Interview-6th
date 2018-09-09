package com.ch1.arraysandlists;

public class item1dot8 {

	//Assume you have a method isSubstring which checks if one word is a substring of another  
	//Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one call
	//to isSubstring (i e , “waterbottle” is a rotation of “erbottlewat”) 
	
	
	public static void main(String[] args) {

		String word = "waterbottle";
		
//		String rotatedWord = "erbottlewat";
		
		String rotatedWord = "bottlewater";
		String wordFirstLetter = word.substring(0, 1);
		
		String[] rotatedWordSplit = rotatedWord.split(wordFirstLetter);
		
		
		if (rotatedWordSplit.length > 0) {
			for (int i = 0; i < rotatedWordSplit.length; i++) {
				 
				if (word.indexOf(rotatedWordSplit[i]) >= 0) {
					word = word.replaceFirst(rotatedWordSplit[i], "");
				}
			}
			
			if (word.equals(wordFirstLetter)) {
				System.out.println("isSubstring");
			}
			else {
				System.out.println("not isSubstring");
			}
		} else {
			System.out.println("not isSubstring");
		}
	}

}
