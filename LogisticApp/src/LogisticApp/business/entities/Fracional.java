package LogisticApp.business.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Fracional extends Rota {

	private List<Rota> trechos;
	
	public Fracional(String nome){
		this(0, nome);
	}

	public Fracional(int id, String nome) {
		super(id, nome);
		this.trechos = new ArrayList<Rota>();
	}

	public void addTrecho(Collection<Rota> rotas) {
		this.trechos.addAll(rotas);
	}

	public void addTrecho(Rota rota) {
		this.trechos.add(rota);
	}
	
	@Override
	public void aumentarCapacidadeAlocada(double volume) {
		for (Rota rota : this.trechos)
			rota.aumentarCapacidadeAlocada(volume);
	}
	
	@Override
	public double getCapacidadeTransporte() {
		Rota menor = null;
		for (Rota rota : this.trechos) {
			if (menor == null)
				menor = rota;
			else if (rota.getCapacidadeTransporte() < menor.getCapacidadeTransporte())
				menor = rota;
		}
		return (menor == null) ? 0 : menor.getCapacidadeTransporte();
	}

	@Override
	public double getCustoGrama() {
		double soma = 0;
		for (Rota rota : this.trechos)
			soma += rota.getCustoGrama();
		return soma * 0.8;
	}

	@Override
	public Localidade getDestino() {
		return (this.trechos.isEmpty()) ? null : this.trechos.get(this.trechos.size() - 1).getDestino();
	}

	@Override
	public Localidade getOrigem() {
		return (this.trechos.isEmpty()) ? null : this.trechos.get(0).getOrigem();
	}

	@Override
	public int getTempoEntrega() {
		int soma = 0;
		for (Rota rota : this.trechos)
			soma += rota.getTempoEntrega();
		return soma + this.trechos.size() - 1;
	}

	public Rota getTrecho(int index){
		return this.trechos.get(index);
	}

	public int getTrechosSize(){
		return this.trechos.size();
	}

}
