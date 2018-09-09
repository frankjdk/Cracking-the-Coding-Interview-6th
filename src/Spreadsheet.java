//on default package based on instructions how to run
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *  Per requirements of Redmart - Software Engineer position
 *  
 *  1. compile the package private class: javac Spreadsheet.java
 *  2. run .class with: cat spreadsheet.txt | java Spreadsheet
 * @author Frank Gener Herrera
 *
 */
public class Spreadsheet {

	private static final String REGEX_REFERENCE = "^[A-Za-z]{1}\\d+$";
	private static final String REGEX_VALUE = "^-?\\d+(\\.\\d+)?$";
	private static final String REGEX_VALUE_NO_DECIMAL = "^-?\\d+$";

	private static final String REGEX_ARITHMETIC = "^[\\+\\-\\*\\/]$";
	private static final String REGEX_INCREMENT = "^[\\+]{2}$";
	private static final String REGEX_DECREMENT = "^[\\-]{2}$";

	public static void main(String[] args) {

//		String[] test = { "3 2", "A2", "4 5 *", "A1", "A1 B2 / 2 +", "3", "39 B1 B2 * /" };	
//		String[] test = { "3 2", "4", "2 2 *", "-4 ", "A1", "A2 ++ ", "B1" };
//		List<String> argList = Arrays.asList(test);

		List<String> argList = readSpreadsheetData();
		Map<String, String> resultMap = initializeMap(argList);
		loopMap(resultMap);
		
		//prints the result
		System.out.println(resultMap);
	}

	/**
	 * Checks the provided map if all expressions and references are resolved
	 * 
	 * @param resultMap
	 *            the map containing the spreadsheet
	 */
	private static void loopMap(Map<String, String> resultMap) {

		boolean loopBack;
		do {
			loopBack = false;
			loopBack = loopValues(resultMap);

		} while (loopBack);
	}

	/**
	 * Loops through the map's values until all values are long values only. 
	 * Any value matching REGEX_REFERENCE (ex: A1, B2, etc) are replaced by their reference's value. 
	 * Any value matching REGEX_ARITHMETIC performs arithmetic operation assuming two float values are before it. 
	 * Any value matching REGEX_INCREMENT / REGEX_DECREMENT performs increment/decrement arithmetic operation assuming one float value is before it.
	 * Any value matching REGEX_VALUE_NO_DECIMAL and not derived from other references are reinserted but with decimal values
	 * 
	 * @param resultMap
	 *            the spreadsheet
	 * @return boolean if further looping is required; if all values of the map are
	 *         not yet floats
	 */
	private static boolean loopValues(Map<String, String> resultMap) {

		DecimalFormat formatter = new DecimalFormat("#.00000");
		boolean loopBack = false;

		for (String keyNM : resultMap.keySet()) {
			String value = resultMap.get(keyNM);

			String[] valueSplit = value.split(" ");

			for (int i = 0; i < valueSplit.length; i++) {

				 if (valueSplit[i].matches(REGEX_VALUE_NO_DECIMAL) && valueSplit.length == 1) {
					 
					 resultMap.replace(keyNM, valueSplit[i] + ".00000"); 
				}
				 
				else if (valueSplit[i].matches(REGEX_REFERENCE)) {

					String reference = valueSplit[i];
					checkCyclicDependency(reference, resultMap);

					String newValue = value.replaceFirst(valueSplit[i], resultMap.get(valueSplit[i]));
					resultMap.replace(keyNM, newValue);
					loopBack = true;

				} else if (valueSplit[i].matches(REGEX_INCREMENT) && i >= 1 // one float value before the expression
						&& valueSplit[i - 1].matches(REGEX_VALUE)) {

					String previousFloat1 = valueSplit[i - 1];

					String newFloat = formatter.format(Float.parseFloat(previousFloat1) + 1);
					String newExpression = value.replaceFirst(previousFloat1 + " \\" + valueSplit[i], newFloat);
					resultMap.replace(keyNM, newExpression);

				} else if (valueSplit[i].matches(REGEX_DECREMENT) && i >= 1 // one float value before the expression
						&& valueSplit[i - 1].matches(REGEX_VALUE)) {

					String previousFloat1 = valueSplit[i - 1];
					String newFloat = formatter.format(Float.parseFloat(previousFloat1) - 1);
					String newExpression = value.replaceFirst(previousFloat1 + " \\" + valueSplit[i], newFloat);
					resultMap.replace(keyNM, newExpression);

				} else if (valueSplit[i].matches(REGEX_ARITHMETIC) && i >= 2 // two float values before the expression
						&& valueSplit[i - 1].matches(REGEX_VALUE) && valueSplit[i - 2].matches(REGEX_VALUE)) {

					loopBack = true;

					String previousFloat2 = valueSplit[i - 2];
					String previousFloat1 = valueSplit[i - 1];
					String newFloat = "";

					if (valueSplit[i].equals("+")) {

						newFloat = formatter
								.format(Float.parseFloat(previousFloat2) + Float.parseFloat(previousFloat1));

					} else if (valueSplit[i].equals("-")) {

						newFloat = formatter
								.format(Float.parseFloat(previousFloat2) - Float.parseFloat(previousFloat1));

					} else if (valueSplit[i].equals("*")) {

						newFloat = formatter
								.format(Float.parseFloat(previousFloat2) * Float.parseFloat(previousFloat1));

					} else if (valueSplit[i].equals("/")) {

						newFloat = formatter
								.format(Float.parseFloat(previousFloat2) / Float.parseFloat(previousFloat1));
					}

					String newExpression = value
							.replaceFirst(previousFloat2 + " " + previousFloat1 + " \\" + valueSplit[i], newFloat);
					resultMap.replace(keyNM, newExpression);
				}
			}
		}
		return loopBack;
	}

