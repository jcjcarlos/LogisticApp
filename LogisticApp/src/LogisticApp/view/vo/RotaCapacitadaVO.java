package LogisticApp.view.vo;

public class RotaCapacitadaVO extends RotaVO {

	private int tempoEntrega;
	private double valorPeso;

	public RotaCapacitadaVO(int id, String nome, int tempoEntrega, double valorPeso) {
		super(id, nome);
		this.tempoEntrega = tempoEntrega;
		this.valorPeso = valorPeso;
	}

	public int getTempoEntrega() {
		return tempoEntrega;
	}

	public double getValorPeso() {
		return valorPeso;
	}

	public String toString() {
		return this.getNome() + " - " + this.getTempoEntrega() + " dias - R$ "
				+ String.format("%.2f", this.getValorPeso());
	}

}