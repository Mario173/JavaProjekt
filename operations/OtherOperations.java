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
	public double[] solveLinearSystem() {
		int n = matrix.numOfRows;
		//double[][] solutions = new double[n][1];
		UnaryOperations Unar = new UnaryOperations(matrix);
		ArrayList<Matrix> LU;
		double [] Usolution = new double[n];
		double [] Lsolution = new double[n];
		double k, sum;
		double[] perm_vector = new double[n];
		try { 
				//dodano
			LU = Unar.LUDecomposition();
			Matrix pivot = LU.get(0);
			Matrix L = LU.get(1), U = LU.get(2);
			// rjesavamo U = b
			
			for(int i = 0; i < n; i++) {
				perm_vector[i] = vector[(int) pivot.elements[i][0]];
			}
			
			
			
			for( int i = 0; i < n; i++ )
			{
				k = L.elements[i][i];
				sum = 0.0;
				for( int j = 0; j < i; j++ ) {
					sum += Lsolution[j] * L.elements[i][j];
				}
				Lsolution[i] = (perm_vector[i] - sum) / k;
			}
			
			for( int i = n-1; i >=0; i--)
			{
				k = U.elements[i][i]; 
				sum = 0.0;
				for( int j = n-1; j > i; j-- )
					sum += Usolution[j] * U.elements[i][j];
				Usolution[i] = (Lsolution[i] - sum)/k;
			}
			// sad idemo rijesiti L = y. sad je super sto su na dijagonali sve 1
			
			return Usolution;
			// nadam se da nisam zamjenio br redaka i stupaca dolje
		} catch (MatrixDimensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new double[n];
		
		
	}

}
