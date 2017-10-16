package LogisticApp.view.vo;

public class RotaCapacitadaVO extends RotaVO {
	
	private double pesoVolume;
	private double valorPeso;
	
	public RotaCapacitadaVO(int id, String nome, double pesoVolume, double valorPeso) {
		super(id, nome);
		this.pesoVolume = pesoVolume;
		this.valorPeso = valorPeso;
	}
	
	public double getPesoVolume() {
		return pesoVolume;
	}
	
	public double getValorPeso() {
		return valorPeso;
	}

}