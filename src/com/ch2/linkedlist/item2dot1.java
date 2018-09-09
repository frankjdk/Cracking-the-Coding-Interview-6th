package com.ch2.linkedlist;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class item2dot1 {

	
	// Write code to remove duplicates from an unsorted linked list 
	// FOLLOW UP 
	// How would you solve this problem if a temporary buffer is not allowed? 
	public static void main(String[] args) {
		List<String> thisLinkedList = new LinkedList<>();
		
		
		thisLinkedList.add("a");
		thisLinkedList.add("b");
		thisLinkedList.add("c");
		thisLinkedList.add("a");
		thisLinkedList.add("e");
		System.out.println(thisLinkedList.stream().distinct().collect(Collectors.toList())); // temporary buffer?
	}

}
