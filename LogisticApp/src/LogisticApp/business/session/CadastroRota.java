package LogisticApp.business.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import LogisticApp.business.entities.Direta;
import LogisticApp.business.entities.Fracional;
import LogisticApp.business.entities.Rota;
import LogisticApp.business.session.interfaces.ICadastroRotaSession;
import LogisticApp.data.interfaces.ILocalidadeDAO;
import LogisticApp.data.interfaces.IRotaDAO;
import LogisticApp.data.sql.LocalidadeDAOSQL;
import LogisticApp.data.sql.RotaDAOSQL;

public class CadastroRota implements ICadastroRotaSession {

	private IRotaDAO rotaDAO;

	public CadastroRota() {
		this.rotaDAO = new RotaDAOSQL();
	}

	@Override
	public void createRota(int id, String nome, char tipo, int idOrigem, int idDestino, double capacidadeTotal,
			double custoGrama, int tempoEntrega, List<Integer> trechos) throws Exception {
		Rota rota = null;
		if (tipo == 'D') {
			ILocalidadeDAO localidadeDAO = new LocalidadeDAOSQL();
			rota = new Direta(id, nome, localidadeDAO.retrieveById(idOrigem), localidadeDAO.retrieveById(idDestino),
					capacidadeTotal, 0, custoGrama, tempoEntrega);
		}
		else if(tipo == 'F'){
			Collection<Rota> trechosFracional = new ArrayList<Rota>();
			for(Integer trechoId : trechos){
				Rota trecho = this.rotaDAO.retrieveById(trechoId);
				trechosFracional.add(trecho);
			}
			Fracional fracional = new Fracional(id, nome);
			fracional.addTrecho(trechosFracional);
			rota = fracional;
		}
		this.rotaDAO.create(rota);
	}

}
