package operations;

import matrix.Matrix;

/**
 * Class containing implementations for all binary operations where both operands are matrices.
 * @author Mario
 *
 */
public class BinaryOperationsTwoMatrices {
	Matrix first, second;
	
	/**
	 * Constructs an object used for calculations
	 * @param a represents the first matrix in the operation
	 * @param b represents the second matrix in the operation
	 */
	public BinaryOperationsTwoMatrices(Matrix a, Matrix b) {
		this.first = a;
		this.second = b;
	}
	
	/**
	 * Checks if the given matrices are equal.
	 * @return true if they are equal, false otherwise
	 */
	public boolean isEqual() {
		if(this.first.numOfRows != this.second.numOfRows || this.first.numOfCols != this.second.numOfCols) {
			return false;
		}
		for(int i = 0; i < this.first.numOfRows; i++) {
			for(int j = 0; j < this.second.numOfCols; j++) {
				if(this.first.elements[i][j] != this.second.elements[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Adds given matrices.
	 * @return the sum of given matrices
	 */
	public Matrix add() {
		if(this.first.numOfRows != this.second.numOfRows || this.first.numOfCols != this.second.numOfCols) {
			// baci neki exception -> poslije
		}
		Matrix result = new Matrix(this.first.numOfCols, this.first.numOfRows, new double[this.first.numOfRows][this.first.numOfCols]);
		for(int i = 0; i < this.first.numOfRows; i++) {
			for(int j = 0; j < this.first.numOfCols; j++) {
				result.elements[i][j] = this.first.elements[i][j] + this.second.elements[i][j];
			}
		}
		return result;
	}
	
	/**
	 * Subtracts given matrices.
	 * @return the difference of the given matrices
	 */
	public Matrix subtract() {
		if(this.first.numOfRows != this.second.numOfRows || this.first.numOfCols != this.second.numOfCols) {
			// baci neki exception -> poslije
		}
		Matrix result = new Matrix(this.first.numOfCols, this.first.numOfRows, new double[this.first.numOfRows][this.first.numOfCols]);
		for(int i = 0; i < this.first.numOfRows; i++) {
			for(int j = 0; j < this.first.numOfCols; j++) {
				result.elements[i][j] = this.first.elements[i][j] - this.second.elements[i][j];
			}
		}
		return result;
	}
	
	/**
	 * Multiplies given matrices.
	 * @return the product of the given matrices
	 * uses standard linear algorithm 
	 */
	public Matrix multiply() {
		// multiply the matrices
		// if matrices aren't chained throw an exception -> number of colums of first equal to num of rows of second matrix
		if( this.first.numOfCols != this.second.numOfRows )
		{
			//izbaci neki exception jer nisu amtrice ulancane
		}
		Matrix result = new  Matrix(this.first.numOfRows, this.second.numOfCols, new double[this.first.numOfRows][this.second.numOfCols]);
		for( int i = 0; i < result.numOfRows; i++ )
		{
			for( int j = 0; j < result.numOfCols; j++ )
			{
				double sum = 0.0;
				for( int k = 0; k < this.first.numOfCols; k++ )
				{
					sum += this.first.elements[i][k] * this.second.elements[k][j];
				}
				result.elements[i][j] = sum;
			}
		}
		return result;
	}
}
