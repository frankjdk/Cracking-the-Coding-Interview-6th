package com.ch1.arraysandlists;

public class item1dot66 {

	// Given an image represented by an NxN matrix, where each pixel in the image is 4
	// bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
	public static void main(String[] args) {
		
		// 1 2 3	-->	7 4 1
		// 4 5 6	--> 8 5 2
		// 7 8 9 	--> 9 6 3
		String[][] nnArray= { {"1","2", "3"},{"4","5","6"}, {"7","8","9"}};

		
//		System.out.println("corners: " + nnArray[0][0]
//				+ nnArray[0][nnArray.length-1]
//						+ nnArray[nnArray.length-1][0]
//								+ nnArray[nnArray.length-1][nnArray.length-1]);
//		
		String[][] nnNewArray = new String[nnArray.length][nnArray[0].length];
		
		//turn 90 degrees clockwise - working
		for (int i = 0; i < nnArray.length; i++) {
			
			for (int j = 0; j < nnArray[i].length; j++) {
				
				nnNewArray[i][j]= nnArray[nnNewArray[i].length -1-j][i];
			}
		}
		
		//turn 90 degrees counter clockwise - working
//		for (int i = 0; i < nnArray.length; i++) {
//					
//			for (int j = 0; j < nnArray[i].length; j++) {
//						
//				nnNewArray[i][j]= nnArray[j][nnNewArray[j].length -1-i];
//			}
//		}
		
		
		
		
		//printing
		for (int i = 0; i < nnNewArray.length; i++) {
			
			for (int j = 0; j < nnNewArray[i].length; j++) {
				
				System.out.print(nnNewArray[i][j]);
			}
			System.out.print("\n");
		}
		

		
	}

}
