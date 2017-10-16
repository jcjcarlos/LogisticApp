package LogisticApp.exception;

public class RotaNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;
	
	public RotaNotFoundException(Exception e){
		super(e);
	}
	
	public RotaNotFoundException(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String toString(){
		return "RotaNotFoundException : [ " + this.message + " ] ";
	}
	
}
