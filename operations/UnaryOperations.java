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
