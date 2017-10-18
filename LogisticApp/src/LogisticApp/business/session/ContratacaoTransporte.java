package LogisticApp.business.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import LogisticApp.business.entities.Localidade;
import LogisticApp.business.entities.Rota;
import LogisticApp.business.session.interfaces.IContratacaoTransporteSession;
import LogisticApp.data.interfaces.ILocalidadeDAO;
import LogisticApp.data.interfaces.IRotaDAO;
import LogisticApp.data.sql.LocalidadeDAOSQL;
import LogisticApp.data.sql.RotaDAOSQL;
import LogisticApp.exception.LogisticException;
import LogisticApp.view.vo.LocalidadeVO;
import LogisticApp.view.vo.RotaCapacitadaVO;
import LogisticApp.view.vo.RotaVO;

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
	public List<RotaVO> getInfoRotasCapacitadas(int idOrigem, int idDestino, double pesoVolume)
			throws Exception {
		List<RotaVO> rotasCapacitadas = new ArrayList<RotaVO>();
		Localidade origem = this.localidadeDAO.retrieveById(idOrigem);
		Localidade destino = this.localidadeDAO.retrieveById(idDestino);
		Collection<Rota> rotas = this.getRotasCapacitadas(origem, destino, pesoVolume);
		
		if(rotas.isEmpty())
			throw new LogisticException("Não foi possível encontrar rotas nesse trecho com capacidade para o peso solicitado.");
		
		for (Rota rota : rotas)
			rotasCapacitadas.add(new RotaCapacitadaVO(rota.getId(), rota.getNome(), pesoVolume,
					this.calcularValorPeso(rota.getCustoGrama(), pesoVolume)));
		return rotasCapacitadas;
	}

	private Collection<Rota> getRotasCapacitadas(Localidade origem, Localidade destino, double pesoVolume)
			throws Exception {
		Collection<Rota> rotas = this.rotaDAO.retrieveByOriginDestiny(origem, destino);
		
		if(rotas.isEmpty())
			throw new LogisticException("Não foi possível encontrar rotas com essas localidades como origem e destino.");
		
		List<Rota> rotasCapacitadas = new ArrayList<Rota>();
		for (Rota rota : rotas) {
			if (rota.getCapacidadeTransporte() >= pesoVolume)
				rotasCapacitadas.add(rota);
		}
		return rotasCapacitadas;
	}

	@Override
	public List<LocalidadeVO> recuperarLocalidades() throws Exception {
		Collection<Localidade> locais = this.localidadeDAO.retrieveAll();
		List<LocalidadeVO> localidades = new ArrayList<LocalidadeVO>();
		for (Localidade local : locais)
			localidades.add(new LocalidadeVO(local.getId(), local.getDescricao()));
		return localidades;
	}

}
