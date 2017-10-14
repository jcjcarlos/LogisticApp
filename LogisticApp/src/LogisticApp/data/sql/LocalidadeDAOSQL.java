package LogisticApp.data.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import LogisticApp.business.entities.Localidade;
import LogisticApp.data.DBConnection;
import LogisticApp.data.interfaces.LocalidadeDAO;
import LogisticApp.data.queries.LocalidadeQueries;

public class LocalidadeDAOSQL implements LocalidadeDAO {

	@Override
	public void create(Localidade localidade) throws SQLException {
		PreparedStatement pstm = DBConnection.getConnection().prepareStatement(LocalidadeQueries.CREATE_LOCALIDADE.getConsulta());
		pstm.setInt(1, localidade.getId());
		pstm.setString(2, localidade.getDescricao());
		pstm.executeQuery();
	}

	@Override
	public void update(Localidade localidade) throws SQLException {
		PreparedStatement pstm = DBConnection.getConnection().prepareStatement(LocalidadeQueries.UPDATE_LOCALIDADE.getConsulta());
		
	}

	@Override
	public Localidade retrieveById(int id) {
		return null;
	}

	@Override
	public void deleteById(int id) {
		
	}

	@Override
	public Collection<Localidade> retrieveAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Localidade localidade) {
		// TODO Auto-generated method stub
		
	}
	
}
