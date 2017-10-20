package LogisticApp.view.vo;

public class RotaVO {
	
	private int id;
	private String nome;
	private String descricaoOrigem;
	private String descricaoDestino;
	
	public RotaVO(int id, String nome, String descricaoOrigem, String descricaoDestino) {
		this.id = id;
		this.nome = nome;
		this.descricaoOrigem = descricaoOrigem;
		this.descricaoDestino = descricaoDestino;
	}

	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public String getDescricaoOrigem() {
		return descricaoOrigem;
	}

	public String getDescricaoDestino() {
		return descricaoDestino;
	}
	
	public String toString(){
		return this.nome + " - " + this.descricaoOrigem + " - " + this.descricaoDestino;
	}

}
