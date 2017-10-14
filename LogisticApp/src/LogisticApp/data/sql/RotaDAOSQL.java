package LogisticApp.data.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import LogisticApp.business.entities.Direta;
import LogisticApp.business.entities.Localidade;
import LogisticApp.business.entities.Rota;
import LogisticApp.data.DBConnection;
import LogisticApp.data.interfaces.RotaDAO;
import LogisticApp.data.queries.RotaQueries;

public class RotaDAOSQL implements RotaDAO {

	public RotaDAOSQL() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Rota rota) {
		// TODO Auto-generated method stub

	}

	@Override
	public void create(Rota rota) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Rota> retrieveAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rota retrieveById(int id) throws SQLException {
		PreparedStatement pstm = DBConnection.getConnection().prepareStatement(RotaQueries.RETRIEVE_BY_ID.getConsulta());
		pstm.setInt(1, id);
		ResultSet rset = pstm.executeQuery();
		Rota rota = null;
		
		if(rset.next()){
			int id_ = rset.getInt("id");
			String nome = rset.getString("nome");
			char tipo = rset.getString("tipo").charAt(0);
			if(tipo == 'D'){
				LocalidadeDAOSQL localidadeDAO = new LocalidadeDAOSQL();
				Localidade origem = localidadeDAO.retrieveById(rset.getInt("idLocalidadeOrigem"));
				Localidade destino = localidadeDAO.retrieveById(rset.getInt("idLocalidadeDestino"));
				rota = new Direta(id_, 
								  nome, 
								  origem, 
								  destino, 
								  rset.getDouble("capacidadeTotal"), 
								  rset.getDouble("capacidadeAlocada"), 
								  rset.getDouble("custoGrama"), 
								  rset.getInt("tempoEntrega"));
			}
			else{
				// recursão de inicialização de rota fracionada
				// recupera de Trecho onde idRotaFracionada igual a meu id com order by ordem
				// chama o retrieveById para cada idRotaTrecho inicializando uma rota filha
				// adiciona rota filha a um ArrayList de Rota
				
			}
		}
		
		return rota;
	}

	@Override
	public Collection<Rota> retrieveByOriginDestiny(Localidade origem, Localidade destino) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Rota rota) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

}
