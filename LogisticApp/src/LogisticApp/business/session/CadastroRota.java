package LogisticApp.business.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import LogisticApp.business.entities.Direta;
import LogisticApp.business.entities.Fracional;
import LogisticApp.business.entities.Localidade;
import LogisticApp.business.entities.Rota;
import LogisticApp.business.session.interfaces.ICadastroRotaSession;
import LogisticApp.data.interfaces.ILocalidadeDAO;
import LogisticApp.data.interfaces.IRotaDAO;
import LogisticApp.data.sql.LocalidadeDAOSQL;
import LogisticApp.data.sql.RotaDAOSQL;
import LogisticApp.view.vo.LocalidadeVO;
import LogisticApp.view.vo.RotaVO;

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
	public RotaVO recuperarRotaPorNome(String nome) throws Exception {
		Rota rota = this.rotaDAO.retrieveByName(nome);
		RotaVO rotaVO = new RotaVO(rota.getId(), rota.getNome());
		return rotaVO;
	}

	@Override
	public List<LocalidadeVO> recuperarLocalidades() throws Exception {
		List<LocalidadeVO> localidades = new ArrayList<LocalidadeVO>();
		Collection<Localidade> locais = this.localidadeDAO.retrieveAll();
		for (Localidade local : locais)
			localidades.add(new LocalidadeVO(local.getId(), local.getDescricao()));
		return localidades;
	}

}
