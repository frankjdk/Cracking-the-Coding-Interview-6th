package com.ch1.arraysandlists;

public class item1dot6 {

	// String Compression
	// Implement a method to perform basic string compression using the counts of repeated characters.
	// For example, the string aabcccccaaa would become a2b1c5a3. 
	// If the "compressed" string would not become smaller than the original string, 
	// your method should return the original string. 
	
	// You can assume the string has only uppercase and lowercase letters (a - z). 
	public static void main(String[] args) {

		
		String word = "aabcccccaaa";
		
		for (int i = 0; i < word.length(); i++) {
			
			String letter = word.substring(i, i+1);
			
			String wordManip = word;
			
			int count = 0;
			int firstIndex = word.length() + 1;
//			while(word.matches(".*[" + letter + "]")) {
				count++;
				
				if (word.indexOf(letter) <= firstIndex) {
					firstIndex =  word.indexOf(letter);
				}
				
				word = word.replaceFirst(letter, "");
				
				
			}
			
			if (count > 0) {
				word = word.replaceFirst(word.substring(i, i+1), letter + count);	
			} else {
				word = word.replaceFirst(word.substring(i, i+1), letter);	
			}
			
			System.out.println(word);
		}

	}

}
