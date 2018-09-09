package com.ch1.arraysandlists;

public class item1dot4 {

	//Palindrome Permutation: 
	//Given a string, write a function to check if it is a permutation of a palindrome. 
	
	//A palindrome is a word or phrase that is the same forwards and backwards. 
	//A permutation is a rearrangement of letters. 
	//The palindrome does not need to be limited to just dictionary words
	
	
	//EXAMPLE 
	//Input: Tact Coa 
	//Output: True (permutations: "taco cat". "atco cta". etc.) 
	public static void main(String[] args) {
		
		String word = "Tact Coa";
		
		String letters = word.replaceAll("\\W", "").toLowerCase();
		System.out.println(letters);
		
		int singleLetterCount = 0;
		for (int i = 0; i < letters.length(); i++) {
			
			String letter = letters.substring(i, i+1);
			String singleLetters = letters.replaceAll("[^"+ letter + "]", "");
			System.out.println(singleLetters);
			
			if (singleLetters.length() % 2 != 0) {
				singleLetterCount++;
			}
		}
		
		System.out.println("Palindrome Permutation? : " + !(singleLetterCount > 1));
	}
}
