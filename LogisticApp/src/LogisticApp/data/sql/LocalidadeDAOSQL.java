package LogisticApp.data.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import LogisticApp.business.entities.Localidade;
import LogisticApp.data.DBConnection;
import LogisticApp.data.interfaces.ILocalidadeDAO;
import LogisticApp.data.interfaces.ISequenceSurrogate;
import LogisticApp.data.queries.LocalidadeQueries;
import LogisticApp.exception.CadastroException;
import LogisticApp.exception.LogisticException;

public class LocalidadeDAOSQL implements ILocalidadeDAO {

	@Override
	public void create(Localidade localidade) throws Exception {
		PreparedStatement pstm = DBConnection.getConnection()
				.prepareStatement(LocalidadeQueries.CREATE_LOCALIDADE.getConsulta());
		ISequenceSurrogate sequenceSurrogate = new SequenceSurrogateSQL();
		int id = sequenceSurrogate.generateKey("surrogate_localidade");
		pstm.setInt(1, id);
		pstm.setString(2, localidade.getDescricao());
		try{
			pstm.executeUpdate();
		}
		catch (SQLException ex) {
			sequenceSurrogate.restoreKey("surrogate_localidade", id);
			if (ex.getSQLState().startsWith("23"))
				throw new CadastroException("Uma localidade com esse nome já existe nos nossos registros.");
			else
				throw new CadastroException("Erro no banco de dados.");
		}
	}

	@Override
	public Collection<Localidade> retrieveAll() throws Exception {
		List<Localidade> localidades = new ArrayList<Localidade>();
		PreparedStatement pstm = DBConnection.getConnection()
				.prepareStatement(LocalidadeQueries.RETRIEVE_ALL.getConsulta());
		ResultSet rset;
		try {
			rset = pstm.executeQuery();
		} catch (Exception ex) {
			throw new LogisticException("Erro no banco de dados.");
		}
		while (rset.next()) {
			Localidade localidade = new Localidade(rset.getInt("id"), rset.getString("descricao"));
			localidades.add(localidade);
		}
		Collections.sort(localidades);
		return localidades;
	}

	@Override
	public Localidade retrieveById(int id) throws Exception {
		PreparedStatement pstm = DBConnection.getConnection()
				.prepareStatement(LocalidadeQueries.RETRIEVE_BY_ID.getConsulta());
		pstm.setInt(1, id);
		ResultSet rset;
		try {
			rset = pstm.executeQuery();
		} catch (Exception ex) {
			throw new LogisticException("Erro no banco de dados.");
		}
		Localidade localidade = null;
		if (rset.next())
			localidade = new Localidade(rset.getInt("id"), rset.getString("descricao"));
		return localidade;
	}

	@Override
	public void update(Localidade localidade) throws Exception {
		PreparedStatement pstm = DBConnection.getConnection()
				.prepareStatement(LocalidadeQueries.UPDATE_LOCALIDADE.getConsulta());
		pstm.setInt(1, localidade.getId());
		pstm.setString(2, localidade.getDescricao());
		pstm.setInt(3, localidade.getId());
		try {
			pstm.executeUpdate();
		} catch (Exception ex) {
			throw new LogisticException("Erro durante a rotina de atualização de localidades.");
		}
	}

}
