package operations;

import matrix.Matrix;

/**
 * Class used for calculating operations with matrices that aren't unary nor binary
 * @author Mario
 *
 */
public class OtherOperations {
	protected Matrix matrix;
	protected Matrix column;
	protected int[] polynomial;
	
	/**
	 * Constructor for solving linear systems
	 * @param first matrix filled with coefficients from variables
	 * @param second one column matrix filled with coefficients without variables
	 */
	public OtherOperations(Matrix first, Matrix second) {
		this.matrix = first;
		this.column = second;
	}
	
	/**
	 * Constructor for finding value p(A) of polynomial p(x) given a matrix A 
	 * @param A represents the given matrix
	 * @param p represents the given polynomial
	 */
	public OtherOperations(Matrix A, int[] p) {
		this.matrix = A;
		this.polynomial = p;
	}
	
	/**
	 * Method used for finding the value of the given polynomial in a given matrix
	 * @return the resulting matrix 
	 */
	public Matrix calculatePolynomialValue() {
		// polynomial
		return new Matrix();
	}
	
	/**
	 * Method used for solving linear system of equations
	 * @return the matrix containing the solution
	 */
	public Matrix solveLinearSystem() {
		return new Matrix();
	}

}
