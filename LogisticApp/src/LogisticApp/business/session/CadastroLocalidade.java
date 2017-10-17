package LogisticApp.business.session;

import java.sql.SQLException;

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
	public void createLocalidade(int id, String nome) throws CadastroException {
		Localidade localidade = new Localidade(id, nome);
		try {
			this.localidadeDAO.create(localidade);
		} catch (SQLException ex) {
			if (ex.getSQLState().startsWith("23"))
				throw new CadastroException("O ID já existe na base de dados.");
			else
				throw new CadastroException("Erro na base de dados.");
		} catch (Exception ex) {
			throw new CadastroException("Erro: Não foi possível realizar o cadastro da localidade " + nome + ".");
		}
	}

}
