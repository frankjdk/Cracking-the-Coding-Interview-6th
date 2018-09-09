package com.ch1.arraysandlists;

public class item1dot5 {

	
	//One Away: 
	//There are three types of edits that can be performed on strings: 
	//insert a character, remove a character, or replace a character. 
	
	//Given two strings, write a function to check if they are one edit (or zero edits) away. 
	//EXAMPLE 
	//pale, ple -> true 
	//pales. pale -> true 
	//pale. bale -> true 
	//pale. bake -> false 
	public static void main(String[] args) {

		String before = "pale";
		String after = "bale";
		
		
		String longerWord = before.length() >= after.length() ? before : after;
		String shorterWord = before.length() < after.length() ? before : after;
		
		
		for (int i = 0; i < shorterWord.length(); i++) {
			String letter = shorterWord.substring(i, i+1);
			longerWord = longerWord.replaceFirst(letter, "");
			
		}

		System.out.println(longerWord);
		System.out.println("One edit? : " + (longerWord.length() == 1));
	}

}
