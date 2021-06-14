package matrix;

/**
 * Class representing a matrix
 * @author Mario
 *
 */
public class Matrix {
	protected int numOfRows, numOfCols;
	protected double elements[][];
	
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
}
