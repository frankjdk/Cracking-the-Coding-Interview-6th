package com.ch1.arraysandlists;

public class item1dot7 {

	//Write an algorithm such that if an element in an MxN matrix is 0, 
	// its entire row and column is set to 0 
	public static void main(String[] args) {

		String[][] nnArray= { {"1","2", "3"},{"4","5","0"}, {"7","8","9"}};
		
//		String[][] nnArray= { {"1","2", "3", "4"},{"5","0", "7", "0"}, {"0", "10", "11", "12"}, {"13", "14", "15", "16"}};
		String[][] newNNArray = new String[nnArray.length][nnArray[0].length];
		
		for (int i = 0; i < nnArray.length; i++) {
			
			for (int j = 0; j < nnArray[i].length; j++) {
				
				if (nnArray[i][j] == "0") {
					
					for (int k = 0; k < newNNArray.length; k++) {
						for (int l = 0; l < newNNArray[k].length; l++) {
							
							if (k == i || l == j) {
								newNNArray[k][l] = "0";
							}
						}			
					}
				} else if (newNNArray[i][j] == null) {
					newNNArray[i][j] = nnArray[i][j];
				}
			}
			
		}
		
		//printing
		for (int i = 0; i < newNNArray.length; i++) {
			
			for (int j = 0; j < newNNArray[i].length; j++) {
				
				System.out.print(newNNArray[i][j]);
			}
			System.out.print("\n");
		}
	}

}
