package LogisticApp.business.session;

import LogisticApp.business.entities.Localidade;
import LogisticApp.business.session.interfaces.ICadastroLocalidadeSession;
import LogisticApp.data.interfaces.ILocalidadeDAO;
import LogisticApp.data.sql.LocalidadeDAOSQL;

public class CadastroLocalidade implements ICadastroLocalidadeSession {
	
	private ILocalidadeDAO localidadeDAO;
	
	public CadastroLocalidade(){
		this.localidadeDAO = new LocalidadeDAOSQL();
	}

	@Override
	public void createLocalidade(int id, String nome) throws Exception {
		Localidade localidade = new Localidade(id, nome);
		this.localidadeDAO.create(localidade);
	}

}
