package LogisticApp.business.entities;

import LogisticApp.exception.LogisticException;

public abstract class Rota implements Comparable<Rota> {

	protected int id;
	protected String nome;
	
	public Rota(String nome){
		this(0, nome);
	}

	public Rota(int id, String nome) {
		this.setId(id);
		this.setNome(nome);
	}

	public abstract void aumentarCapacidadeAlocada(double volume) throws LogisticException;
	
	@Override
	public int compareTo(Rota o){
		return this.getNome().compareTo(o.getNome());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rota other = (Rota) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public abstract double getCapacidadeTransporte();

	public abstract double getCustoGrama();

	public abstract Localidade getDestino();

	public int getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public abstract Localidade getOrigem();

	public abstract int getTempoEntrega();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return this.getNome() + " - " + this.getOrigem().getDescricao() + " - " + this.getDestino().getDescricao();
	}

}
