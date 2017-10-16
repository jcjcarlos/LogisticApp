package LogisticApp.data.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import LogisticApp.business.entities.Localidade;
import LogisticApp.data.DBConnection;
import LogisticApp.data.interfaces.ILocalidadeDAO;
import LogisticApp.data.queries.LocalidadeQueries;

public class LocalidadeDAOSQL implements ILocalidadeDAO {

	@Override
	public void create(Localidade localidade) throws Exception {
		PreparedStatement pstm = DBConnection.getConnection()
				.prepareStatement(LocalidadeQueries.CREATE_LOCALIDADE.getConsulta());
		pstm.setInt(1, localidade.getId());
		pstm.setString(2, localidade.getDescricao());
		pstm.executeUpdate();
	}

	@Override
	public Collection<Localidade> retrieveAll() throws Exception {
		List<Localidade> localidades = new ArrayList<Localidade>();
		PreparedStatement pstm = DBConnection.getConnection()
				.prepareStatement(LocalidadeQueries.RETRIEVE_ALL.getConsulta());
		ResultSet rset = pstm.executeQuery();
		while (rset.next()) {
			Localidade localidade = new Localidade(rset.getInt("id"), rset.getString("descricao"));
			localidades.add(localidade);
		}
		return localidades;
	}

	@Override
	public Localidade retrieveById(int id) throws Exception {
		PreparedStatement pstm = DBConnection.getConnection()
				.prepareStatement(LocalidadeQueries.RETRIEVE_BY_ID.getConsulta());
		pstm.setInt(1, id);
		ResultSet rset = pstm.executeQuery();
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
		pstm.executeUpdate();
	}

}
