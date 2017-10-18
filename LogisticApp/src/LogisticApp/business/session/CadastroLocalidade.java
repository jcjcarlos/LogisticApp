package LogisticApp.business.session;

import LogisticApp.business.entities.Localidade;
import LogisticApp.business.session.interfaces.ICadastroLocalidadeSession;
import LogisticApp.data.interfaces.ILocalidadeDAO;
import LogisticApp.data.sql.LocalidadeDAOSQL;
import LogisticApp.exception.CadastroException;

public class CadastroLocalidade implements ICadastroLocalidadeSession {

	private ILocalidadeDAO localidadeDAO;

	public CadastroLocalidade() {
		this.localidadeDAO = new LocalidadeDAOSQL();
	}

	@Override
	public void createLocalidade(String nome) throws CadastroException {
		Localidade localidade = new Localidade(nome);
		try {
			this.localidadeDAO.create(localidade);
		} catch (CadastroException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new CadastroException("Erro: Não foi possível realizar o cadastro da localidade " + nome + ".");
		}
	}

}
