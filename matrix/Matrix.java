package matrix;

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
	 * method for swapping values in rows x and y in matrix
	 * @param x index of first row that we want to swap
	 * @param y index of second row that we want to swap with the first one
	 * */
	public void swapRows( int x, int y )
	{
		if( x < 0 || y < 0 || x < this.numOfRows || y < this.numOfRows )
		{
			//izbaci gresku
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
	 * */
	public void addRowToRow( int m, int n, double alpha, int pocetak )
	{
		// standardna provjera granica - redovi moraju biti pozitivni i ne smije biti veci od postojeceg br reda
		if( m < 0 || n < 0 || m < this.numOfRows || n < this.numOfRows )
		{
			//izbaci gresku
		}
		for( int i = pocetak; i < this.numOfCols; i++ )
		{
			this.elements[n][i] += this.elements[m][i] * alpha;
		}
	}
}
