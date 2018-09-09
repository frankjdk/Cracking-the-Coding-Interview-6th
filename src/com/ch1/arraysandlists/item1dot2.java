package com.ch1.arraysandlists;

public class item1dot2 {

	// Check Permutation
	// Write a method to decide if two strings are anagrams/permutation or not.
	public static void main (String[] args) {
		
		String string1 = "testmessage";
		String string2 = "2egassemtset";

		for (int i = 0; i < string1.length(); i++) {

			string2 =  string2.replaceFirst(string1.substring(i, i+1), "");
		}
		
		System.out.println("Anagram = " + string2.isEmpty());
	}
}
