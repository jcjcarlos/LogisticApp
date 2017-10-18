package LogisticApp.business.entities;

public class Direta extends Rota {

	private double capacidadeAlocada;
	private double capacidadeTotal;
	private double custoGrama;
	private Localidade destino;
	private Localidade origem;
	private int tempoEntrega;
	
	public Direta(String nome, Localidade origem, Localidade destino, double capacidadeTotal,
			double capacidadeAlocada, double custoGrama, int tempoEntrega){
		this(0, nome, origem, destino, capacidadeTotal, capacidadeAlocada, custoGrama, tempoEntrega);
	}

	public Direta(int id, String nome, Localidade origem, Localidade destino, double capacidadeTotal,
			double capacidadeAlocada, double custoGrama, int tempoEntrega) {
		super(id, nome);
		this.setOrigem(origem);
		this.setDestino(destino);
		this.setCapacidadeTotal(capacidadeTotal);
		this.setCapacidadeAlocada(capacidadeAlocada);
		this.setCustoGrama(custoGrama);
		this.setTempoEntrega(tempoEntrega);
	}

	public void aumentarCapacidadeAlocada(double volume) {
		this.setCapacidadeAlocada(this.getCapacidadeAlocada() + volume);
	}

	public double getCapacidadeAlocada() {
		return this.capacidadeAlocada;
	}

	public double getCapacidadeTotal() {
		return capacidadeTotal;
	}

	@Override
	public double getCapacidadeTransporte() {
		return this.getCapacidadeTotal() - this.getCapacidadeAlocada();
	}

	@Override
	public double getCustoGrama() {
		return this.custoGrama;
	}

	public Localidade getDestino() {
		return destino;
	}

	public Localidade getOrigem() {
		return origem;
	}

	@Override
	public int getTempoEntrega() {
		return this.tempoEntrega;
	}

	public void setCapacidadeAlocada(double capacidadeAlocada) {
		this.capacidadeAlocada = capacidadeAlocada;
	}

	public void setCapacidadeTotal(double capacidadeTotal) {
		this.capacidadeTotal = capacidadeTotal;
	}

	public void setCustoGrama(double custoGrama) {
		this.custoGrama = custoGrama;
	}

	public void setDestino(Localidade destino) {
		this.destino = destino;
	}

	public void setOrigem(Localidade origem) {
		this.origem = origem;
	}

	public void setTempoEntrega(int tempoEntrega) {
		this.tempoEntrega = tempoEntrega;
	}

}
