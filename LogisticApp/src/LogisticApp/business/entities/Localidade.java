package LogisticApp.business.entities;

public class Localidade implements Comparable<Localidade>{

	private String descricao;
	private int id;
	
	public Localidade(String descricao){
		this(0, descricao);
	}

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
		if(id != other.id)
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
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
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		return result;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(Localidade o) {
		return this.getDescricao().compareTo(o.getDescricao());
	}

}
