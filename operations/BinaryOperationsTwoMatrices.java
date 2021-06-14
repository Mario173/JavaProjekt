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
		// check are they equal
		return true;
	}
	
	/**
	 * Adds given matrices.
	 * @return the sum of given matrices
	 */
	public Matrix add() {
		// add the matrices
		return new Matrix();
	}
	
	/**
	 * Subtracts given matrices.
	 * @return the difference of the given matrices
	 */
	public Matrix subtract() {
		// subtract the matrices
		return new Matrix();
	}
	
	/**
	 * Multiplies given matrices.
	 * @return the product of the given matrices
	 */
	public Matrix multiply() {
		// multiply the matrices
		return new Matrix();
	}
}
