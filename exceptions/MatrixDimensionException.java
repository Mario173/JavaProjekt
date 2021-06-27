package exceptions;

@SuppressWarnings("serial")
public class MatrixDimensionException extends Exception {
	public String message;
	
	public MatrixDimensionException(String mess) {
		this.message = "Cannot calculate " + mess + " with given matrices";
		if(mess.equalsIgnoreCase("multiplication")) {
			this.message += "(number of columns of the first matrix need to be equal to the number of rows of the second)!";
		} else {
			this.message += "(the matrix dimensions are wrong)!";
		}
	}
}
