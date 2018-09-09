package com.ch2.linkedlist;

import java.util.LinkedList;
import java.util.List;

public class item2dot2 {

	//Implement an algorithm to find the nth to last element of a singly linked list
	public static void main (String[] args) {
		
		List<String> list = new LinkedList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		
		System.out.println(list.get(list.size() - 1));
		
	}
}
