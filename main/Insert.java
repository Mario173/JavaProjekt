package main;

import java.util.ArrayList;

import matrix.Matrix;

/**
 * Class used for handling all matrix inserting
 * @author Mario
 *
 */
public class Insert {
	// posalje se u spremnik di cemo cuvat matrice
	Matrix lastInserted;
	
	/**
	 * Default constructor
	 */
	public Insert() {
		super();
	}
	
	/**
	 * Method used for in serting matrices from database
	 */
	public void insertFromDatabase() {
		
	}
	
	/**
	 * Method used for inserting matrices from files
	 * @param filePath string representing path to the file
	 */
	public void insertFromFile(String filePath) {
		
	}
	
	/**
	 * Method used for inserting matrices from the text box
	 * @param matrix string representation of a matrix
	 */
	public void insertFromTextBox(String matrix) {
		@SuppressWarnings("unused")
		int i = 0, numOfRows = 0, numOfCols = 0, countElements = 0, countParentheses = 0;
		ArrayList<ArrayList<Double>> values = new ArrayList<>(); // 
		matrix = matrix.trim(); // remove all leading and trailing whitespaces
		while(i != matrix.length()) {
			Character curr = matrix.charAt(i);
			if(curr == '{') {
				countParentheses++;
			} else if(curr == '}') {
				countParentheses--;
				numOfRows++;
				if(i != matrix.length() - 1 && numOfCols != countElements) {
					// baci gresku jer nisu svi redovi isto veliki
				}
				countElements = 0;
			} else if(Character.isDigit(curr)) {
				String s = curr.toString();
				i++;
				while(i != matrix.length() && matrix.charAt(i) != ',' && matrix.charAt(i) != '}') {
					s += matrix.charAt(i);
					i++;
				}
				i--;
				try {
					values.get(numOfRows).add(Double.parseDouble(s)); // malo bolje taj try-catch
				} catch(Exception e) {
					e.printStackTrace();
				}
				countElements++;
			} else {
				// nije dobro -> baci gresku
			}
		}
		// ne return nego ga sibni u memoriju pa cemo je od tamo vadit
		// return new Matrix(numOfRows, numOfCols, values);
	}
}
