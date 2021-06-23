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
	 * 
	 * */
	public void switchRows( int x, int y )
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
}