	/**
	 * Retrieves the spreadsheet data from the spreadsheet.txt file as a result of
	 * 'cat' command
	 * 
	 * @return list of values from spreadsheet.txt as cell values
	 */
	private static List<String> readSpreadsheetData() {
		List<String> argList = new ArrayList<>();
		try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {

			String arg = null;
			while ((arg = input.readLine()) != null) {
				System.out.println(arg);
				argList.add(arg);

			}

		} catch (IOException e) {
			System.out.println(e);
			System.out.println("Issue encountered reading from file. Exiting...");
			System.exit(-1);
		}

		return argList;
	}

	/**
	 * Sets up the key-value pairs of the map which imitates the spreadsheet. Keys
	 * are the column-row / nm values (ex: A1, B2, etc) and values are the cells
	 * 
	 * @param argList
	 *            retrieved cell values
	 * @return the spreadsheet represented as a map
	 */
	private static Map<String, String> initializeMap(List<String> argList) {
		String[] sheetDimension = argList.get(0).split(" ");
		
		checkDimension(sheetDimension, argList);
		int nColumn = Integer.valueOf(sheetDimension[0]);
		Map<String, String> resultMap = new LinkedHashMap<>(); // used linkedhashmap to retain insert order
		int nColumnCount = 1;
		int mRowCount = 1;
		for (int argIndex = 1; argIndex < argList.size(); argIndex++) {

			char mCountLetter = (char) (mRowCount + 64);

			resultMap.put("" + mCountLetter + nColumnCount, argList.get(argIndex).trim());

			if (nColumnCount >= nColumn) {
				nColumnCount = 1;
				mRowCount++;
			} else {
				nColumnCount++;
			}
		}

		return resultMap;
	}

	
	/**
	 * Checks if a reference has a value which references itself. Exits if so
	 * Ex: C1 = D1 + 2, but D2 = C1 + 5
	 * @param reference the column row value
	 * @param wholeMap the spreadsheet
	 */
	private static void checkCyclicDependency(String reference, Map<String, String> wholeMap) {

		String subreferenceString = wholeMap.get(reference);
		for (String subreference : subreferenceString.split(" ")) {
			if (subreference.equals(reference)) {
				System.out.println("cyclic dependency detected: " + reference + " has value " + subreferenceString
						+ " which references itself. Exiting...");
				System.exit(-1);
			}
		}
	}
	
	/**
	 * Provided dimensions must be checked if compatible format
	 * @param sheetDimension the n x m values
	 * @param argList the list of data (dimension & cells) provided
	 */
	private static void checkDimension(String[] sheetDimension, List<String> argList) {
		if(sheetDimension.length != 2) {
			System.out.println("Sheet dimension not compatible: must be two-dimensional n x m ");
			System.exit(-1);
		} else if (! (sheetDimension[0].matches("^\\d+$") && sheetDimension[1].matches("^\\d+$"))) {
			System.out.println("Sheet dimension not compatible: must be numbers ");
			System.exit(-1);
		} else {
			double numberOfCells = Double.parseDouble(sheetDimension[0]) * Double.parseDouble(sheetDimension[1]);
			
			if (numberOfCells != argList.size() - 1) {
				System.out.println("Sheet dimension equal to number of cells found ");
				System.exit(-1);
			}
		}
	}
}
