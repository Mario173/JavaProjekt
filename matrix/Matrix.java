package matrix;

import exceptions.MatrixDimensionException;

/**
 * Class representing a matrix
 * @author Mario
 *
 */
public class Matrix {
	public int numOfRows, numOfCols;
	public double elements[][];
	
	public Matrix() {
		// to je samo da zasad ne javlja gresku, nece nan tribat
	}
	
	/**
	 * Constuctor for matrices used in operations
	 * @param col representing the number of columns of the matrix
	 * @param row rperesenting the number of rows of the matrix
	 * @param elem represents the elements of the matrix
	 */
	public Matrix(int col, int row, double[][] elem) {
		this.numOfCols = col;
		this.numOfRows = row;
		this.elements = elem;
	}
	
	/**
	 * Constructor when the matrix is inserted from database
	 * @param col number of columns
	 * @param row number of rows
	 * @param elem string containing all elements
	 */
	public Matrix(int col, int row, String elem) {
		//potrebno je implementirat
		this.numOfCols = col;
		this.numOfRows = row;
		double[][] elements = new double[row][col];
		String[] elems = elem.split(";");
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				try {
					elements[i][j] = Double.parseDouble(elems[i * col + j]);
				} catch(IndexOutOfBoundsException e) {
					e.printStackTrace();
					return; // mozda jos nesto jer je greska
				}
			}
		}
	}
	
	/**
	 * method for swapping values in rows x and y in matrix
	 * @param x index of first row that we want to swap
	 * @param y index of second row that we want to swap with the first one
	 * @throws MatrixDimensionException 
	 * */
	public void swapRows( int x, int y ) throws MatrixDimensionException
	{
		if( x < 0 || y < 0 || x < this.numOfRows || y < this.numOfRows )
		{
			throw new MatrixDimensionException("swapping rows (wrong row index)");
		}
		for( int i = 0; i < this.numOfCols; i++ )
		{
			double tmp = this.elements[x][i];
			this.elements[x][i] = this.elements[y][i];
			this.elements[y][i] = tmp;
		}
	}
	
	/**
	 * method that multiplies row m with coefficient alpha and adds values of that row to row n
	 * @param m index of row that we multiply by alpha and add to another row
	 * @param n index of row that we change by adding another row to it
	 * @param alpha coefficient by which we multiply row m 
	 * @param pocetak defaultna vrijednost je 0 i kaze od kojeg se stupca krene s dodavanjem
	 * @throws MatrixDimensionException 
	 * */
	public void addRowToRow( int m, int n, double alpha, int pocetak ) throws MatrixDimensionException
	{
		// standardna provjera granica - redovi moraju biti pozitivni i ne smije biti veci od postojeceg br reda
		if( m < 0 || n < 0 || m < this.numOfRows || n < this.numOfRows )
		{
			throw new MatrixDimensionException("adding row to row (wrong row index)");
		}
		for( int i = pocetak; i < this.numOfCols; i++ )
		{
			this.elements[n][i] += this.elements[m][i] * alpha;
		}
	}
}
