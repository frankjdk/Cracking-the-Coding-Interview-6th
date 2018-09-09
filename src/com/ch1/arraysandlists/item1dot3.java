package com.ch1.arraysandlists;

public class item1dot3 {

	// URLif
	//Write a method to replace all spaces in a string with ‘%20’.
	public static void main (String[] args) {
		
		String message = " test me      ssage ";
		System.out.println(message.replaceAll("\\s+", "%20"));
	}
}
