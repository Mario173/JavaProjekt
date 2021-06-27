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
	// posalje se u spremnik di cemo cuvat matrice
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
	protected void handleCharacters(Reader reader) throws IOException, WrongInsertException {
        int r;
        int read = 0, i = 0, j = 0;
        int numOfRows = 0, numOfCols = 0;
        ArrayList<ArrayList<Double>> values = new ArrayList<>();
        String s = new String();
        
        while ((r = reader.read()) != -1) {
        	char curr = (char)r;
        	
        	if(read == 0 && curr != ' ') {
        		numOfRows = r;
        		read++;
        	}
        	if(read == 1 && curr != ' ') {
        		numOfCols = r;
        		read++;
        	}
        	
        	if(curr == ' ') {
        		try {
        			values.get(i).add(Double.parseDouble(s));
        			s = "";
        			j++;
        			if(j == numOfCols) {
        				j = 0;
        				i++;
        			}
    				if(i >= numOfRows && j > 0) {
    					throw new WrongInsertException("Too many symbols! Expected: " + (numOfRows * numOfCols));
    				}
        		} catch(NullPointerException e) {
        			e.printStackTrace();
        			return;
        		} catch(NumberFormatException f) {
        			f.printStackTrace();
        			return;
        		}
        		continue;
        	} else if(curr == '.' || Character.isDigit(curr)) {
        		s += curr;
        	} else {
        		// a symbol that is not a number, '.' or ' '
				throw new WrongInsertException("Invalid symbol!");
        	}
            System.out.println("Do something with " + r);
        }
        if(j != 0 || i != numOfRows) {
			throw new WrongInsertException("Not enough symbols! Expected: " + (numOfRows * numOfCols));
        }
        
        this.lastInserted.numOfRows = numOfRows;
        this.lastInserted.numOfCols = numOfCols;
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
	public void insertFromFile(String filePath) {
		try {
			File myFile = new File(filePath);
			InputStream in = new FileInputStream(myFile);
            Reader reader = new InputStreamReader(in, Charset.defaultCharset());
            // buffer for efficiency
            Reader buffer = new BufferedReader(reader);
            handleCharacters(buffer);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			return;
		} catch(IOException f) {
			f.printStackTrace();
			return;
		} catch(WrongInsertException w) {
			System.out.println(w.message);
			return;
		}
	}
	
	/**
	 * Method used for inserting matrices from the text box
	 * @param matrix string representation of a matrix
	 * @throws WrongInsertException if there's an error during the insert
	 */
	public void insertFromTextBox(String matrix) throws WrongInsertException {
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
				String s = curr.toString();
				i++;
				while(i != matrix.length() && matrix.charAt(i) != ',' && matrix.charAt(i) != '}') {
					s += matrix.charAt(i);
					i++;
				}
				i--;
				try {
					values.get(numOfRows).add(Double.parseDouble(s));
					countElements++;
				} catch(NullPointerException e) {
					e.printStackTrace();
					return;
				} catch(NumberFormatException f) {
					f.printStackTrace();
					return;
				}
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
		if(numOfRows != 1) {
			numOfRows--;
		}
		double[][] arr = new double[numOfRows][numOfCols];
		for(int j = 0; j < numOfRows; j++) {
			for(int k = 0; k < numOfCols; k++) {
				arr[j][k] = values.get(j).get(k);
			}
		}
		this.lastInserted = new Matrix(numOfCols, numOfRows, arr);
	}
}
