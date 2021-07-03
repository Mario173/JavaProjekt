package operations;

import java.util.ArrayList;

import matrix.Matrix;

/**
 * Class used for calculating operations with matrices that aren't unary nor binary
 * @author Mario
 *
 */
public class OtherOperations {
	protected Matrix matrix;
	protected double[] polynomial;
	
	/**
	 * Constructor for finding value p(A) of polynomial p(x) given a matrix A 
	 * @param A represents the given matrix
	 * @param p represents the given polynomial
	 */
	public OtherOperations(Matrix A, double[] p) {
		this.matrix = A;
		this.polynomial = p;
	}
	
	/**
	 * Method used for finding the value of the given polynomial in a given matrix
	 * @return the resulting matrix 
	 * @throws SquareMatrixException if the matrix is not square
	 */
	public Matrix calculatePolynomialValue() throws SquareMatrixException {
		// polynomial
		if(this.matrix.numOfCols != this.matrix.numOfRows) {
			throw new SquareMatrixException("calculate polynomial");
		}
		double[][] zero = {};
		Matrix result = new Matrix(this.matrix.numOfCols, this.matrix.numOfRows, zero);
		for(int i = 0; i < this.polynomial.length; i++) {
			MatrixAndScalarOperations pot = new MatrixAndScalarOperations(this.matrix, i);
			pot.matrix = pot.potentiate();
			pot.scalar = this.polynomial[i];
			BinaryOperationsTwoMatrices add = new BinaryOperationsTwoMatrices(result, pot.scalar());
			result = add.add();
		}
		return result;
	}
	
	/**
	 * Method used for solving linear system of equations
	 * @return the matrix containing the solution
	 */
	public Matrix solveLinearSystem() {
		int n = matrix.numOfRows;
		double[][] solutions = new double[n][1];
		UnaryOperations Unar = new UnaryOperations(matrix);
		ArrayList<Matrix> LU = Unar.LUDecomposition();
		Matrix L = LU.get(1), U = LU.get(2);
		// rjesavamo U = b
		for( int i = n-1; i >=0; i--)
		{
			double k = U.elements[i][i], sum = 0.0;
			for( int j = n-1; j > i; j-- )
				sum += solutions[j][1]*U.elements[i][j];
			solutions[i][0] = ((double) this.polynomial[i] - sum)/k;
		}
		// sad idemo rijesiti L = y. sad je super sto su na dijagonali sve 1
		for( int i = 0; i < n; i++ )
		{
			double sum = 0.0;
			for( int j = 0; j < i; j++ )
				sum += solutions[j][1] * L.elements[i][j];
			solutions[i][0] = sum;
		}
		// nadam se da nisam zamjenio br redaka i stupaca dolje
		return new Matrix(1, n, solutions);
	}

}
