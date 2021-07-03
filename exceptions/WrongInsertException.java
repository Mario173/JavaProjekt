package exceptions;

@SuppressWarnings("serial")
public class WrongInsertException extends Exception {
	public String message;
	
	public WrongInsertException(String mess) {
		message = "Error while inserting the matrix: " + mess;
	}
	
	public String getMessage() {
		return this.message;
	}
}
