package LogisticApp.business.session;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import LogisticApp.business.entities.Localidade;
import LogisticApp.business.entities.Rota;
import LogisticApp.business.session.interfaces.IContratacaoTransporteSession;
import LogisticApp.data.interfaces.IRotaDAO;
import LogisticApp.data.interfaces.ILocalidadeDAO;
import LogisticApp.data.sql.RotaDAOSQL;
import LogisticApp.data.sql.LocalidadeDAOSQL;

public class ContratacaoTransporte implements IContratacaoTransporteSession {

	private IRotaDAO rotaDAO;
	private ILocalidadeDAO localidadeDAO;

	public ContratacaoTransporte() {
		this.rotaDAO = new RotaDAOSQL();
		this.localidadeDAO = new LocalidadeDAOSQL();
	}

	@Override
	public void atualizarRota(int idRota, double pesoVolume) throws Exception {
		Rota rota = this.rotaDAO.retrieveById(idRota);
		rota.aumentarCapacidadeAlocada(pesoVolume);
		this.rotaDAO.update(rota);
	}

	private double calcularValorPeso(double custoGrama, double pesoVolume) {
		return pesoVolume * 1000 * custoGrama;
	}

	@Override
	public Map<Integer, String> getInfoRotasCapacitadas(int idOrigem, int idDestino, double pesoVolume)
			throws Exception {
		Map<Integer, String> rotasCapacitadas = new HashMap<Integer, String>();
		Localidade origem = this.localidadeDAO.retrieveById(idOrigem);
		Localidade destino = this.localidadeDAO.retrieveById(idDestino);
		Collection<Rota> rotas = this.getRotasCapacitadas(origem, destino, pesoVolume);
		for (Rota rota : rotas)
			rotasCapacitadas.put(rota.getId(), mensagemInfoRota(rota, pesoVolume));
		return rotasCapacitadas;
	}

	private Collection<Rota> getRotasCapacitadas(Localidade origem, Localidade destino, double pesoVolume)
			throws Exception {
		Collection<Rota> rotas = this.rotaDAO.retrieveByOriginDestiny(origem, destino);
		for (Rota rota : rotas) {
			if (rota.getCapacidadeTransporte() < pesoVolume)
				rotas.remove(rota);
		}
		return rotas;
	}

	private String mensagemInfoRota(Rota rota, double pesoVolume) {
		String result = "";
		result += rota.getNome() + " - ";
		result += rota.getTempoEntrega() + " dias - R$ ";
		result += String.format("%.2f", this.calcularValorPeso(rota.getCustoGrama(), pesoVolume));
		return result;
	}

	@Override
	public Map<Integer, String> recuperarLocalidades() throws Exception {
		Map<Integer, String> localidades = new HashMap<Integer, String>();
		Collection<Localidade> locais = this.localidadeDAO.retrieveAll();
		for (Localidade local : locais)
			localidades.put(local.getId(), local.getDescricao());
		return localidades;
	}

}
