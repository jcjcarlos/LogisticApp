package LogisticApp.business.entities;

public abstract class Rota {
	
	protected int id;
	protected String nome;
	
	public Rota(int id, String nome){
		this.setId(id);
		this.setNome(nome);
	}
	
	private void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public int getId(){
		return this.id;
	}
	
	public abstract Localidade getOrigem();
	
	public abstract Localidade getDestino();
	
	public abstract double getCapacidadeTransporte();
	
	public abstract double getCustoGrama();
	
	public abstract int getTempoEntrega();
	
	public abstract void aumentarCapacidadeAlocada(double volume);
	
}
