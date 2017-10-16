package LogisticApp.business.session;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import LogisticApp.business.entities.Localidade;
import LogisticApp.business.entities.Rota;
import LogisticApp.business.session.interfaces.IContratacaoTransporteSession;
import LogisticApp.data.interfaces.IRotaDAO;
import LogisticApp.data.sql.RotaDAOSQL;

public class ContratacaoTransporte implements IContratacaoTransporteSession {

	private Localidade destino;
	private Localidade origem;
	private double pesoVolume;
	private IRotaDAO rotaDAO;

	public ContratacaoTransporte(Localidade origem, Localidade destino, double pesoVolume) {
		this.setOrigem(origem);
		this.setDestino(destino);
		this.setPesoVolume(pesoVolume);
		this.rotaDAO = new RotaDAOSQL();
	}

	@Override
	public void atualizarRota(int idRota) throws Exception {
		Rota rota = this.rotaDAO.retrieveById(idRota);
		rota.aumentarCapacidadeAlocada(this.getPesoVolume());
		this.rotaDAO.update(rota);
	}

	private double calcularValorPeso(double custoGrama) {
		return this.getPesoVolume() * 1000 * custoGrama;
	}

	public Localidade getDestino() {
		return this.destino;
	}

	@Override
	public Map<Integer, String> getInfoRotasCapacitadas() throws Exception {
		Map<Integer, String> rotasCapacitadas = new HashMap<Integer, String>();
		Collection<Rota> rotas = this.getRotasCapacitadas();
		for (Rota rota : rotas)
			rotasCapacitadas.put(rota.getId(), mensagemInfoRota(rota));
		return rotasCapacitadas;
	}

	public Localidade getOrigem() {
		return this.origem;
	}

	public double getPesoVolume() {
		return this.pesoVolume;
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

	public void setDestino(Localidade destino) {
		this.destino = destino;
	}

	public void setOrigem(Localidade origem) {
		this.origem = origem;
	}

	public void setPesoVolume(double pesoVolume) {
		this.pesoVolume = pesoVolume;
	}

}
