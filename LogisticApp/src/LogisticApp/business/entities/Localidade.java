package LogisticApp.business.entities;

public class Localidade {
	
	private int id;
	private String descricao;
	
	public Localidade(int id, String descricao){
		this.setId(id);
		this.setDescricao(descricao);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
