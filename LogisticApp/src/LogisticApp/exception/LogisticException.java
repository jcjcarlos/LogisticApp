package LogisticApp.exception;

public class LogisticException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String message;

	public LogisticException(String message) {
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}
	
}
