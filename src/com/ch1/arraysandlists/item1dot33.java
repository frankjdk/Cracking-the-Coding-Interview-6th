package com.ch1.arraysandlists;

public class item1dot33 {

//	Design an algorithm and write code to remove the duplicate characters in a string
//	without using any additional buffer. 
	
//  NOTE: One or two additional variables are fine.
//	An extra copy of the array is not.
//	FOLLOW UP
//	Write the test cases for this method.
	public static void main (String[] args) {
		
//		String message = "testmessage";

//		String message = "abcdefghijklmnopqrstuvwxyz";
//		String message = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
		String message = "aabbccddeeffgghhiijjkkllmmnnooppqqrrssttuuvvwwxxyyzz";
		
		for (int i = 0; i < message.length(); i++) {
			String letter = message.substring(i, i+1);
			
			for (int j = 0; j < message.length(); j++) {
				if (i != j && letter.equals(message.substring(j, j+1))) {
					
					message= message.replaceFirst(letter, "");
					break;
				}
			}
		}
		
		System.out.println(message);
	}
}
