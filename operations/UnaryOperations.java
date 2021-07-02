package operations;

import java.util.ArrayList;

import exceptions.MatrixDimensionException;
import exceptions.SquareMatrixException;
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
	 * @throws MatrixDimensionException 
	 */
	public ArrayList<Matrix> LUDecomposition() throws MatrixDimensionException {
		int m = this.matrix.numOfRows;
		// stvaranje pivotne matrice, ima m redaka, 1 stupac
		double[][] piv_elem = new double[m][1];
		double[][] L_elem = new double[m][m];
		int piv, piv_sign = 1;
		for( int i = 0; i < m; i++)
		{
			piv_elem[i][1]= i;
			L_elem[i][i] = 1;
		}
		Matrix L = new Matrix( m, m, L_elem );
		Matrix pivot = new Matrix( 1, m, piv_elem );
		for( int i = 0; i < m; i++)
		{
			double max = this.matrix.elements[i][i];
			piv = i;
			//u svakom retku pronadi najveci vrijednost na u i-tom stupcu
			for( int j = i; j < m; j++ )
			{
				if( java.lang.Math.abs( this.matrix.elements[j][i] ) < max )
				{
					piv = j; // postavi da je u ce se j-ti redak trebati pivotirati
					max = java.lang.Math.abs( this.matrix.elements[j][i] );
				}
			}
			if( piv != i )
			{
				// trebamo pivotirati retke piv i i
				this.matrix.swapRows(i, piv);
				int tmp = (int) pivot.elements[i][1];
				pivot.elements[i][1] = piv;
				pivot.elements[piv][1] = tmp;
			}
			// sada radimo gaussovu eliminaciju + upisujemo koef ispod tj vrijednosti L matrice
			// j ide po retcima
			for( int j = i; j < m; j++ )
			{
				 //odredi koeficijent s kojim eliminiras i njega zapisi u L
				double alfa = this.matrix.elements[j][i] / this.matrix.elements[i][i];
				this.matrix.addRowToRow(i, j, -alfa, i);
				//L.elements[j][i] = -alfa;
				this.matrix.elements[j][i] = -alfa;
			}
		}
		// upisivanje vrijednosti u L matricu i brisanje iz matrix
		for( int j = 0; j < m; j++)
		{
			for( int i = j+1; i < m; i++)
			{
				L.elements[i][j] = matrix.elements[i][j];
				matrix.elements[i][j] = 0.0;
			}
		}
		ArrayList<Matrix> result = new ArrayList<Matrix>();
		// vracamo P * L * matrix
		result.add(pivot);  result.add(L); result.add(matrix);
		return result;
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
		// algoritam koji koristima o i pri rucnom racunanju, osim sto ne radim zaprave ponistavanja
		// u retku, ali ponasam se kao da jesam
		int min = Math.min(this.matrix.numOfCols, this.matrix.numOfRows), rank;
		rank = min;
		for( int i = 0; i < min; i++)
		{
			// provjera da nam nije 0 na dijagonali
			if( this.matrix.elements[i][i] == 0 )
			{
				for( int k = i+1; k < this.matrix.numOfRows; k++)
					if( this.matrix.elements[k][i] != 0 )
						{
							this.matrix.swapRows(i, k);
							break;
						}
				// u stupcu su sve same 0, smanjiti rank za 1 i prijedi na slijedeci redak i+1
				rank--;
				continue;
			}
			// sad idemo ponistiti brojeve ispod
			for( int j = i+1; j < this.matrix.numOfRows; j++ )
			{
				double k = this.matrix.elements[j][i] / this.matrix.elements[i][i];
				this.matrix.addRowToRow(i, j, k, i);
			}
		}
		return rank;
	}
	
	/**
	 * Calculates the determinant of the given matrix
	 * @return the determinant of the given matrix
	 * @throws SquareMatrixException 
	 */
	public double determinant() throws SquareMatrixException {
		if(this.matrix.numOfCols != this.matrix.numOfRows) {
			throw new SquareMatrixException("determinant");
		}
		return determinantOfMatrix(this.matrix.elements, this.matrix.numOfCols);
	}
	
	private void getCofactor(double mat[][], double temp[][], int p, int q, int n) {
		int i = 0, j = 0;
		
		// Looping for each element of the matrix
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				// Copying into temporary matrix only those element which are not in given row and column
				if (row != p && col != q) {
					temp[i][j++] = mat[row][col];
					// Row is filled, so increase row index and reset col index
					if (j == n - 1) {
						j = 0;
						i++;
					}
				}
			}
		}
	}

	/* Recursive function for finding determinant
	of matrix. n is current dimension of mat[][]. */
	/**
	 * Recursive function for finding determinant of matrix
	 * @param mat array containing elements of the current submatrix
	 * @param n current size of the matrix
	 * @return
	 */
	private double determinantOfMatrix(double mat[][], int n) {
		double D = 0; // Initialize result
		
		// Base case : if matrix contains single element
		if (n == 1) {
			return mat[0][0];
		}
		
		// Another base case to spped up the execution
		if (n == 2) {
			return mat[0][0] * mat[1][1] - mat[0][1] * mat[1][0];
		}
		
		// To store cofactors
		double temp[][] = new double[this.matrix.numOfRows][this.matrix.numOfCols];
		
		// To store sign multiplier
		int sign = 1;
		
		// Iterate for each element of first row
		for (int f = 0; f < n; f++) {
			// Getting Cofactor of mat[0][f]
			getCofactor(mat, temp, 0, f, n);
			D += sign * mat[0][f] * determinantOfMatrix(temp, n - 1);
			
			// terms are to be added with
			// alternate sign
			sign = -sign;
		}
		
		return D;
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
		// provjeri je li kvadratna, a nakon toga ima li U iz dekomp dijagonalu bez 0
		if( matrix.numOfCols != matrix.numOfRows );
			throw new SquareMatrixException("Nije kvadratna");
		int n= matrix.numOfCols, i, j, k ;
		
		
		// ajmo probat naivni gaussov nacin - u biti on se cini dosta zgodan za visedretvenost?
		double[][] elem_inv = new double[n][n];
		for( i = 0; i < n; i++ ) elem_inv[i][i] = 1;    // 1 na dijagonalu
		Matrix inverseL = new Matrix( n, n, elem_inv );
		for( i = 0; i < n-1; i++)
			for( j = i+1; j < n; j++)
			{
				double c = - matrix.elements[j][i] / matrix.elements[i][i];
				matrix.addRowToRow(i, j, c, 0);
				inverseL.addRowToRow(i, j, c, 0);
			}
		for( i = n-1; i >= 0; i-- )
			for( j = i-1; j>= 0; j--)
			{
				double c = - matrix.elements[j][i] / matrix.elements[i][i];
				matrix.addRowToRow(i, j, c, 0);
				inverseL.addRowToRow(i, j, c, 0);
			}
		for( i = 0; i < n; i++ )
		{
			inverseL.scaleRow(i, 1/matrix.elements[i][i] );
		}
		return inverseL;
	}
}
