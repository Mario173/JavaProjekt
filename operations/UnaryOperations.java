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
		return 0;
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
		return 0;
	}
	
	/**
	 * Calculates the transposed matrix of the given matrix
	 * @return the transposed matrix
	 */
	public Matrix transpose() {
		return new Matrix();
	}
	
	/**
	 * Calculates the inverse of the given matrix
	 * @return the inverse matrix
	 */
	public Matrix inverse() {
		return new Matrix();
	}
}
