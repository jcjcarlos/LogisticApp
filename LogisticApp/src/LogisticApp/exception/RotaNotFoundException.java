package LogisticApp.exception;

public class RotaNotFoundException extends Exception {

	private String nome;
	
	public RotaNotFoundException(Exception e){
		super(e);
	}
	
	public RotaNotFoundException(String nome){
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public String toString(){
		return "RotaNotFoundException : [Nome da rota: " + this.getNome() + "]";
	}
	
}
