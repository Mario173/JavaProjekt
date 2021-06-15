package operations;

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
		for(int i = 0; i < this.matrix.numOfRows; i++) {
			for(int j = 0; j < this.matrix.numOfCols; j++) {
				this.matrix.elements[i][j] *= this.scalar;
			}
		}
		return this.matrix;
	}
	
	/**
	 * Potentiates the matrix to a given potention
	 * @return the resulting matrix
	 */
	public Matrix potentiate() {
		// potentiate the matrix on the given potention
		return new Matrix();
	}

}
