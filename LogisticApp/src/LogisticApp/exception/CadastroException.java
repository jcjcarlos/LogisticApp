package LogisticApp.exception;

public class CadastroException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;

	public CadastroException(String message) {
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}
	
	public String toString(){
		return "CadastroException : [ " + this.message + " ]";
	}

}
