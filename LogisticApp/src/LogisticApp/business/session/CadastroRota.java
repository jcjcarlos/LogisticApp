package LogisticApp.business.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import LogisticApp.business.entities.Direta;
import LogisticApp.business.entities.Fracional;
import LogisticApp.business.entities.Localidade;
import LogisticApp.business.entities.Rota;
import LogisticApp.business.session.interfaces.ICadastroRotaSession;
import LogisticApp.data.interfaces.ILocalidadeDAO;
import LogisticApp.data.interfaces.IRotaDAO;
import LogisticApp.data.sql.LocalidadeDAOSQL;
import LogisticApp.data.sql.RotaDAOSQL;

public class CadastroRota implements ICadastroRotaSession {

	private IRotaDAO rotaDAO;
	private ILocalidadeDAO localidadeDAO;

	public CadastroRota() {
		this.rotaDAO = new RotaDAOSQL();
		this.localidadeDAO = new LocalidadeDAOSQL();
	}

	@Override
	public void createRota(int id, String nome, char tipo, int idOrigem, int idDestino, double capacidadeTotal,
			double custoGrama, int tempoEntrega, List<Integer> trechos) throws Exception {
		Rota rota = null;
		if (tipo == 'D') {
			rota = new Direta(id, nome, this.localidadeDAO.retrieveById(idOrigem),
					this.localidadeDAO.retrieveById(idDestino), capacidadeTotal, 0, custoGrama, tempoEntrega);
		} else if (tipo == 'F') {
			Collection<Rota> trechosFracional = new ArrayList<Rota>();
			for (Integer trechoId : trechos) {
				Rota trecho = this.rotaDAO.retrieveById(trechoId);
				trechosFracional.add(trecho);
			}
			Fracional fracional = new Fracional(id, nome);
			fracional.addTrecho(trechosFracional);
			rota = fracional;
		}
		this.rotaDAO.create(rota);
	}

	@Override
	public Map<Integer, String> recuperarRotaPorNome(String nome) throws Exception {
		Map<Integer, String> rotas = new HashMap<Integer, String>();
		Rota rota = this.rotaDAO.retrieveByName(nome);
		rotas.put(rota.getId(), rota.toString());
		return rotas;
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
