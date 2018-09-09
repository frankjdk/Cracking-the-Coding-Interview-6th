package com.ch1.arraysandlists;

import java.util.HashSet;
import java.util.Set;

public class item1dot1 {

	// Implement an algorithm to determine if a string has all unique characters. 
	public static void main (String[] args) {
		
		String message = "abcdefghijklmnopqrstuvwxyz";
		
//		Set<String> uniqueSet = new HashSet<>();
//		for (int i = 0; i < message.length(); i ++ ) {
//			uniqueSet.add(message.substring(i, i+1));
//		}
// 		System.out.println("unique = " + (uniqueSet.size() == message.length()));
		
		
		
		// What if you can not use additional data structures?
		for (int i = 0; i < message.length(); i ++ ) {
			String letter = message.substring(i, i+1);
			
			for (int j = i+ 1; j < message.length() ; j ++ ) {
				if (letter.contentEquals(message.substring(j, j+1))) {
					
					System.out.println("not unique: " + letter + " == " + message.substring(j, j+1));
					
					return;
				}
			}
		}
		
		System.out.println("all unique");
		
	}
}
