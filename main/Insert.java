package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import exceptions.WrongInsertException;
import matrix.Matrix;

/**
 * Class used for handling all matrix inserting
 * @author Mario
 *
 */
public class Insert {
	Matrix lastInserted;
	
	/**
	 * Default constructor
	 */
	public Insert() {
		super();
	}
	
	/**
	 * Method used for inserting matrices from database
	 */
	public void insertFromDatabase() {
		
	}
	
	/**
	 * For reading characters from a file char by char
	 * @param reader
	 * @throws IOException
	 * @throws WrongInsertException 
	 */
	@SuppressWarnings("unused")
	protected void handleCharacters(Reader reader) throws IOException, WrongInsertException, NullPointerException, NumberFormatException {
        int r;
        int read = 0, i = 0, j = 0, sign = 1;
        int numOfRows = 0, numOfCols = 0;
        ArrayList<ArrayList<Double>> values = new ArrayList<>();
        String s = new String();
        char prev = ' ', curr;
        
        while ((r = reader.read()) != -1) {
        	curr = (char)r;
        	
        	if(read == 0 && curr != ' ') {
        		numOfRows = Character.getNumericValue(curr);
        		read++;
        		prev = curr;
        		continue;
        	}
        	if(read == 1 && curr != ' ') {
        		numOfCols = Character.getNumericValue(curr);
        		read++;
        		prev = curr;
        		continue;
        	}
        	
        	if((curr == ' ' || curr == '\n' || curr == '\t' || r == 13) && Character.isDigit(prev) && read > 2) {
        		if(j % numOfCols == 0) {
        			values.add(new ArrayList<>());
        		}
        		values.get(i).add(sign * Double.parseDouble(s));
        		sign = 1;
    			s = "";
    			j++;
    			if(j == numOfCols) {
    				j = 0;
    				i++;
    			}
				if(i >= numOfRows && j > 0) {
					throw new WrongInsertException("Too many symbols! Expected: " + (numOfRows * numOfCols));
				}
        		prev = curr;
        		continue;
        	} else if(curr == '.' || Character.isDigit(curr)) {
        		read++;
        		s += curr;
        		prev = curr;
        	} else if(curr == '-' && (prev == ' ' || prev == ',')) {
        		sign = -1;
        	} else if(curr == ' ' || curr == '\n' || curr == '\t' || r == 13) { 
        		prev = ' ';
        		continue;
        	} else {
        		// a symbol that is not a number, '.' or ' '
				throw new WrongInsertException("Invalid symbol!");
        	}
            // System.out.println("Do something with " + r);
        }
        if(j != (numOfCols - 1) || i != (numOfRows - 1)) {
			throw new WrongInsertException("Not enough symbols! Expected: " + (numOfRows * numOfCols));
        }
        // for the last value
        values.get(i).add(sign * Double.parseDouble(s));
        
        this.lastInserted = new Matrix(numOfRows, numOfCols);
        System.out.println(numOfRows + " " + numOfCols);
        double[][] arr = new double[numOfRows][numOfCols];
		for(j = 0; j < numOfRows; j++) {
			for(int k = 0; k < numOfCols; k++) {
				arr[j][k] = values.get(j).get(k);
			}
		}
		this.lastInserted.elements = arr; 
    }
	
	/**
	 * Method used for inserting matrices from files
	 * @param filePath string representing path to the file
	 */
	public void insertFromFile(String filePath) throws FileNotFoundException, IOException, WrongInsertException {
		File myFile = new File(filePath);
		InputStream in = new FileInputStream(myFile);
        Reader reader = new InputStreamReader(in, Charset.defaultCharset());
        // buffer for efficiency
        Reader buffer = new BufferedReader(reader);
        handleCharacters(buffer);
	}
	
	/**
	 * Method used for inserting matrices from the text box
	 * @param matrix string representation of a matrix
	 * @throws WrongInsertException if there's an error during the insert
	 */
	public void insertFromTextBox(String matrix) throws WrongInsertException, NullPointerException, NumberFormatException {
		@SuppressWarnings("unused")
		int i = 0, numOfRows = 0, numOfCols = 0, countElements = 0, countParentheses = 0;
		ArrayList<ArrayList<Double>> values = new ArrayList<>();
		matrix = matrix.replaceAll("\\s+",""); // remove all whitespaces
		while(i != matrix.length()) {
			Character curr = matrix.charAt(i);
			if(curr == '{') {
				values.add(new ArrayList<Double>());
				countParentheses++;
			} else if(curr == '}') {
				if(numOfRows == 0) {
					numOfCols = countElements;
				}
				countParentheses--;
				numOfRows++;
				if(i != matrix.length() - 1 && numOfCols != countElements) {
					// not all rows are the same size
					throw new WrongInsertException("All rows need to be the same size!");
				}
				countElements = 0;
			} else if(curr == ',') {
				i++;
				continue;
			} else if(Character.isDigit(curr)) {
				int sign = 1;
				if(matrix.charAt(i - 1) == '-') {
					sign = -1;
				}
				String s = curr.toString();
				i++;
				while(i != matrix.length() && matrix.charAt(i) != ',' && matrix.charAt(i) != '}') {
					s += matrix.charAt(i);
					i++;
				}
				i--;
				values.get(numOfRows).add(sign * Double.parseDouble(s));
				countElements++;
			} else if(curr =='-' || curr == '+') {
				i++;
				continue;
			} else {
				// a symbol that is not a number, ',', '.', '{' or '}'
				throw new WrongInsertException("Invalid symbol!");
			}
			i++;
		}
		if(countParentheses > 0) {
			// wrong number of parentheses
			throw new WrongInsertException("Too many { parentheses!");
		}
		if(countParentheses < 0) {
			// wrong number of parentheses
			throw new WrongInsertException("Too many } parentheses!");
		}
		
		if(numOfRows == 0) {
			this.lastInserted = new Matrix();
			return;
		}
		
		if(numOfRows != 1) {
			numOfRows--;
		}
		double[][] arr = new double[numOfRows][numOfCols];
		for(int j = 0; j < numOfRows; j++) {
			for(int k = 0; k < numOfCols; k++) {
				arr[j][k] = values.get(j).get(k);
			}
		}
		this.lastInserted = new Matrix(numOfRows, numOfCols, arr);
	}
}
