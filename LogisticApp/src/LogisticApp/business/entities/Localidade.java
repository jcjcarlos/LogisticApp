package LogisticApp.business.entities;

public class Localidade {

	private String descricao;
	private int id;

	public Localidade(int id, String descricao) {
		this.setId(id);
		this.setDescricao(descricao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Localidade other = (Localidade) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(int id) {
		this.id = id;
	}

}
