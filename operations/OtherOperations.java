package operations;

import java.util.ArrayList;

import matrix.Matrix;

import exceptions.*;

/**
 * Class used for calculating operations with matrices that aren't unary nor binary
 * @author Mario
 *
 */
public class OtherOperations {
	protected Matrix matrix;
	protected double[] vector;
	
	/**
	 * Constructor for finding value p(A) of polynomial p(x) given a matrix A 
	 * @param A represents the given matrix
	 * @param p represents the given polynomial
	 */
	public OtherOperations(Matrix A, double[] p) {
		this.matrix = A;
		this.vector = p;
	}
	
	/**
	 * Method used for finding the value of the given polynomial in a given matrix
	 * @return the resulting matrix 
	 * @throws SquareMatrixException if the matrix is not square
	 * @throws MatrixDimensionException 
	 * @throws ImpermissibleExponentException 
	 */
	public Matrix calculatePolynomialValue() throws SquareMatrixException, MatrixDimensionException, ImpermissibleExponentException {
		// polynomial
		if(this.matrix.numOfCols != this.matrix.numOfRows) {
			throw new MatrixDimensionException("polynomial");
		}
		double[][] zero = new double[this.matrix.numOfRows][this.matrix.numOfCols];
		Matrix result = new Matrix(this.matrix.numOfRows, this.matrix.numOfCols, zero);
		
		for(int j = 0; j < this.vector.length; j++) {
			//System.out.println(this.vector[j]);
		}
		
		for(int i = this.vector.length - 1; i >= 0; i--) {
			if(vector[i] == 0) {
				continue;
			}
			Matrix help = new Matrix(this.matrix);
			MatrixAndScalarOperations pot = new MatrixAndScalarOperations(help, i);
			pot.matrix = pot.potentiate();
			pot.scalar = this.vector[i];
			//System.out.println(result.numOfRows + " " + pot.scalar().numOfRows);
			BinaryOperationsTwoMatrices add = new BinaryOperationsTwoMatrices(result, pot.scalar());
			result = add.add();
		}
		return result;
	}
	
	/**
	 * Method used for solving linear system of equations
	 * @return the matrix containing the solution
	 * @throws MatrixDimensionException 
	 * @throws InterruptedException 
	 * @throws SquareMatrixException 
	 * @throws MatrixIsSingularException 
	 */
	public double[] solveLinearSystem() throws MatrixDimensionException, InterruptedException, SquareMatrixException, MatrixIsSingularException {
		int n = matrix.numOfRows;
		//double[][] solutions = new double[n][1];
		UnaryOperations Unar = new UnaryOperations(matrix);
		ArrayList<Matrix> LU;
		double [] Usolution = new double[n];
		double [] Lsolution = new double[n];
		double k, sum;
		double[] perm_vector = new double[n];
		
				//dodano
		LU = Unar.LUDecomposition();
		Matrix pivot = LU.get(0);
		Matrix L = LU.get(1), U = LU.get(2);
		// rjesavamo U = b
		
		double determinant = new UnaryOperations(U).determinant();
		
		if( determinant >= -0.001 && determinant < 0.001) {
			throw new MatrixIsSingularException("linear system");
		}
		
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
		
	}

}
