package operations;

import java.util.ArrayList;

import exceptions.ImpermissibleExponentException;
import exceptions.MatrixDimensionException;
import exceptions.SquareMatrixException;
import matrix.Matrix;

/**
 * Class representing operations where one operand is a matrix, and other is scalar
 * @author Mario
 *
 */
public class MatrixAndScalarOperations {
	protected double scalar;
	protected Matrix matrix;
	
	/**
	 * Constructs an object used for calculations
	 * @param a represents a matrix used in calculations
	 * @param b represents a scalar used in calculations
	 */
	public MatrixAndScalarOperations(Matrix a, double b) {
		this.matrix = a;
		this.scalar = b;
	}
	
	/**
	 * Multiplies the matrix with the given scalar
	 * @return the resulting matrix
	 */
	public Matrix scalar() {
		Matrix res = new Matrix(this.matrix.numOfRows, this.matrix.numOfCols);
		for(int i = 0; i < this.matrix.numOfRows; i++) {
			for(int j = 0; j < this.matrix.numOfCols; j++) {
				res.elements[i][j] = this.matrix.elements[i][j] * this.scalar;
			}
		}
		return res;
	}
	
	/**
	 * Potentiates the matrix to a given potention (integer between 1 and 15)
	 * @return the resulting matrix
	 * @throws SquareMatrixException if the given matrix is not square
	 * @throws MatrixDimensionException 
	 * @throws ImpermissibleExponentException 
	 */
	public Matrix potentiate() throws SquareMatrixException, MatrixDimensionException, ImpermissibleExponentException {
		// potentiate the matrix on the given potention
		if(this.matrix.numOfCols != this.matrix.numOfRows) {
			throw new SquareMatrixException("the power of the matrix");
		}
		ArrayList<Integer> howToExp = new ArrayList<>();
		int exp = (int) this.scalar;
		
		if(exp < 0 || exp > 15) {
			throw new ImpermissibleExponentException();
		}
		
		if(exp == 0) {
			double[][] temp = new double[this.matrix.numOfRows][this.matrix.numOfCols];
			for(int i = 0; i < this.matrix.numOfRows; i++) {
				for(int j = 0; j < this.matrix.numOfCols; j++) {
					if(i == j) {
						temp[i][j] = 1;
					} else {
						temp[i][j] = 0;
					}
				}
			}
			return new Matrix(this.matrix.numOfRows, this.matrix.numOfCols, temp);
		}
		while(exp != 0) {
			howToExp.add(exp % 2);
			exp /= 2;
		}
		Matrix helper = new Matrix(this.matrix.numOfRows, this.matrix.numOfCols, this.matrix.elements);
		BinaryOperationsTwoMatrices multiply = new BinaryOperationsTwoMatrices(helper, helper);
		for(int i = howToExp.size() -  2; i >= 0; i--) {
			helper = multiply.multiply();
			multiply.first = helper;
			if(howToExp.get(i) == 1) {
				multiply.second = this.matrix;
				helper = multiply.multiply();
				multiply.first = helper;
			}
			multiply.second = helper;
		}
		return helper;
	}

}
