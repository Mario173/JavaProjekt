package exceptions;

@SuppressWarnings("serial")
public class MatrixIsSingularException extends Exception {
	public String message;
	
	public MatrixIsSingularException(String mess) {
		this.message = "Cannot calculate " + mess + " on a matrix that is singular";
	}
	
	public String getMessage() {
		return this.message;
	}
}
