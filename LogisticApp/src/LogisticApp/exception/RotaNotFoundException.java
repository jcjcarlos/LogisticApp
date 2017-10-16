package LogisticApp.exception;

public class RotaNotFoundException extends Exception {

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
