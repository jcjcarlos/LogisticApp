package LogisticApp.data.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import LogisticApp.business.entities.Localidade;
import LogisticApp.business.entities.Rota;
import LogisticApp.data.DBConnection;
import LogisticApp.data.interfaces.RotaDAO;

public class FracionalDAOSQL implements RotaDAO {
	
	private static final String UPDATE_FRACIONAL = "";
	private static final String UPDATE_TRECHO = "";
	private static final String RETRIEVE_ALL = "SELECT id, nome FROM ROTA WHERE tipo = 'F'";
	private static final String RETRIEVE_TRECHOS = "SELECT idRotaTrecho FROM TRECHO WHERE idRotaFracionada = ? ORDER BY ordem";
	private static final String CREATE_FRACIONAL = "INSERT INTO ROTA(id, nome, idLocalidadeOrigem, idLocalidadeDestino, capacidadeTransporte, custoGrama, tempoEntrega, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String CREATE_TRECHO = "INSERT INTO TRECHO(idRotaFracionada, idRotaTrecho, ordem) VALUES (?, ?, ?)";
	
	@Override
	public void update(Rota rota) {
		
	}

	@Override
	public void create(Rota rota) {
		
	}

	@Override
	public Collection<Rota> retrieveAll() throws SQLException {
		PreparedStatement psmt = DBConnection.getConnection().prepareStatement(RETRIEVE_ALL);
		List<Rota> rotas = new ArrayList<Rota>();
		ResultSet rset = psmt.executeQuery();
		while(rset.next()){
			int id = rset.getInt("id");
			String nome = rset.getString("nome");
		}
		return rotas;
	}
	
	private Collection<Rota> retrieveTrechos(int id){
		return null;
	}

	@Override
	public Rota retrieveById(int id) {
		return null;
	}

	@Override
	public Rota retrieveByNome(String nome) {
		return null;
	}

	@Override
	public Collection<Rota> retrieveByOriginDestiny(Localidade origem, Localidade destino) {
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

	@Override
	public void deleteByNome(String nome) {
		// TODO Auto-generated method stub
		
	}

}
