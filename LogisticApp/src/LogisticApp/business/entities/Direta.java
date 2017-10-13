package LogisticApp.business.entities;

public class Direta extends Rota {
	
	private Localidade origem;
	private Localidade destino;
	private double capacidadeTotal;
	private double capacidadeAlocada;
	private double custoGrama;
	private int tempoEntrega;

	public Direta(int id, String nome, 
				  Localidade origem, 
				  Localidade destino, 
				  double capacidadeTotal, 
				  double capacidadeAlocada,
				  double custoGrama,
				  int tempoEntrega) {
		super(id, nome);
		this.setOrigem(origem);
		this.setDestino(destino);
		this.setCapacidadeTotal(capacidadeTotal);
		this.setCapacidadeAlocada(capacidadeAlocada);
		this.setCustoGrama(custoGrama);
		this.setTempoEntrega(tempoEntrega);
	}

	@Override
	public double getCapacidadeTransporte() {
		return this.getCapacidadeTotal() - this.getCapacidadeAlocada();
	}

	@Override
	public double getCustoGrama() {
		return this.custoGrama;
	}

	@Override
	public int getTempoEntrega(){
		return this.tempoEntrega;
	}
	
	public void aumentarCapacidadeAlocada(double volume){
		this.setCapacidadeAlocada(this.getCapacidadeAlocada() + volume);
	}

	public Localidade getOrigem() {
		return origem;
	}

	public void setOrigem(Localidade origem) {
		this.origem = origem;
	}

	public Localidade getDestino() {
		return destino;
	}

	public void setDestino(Localidade destino) {
		this.destino = destino;
	}

	public double getCapacidadeTotal() {
		return capacidadeTotal;
	}

	public void setCapacidadeTotal(double capacidadeTotal) {
		this.capacidadeTotal = capacidadeTotal;
	}
	
	public double getCapacidadeAlocada(){
		return this.capacidadeAlocada;
	}
	
	public void setCapacidadeAlocada(double capacidadeAlocada){
		this.capacidadeAlocada = capacidadeAlocada;
	}

	public void setCustoGrama(double custoGrama) {
		this.custoGrama = custoGrama;
	}

	public void setTempoEntrega(int tempoEntrega) {
		this.tempoEntrega = tempoEntrega;
	}

}
