package exceptions;

@SuppressWarnings("serial")
public class ImpermissibleExponentException extends Throwable {
	public String message;
	
	public ImpermissibleExponentException() {
		this.message = "Cannot calculate this operation with the given scalar!\n "
				+ "The scalar needs to be between 0 and 15!";
	}
	
	public String getMessage() {
		return this.message;
	}
}