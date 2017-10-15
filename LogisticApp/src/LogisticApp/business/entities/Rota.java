package LogisticApp.business.entities;

public abstract class Rota {

	protected int id;
	protected String nome;

	public Rota(int id, String nome) {
		this.setId(id);
		this.setNome(nome);
	}

	private void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public int getId() {
		return this.id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
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
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public abstract Localidade getOrigem();

	public abstract Localidade getDestino();

	public abstract double getCapacidadeTransporte();

	public abstract double getCustoGrama();

	public abstract int getTempoEntrega();

	public abstract void aumentarCapacidadeAlocada(double volume);

}
