package operations;

import java.util.ArrayList;

import matrix.Matrix;

/**
 * Class representing operations where the only operand is a matrix
 * @author Mario
 *
 */
public class UnaryOperations {
	protected Matrix matrix;
	
	/**
	 * Constructs an object used for calculations
	 * @param a represents a matrix used in calculations
	 */
	public UnaryOperations(Matrix a) {
		this.matrix = a;
	}
	
	/**
	 * Calculates the LU Decomposition of the given matrix
	 * @return matrices L and U forming an LU Decomposition
	 */
	public ArrayList<Matrix> LUDecomposition() {
		return new ArrayList<Matrix>();
	}
	
	/**
	 * Calculates the trace of the given matrix
	 * @return the trace of the matrix
	 */
	public double trace() {
		if(this.matrix.numOfCols != this.matrix.numOfRows) {
			// baci neki exception
		}
		double trace = 0;
		for(int i = 0; i < this.matrix.numOfCols; i++) {
			trace += this.matrix.elements[i][i];
		}
		return trace;
	}
	
	/**
	 * Calculates the rank of the given matrix
	 * @return the rank of the matrix
	 */
	public int rank() {
		return 0;
	}
	
	/**
	 * Calculates the determinant of the given matrix
	 * @return the determinant of the given matrix
	 */
	public double determinant() {
		if(this.matrix.numOfCols != this.matrix.numOfRows) {
			// baci neki exception
		}
		return 0;
	}
	
	/**
	 * Calculates the transposed matrix of the given matrix
	 * @return the transposed matrix
	 */
	public Matrix transpose() {
		for(int i = 0; i < this.matrix.numOfRows; i++) {
			for(int j = 0; j < this.matrix.numOfCols; j++) {
				double temp = this.matrix.elements[i][j];
				this.matrix.elements[i][j] = this.matrix.elements[j][i];
				this.matrix.elements[j][i] = temp;
			}
		}
		return this.matrix;
	}
	
	/**
	 * Calculates the inverse of the given matrix
	 * @return the inverse matrix
	 */
	public Matrix inverse() {
		return new Matrix();
	}
}
