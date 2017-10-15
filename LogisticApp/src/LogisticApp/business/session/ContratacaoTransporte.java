package LogisticApp.business.session;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import LogisticApp.business.entities.Localidade;
import LogisticApp.business.entities.Rota;
import LogisticApp.business.session.interfaces.LogisticaSessao;
import LogisticApp.data.interfaces.RotaDAO;
import LogisticApp.data.sql.RotaDAOSQL;

public class ContratacaoTransporte implements LogisticaSessao {

	private Localidade origem;
	private Localidade destino;
	private double pesoVolume;
	private RotaDAO rotaDAO;

	public ContratacaoTransporte(Localidade origem, Localidade destino, double pesoVolume) {
		this.setOrigem(origem);
		this.setDestino(destino);
		this.setPesoVolume(pesoVolume);
		this.rotaDAO = new RotaDAOSQL();
	}

	public Localidade getOrigem() {
		return this.origem;
	}

	public void setOrigem(Localidade origem) {
		this.origem = origem;
	}

	public Localidade getDestino() {
		return this.destino;
	}

	public void setDestino(Localidade destino) {
		this.destino = destino;
	}

	public double getPesoVolume() {
		return this.pesoVolume;
	}

	public void setPesoVolume(double pesoVolume) {
		this.pesoVolume = pesoVolume;
	}

	public void atualizarRota(int idRota) throws Exception {
		Rota rota = this.rotaDAO.retrieveById(idRota);
		rota.aumentarCapacidadeAlocada(this.getPesoVolume());
		this.rotaDAO.update(rota);
	}

	public Map<Integer, String> getInfoRotasCapacitadas() throws Exception {
		Map<Integer, String> rotasCapacitadas = new HashMap<Integer, String>();
		Collection<Rota> rotas = this.getRotasCapacitadas();
		for (Rota rota : rotas)
			rotasCapacitadas.put(rota.getId(), mensagemInfoRota(rota));
		return rotasCapacitadas;
	}

	private Collection<Rota> getRotasCapacitadas() throws Exception {
		Collection<Rota> rotas = this.rotaDAO.retrieveByOriginDestiny(this.getOrigem(), this.getDestino());
		for (Rota rota : rotas) {
			if (rota.getCapacidadeTransporte() < this.getPesoVolume())
				rotas.remove(rota);
		}
		return rotas;
	}

	private String mensagemInfoRota(Rota rota) {
		String result = "";
		result += rota.getNome() + " - ";
		result += rota.getTempoEntrega() + " dias - R$ ";
		result += String.format("%.2f", this.calcularValorPeso(rota.getCustoGrama()));
		return result;
	}

	private double calcularValorPeso(double custoGrama) {
		return this.getPesoVolume() * 1000 * custoGrama;
	}

}
