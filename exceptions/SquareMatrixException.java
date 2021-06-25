package exceptions;

@SuppressWarnings("serial")
public class SquareMatrixException extends Exception {
	public String message;
	
	public SquareMatrixException(String mess) {
		this.message = "Cannot calculate " + mess + " on a matrix that is not square";
		this.message += "(has equal number of rows and columns)!";
	}
}
