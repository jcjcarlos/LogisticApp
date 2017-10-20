package LogisticApp.data.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import LogisticApp.business.entities.Direta;
import LogisticApp.business.entities.Fracional;
import LogisticApp.business.entities.Localidade;
import LogisticApp.business.entities.Rota;
import LogisticApp.data.DBConnection;
import LogisticApp.data.interfaces.IRotaDAO;
import LogisticApp.data.interfaces.ISequenceSurrogate;
import LogisticApp.data.queries.RotaQueries;
import LogisticApp.exception.CadastroException;
import LogisticApp.exception.LogisticException;
import LogisticApp.exception.RotaNotFoundException;

public class RotaDAOSQL implements IRotaDAO {

	@Override
	public void create(Rota rota) throws Exception {
		PreparedStatement pstm = DBConnection.getConnection().prepareStatement(RotaQueries.INSERT_ROTA.getConsulta());
		ISequenceSurrogate sequenceSurrogate = new SequenceSurrogateSQL();
		int id = sequenceSurrogate.generateKey("surrogate_rota");
		rota.setId(id);
		pstm.setInt(1, id);
		pstm.setString(2, rota.getNome());
		pstm.setObject(3, (rota.getOrigem() == null) ? null : rota.getOrigem().getId());
		pstm.setObject(4, (rota.getDestino() == null) ? null : rota.getDestino().getId());
		pstm.setDouble(7, rota.getCustoGrama());
		pstm.setInt(8, rota.getTempoEntrega());
		if (rota instanceof Direta) {
			Direta direta = (Direta) rota;
			pstm.setDouble(5, direta.getCapacidadeTotal());
			pstm.setDouble(6, direta.getCapacidadeAlocada());
			pstm.setString(9, "D");
		} else if (rota instanceof Fracional) {
			pstm.setObject(5, null);
			pstm.setObject(6, null);
			pstm.setString(9, "F");
		}
		try {
			pstm.executeUpdate();
		} catch (SQLException ex) {
			sequenceSurrogate.restoreKey("surrogate_rota", id);
			if (ex.getSQLState().startsWith("23"))
				throw new CadastroException("Uma rota com esse nome já existe nos nossos registros.");
			else
				throw new CadastroException("Erro no banco de dados.");
		}
		
		if(rota instanceof Fracional){
			try{
				this.createTrechos((Fracional) rota);
			}
			catch(Exception ex){
				throw new CadastroException("Erro no banco de dados.");
			}
		}
	}

	private void createTrechos(Fracional rota) throws Exception {
		for (int i = 0; i < rota.getTrechosSize(); i++)
			this.insertTrecho(rota.getId(), rota.getTrecho(i).getId(), i);
	}

	private void insertTrecho(int idRotaFracional, int idRotaTrecho, int ordem) throws Exception {
		PreparedStatement pstm = DBConnection.getConnection().prepareStatement(RotaQueries.INSERT_TRECHO.getConsulta());
		pstm.setInt(1, idRotaFracional);
		pstm.setInt(2, idRotaTrecho);
		pstm.setInt(3, ordem);
		try {
			pstm.executeUpdate();
		} catch (Exception ex) {
			throw new CadastroException("Erro no banco de dados.");
		}
	}

	@Override
	public Collection<Rota> retrieveAll() throws Exception {
		PreparedStatement pstm = DBConnection.getConnection().prepareStatement(RotaQueries.RETRIEVE_ALL.getConsulta());
		List<Rota> rotas = new ArrayList<Rota>();
		ResultSet rset;
		try {
			rset = pstm.executeQuery();
		} catch (Exception ex) {
			throw new LogisticException("Erro no banco de dados.");
		}
		while (rset.next()) {
			Rota rota = this.retrieveById(rset.getInt("id"));
			rotas.add(rota);
		}
		Collections.sort(rotas);
		return rotas;
	}

	private Rota createRota(ResultSet rset) throws Exception {
		Rota rota = null;
		if (rset.next()) {
			int id_ = rset.getInt("id");
			String nome = rset.getString("nome");
			char tipo = rset.getString("tipo").charAt(0);
			if (tipo == 'D') {
				LocalidadeDAOSQL localidadeDAO = new LocalidadeDAOSQL();
				Localidade origem = localidadeDAO.retrieveById(rset.getInt("idLocalidadeOrigem"));
				Localidade destino = localidadeDAO.retrieveById(rset.getInt("idLocalidadeDestino"));
				rota = new Direta(id_, nome, origem, destino, rset.getDouble("capacidadeTotal"),
						rset.getDouble("capacidadeAlocada"), rset.getDouble("custoGrama"), rset.getInt("tempoEntrega"));
			} else if (tipo == 'F') {
				Collection<Rota> trechos = this.retrieveTrechos(id_);
				Fracional fracional = new Fracional(id_, nome);
				fracional.addTrecho(trechos);
				rota = fracional;
			}
		}
		return rota;
	}

	@Override
	public Rota retrieveById(int id) throws Exception {
		PreparedStatement pstm = DBConnection.getConnection()
				.prepareStatement(RotaQueries.RETRIEVE_BY_ID.getConsulta());
		pstm.setInt(1, id);
		ResultSet rset;
		try {
			rset = pstm.executeQuery();
		} catch (Exception ex) {
			throw new LogisticException("Erro no banco de dados.");
		}
		Rota rota = this.createRota(rset);
		if (rota == null)
			throw new RotaNotFoundException("Rota com ID " + id + " não encontrada.");
		return rota;
	}

	@Override
	public Rota retrieveByName(String name) throws Exception {
		PreparedStatement pstm = DBConnection.getConnection()
				.prepareStatement(RotaQueries.RETRIEVE_BY_NAME.getConsulta());
		pstm.setString(1, name);
		ResultSet rset;
		try {
			rset = pstm.executeQuery();
		} catch (Exception ex) {
			throw new LogisticException("Erro no banco de dados.");
		}
		Rota rota = this.createRota(rset);
		if (rota == null)
			throw new RotaNotFoundException("Rota com nome " + name + " não encontrada.");
		return rota;
	}

	@Override
	public Collection<Rota> retrieveByOriginDestiny(Localidade origem, Localidade destino) throws Exception {
		PreparedStatement pstm = DBConnection.getConnection()
				.prepareStatement(RotaQueries.RETRIEVE_BY_ORIGIN_DESTINY.getConsulta());
		pstm.setInt(1, origem.getId());
		pstm.setInt(2, destino.getId());
		List<Rota> rotas = new ArrayList<Rota>();
		ResultSet rset;
		try {
			rset = pstm.executeQuery();
		} catch (Exception ex) {
			throw new LogisticException("Erro no banco de dados.");
		}
		while (rset.next()) {
			Rota rota = this.retrieveById(rset.getInt("id"));
			rotas.add(rota);
		}
		if (rotas.size() == 0)
			throw new RotaNotFoundException(
					"Não existe rota nesse trecho: " + origem.getDescricao() + " / " + destino.getDescricao());
		Collections.sort(rotas);
		return rotas;
	}

	private Collection<Rota> retrieveTrechos(int id) throws Exception {
		List<Rota> trechos = new ArrayList<Rota>();
		PreparedStatement pstm = DBConnection.getConnection()
				.prepareStatement(RotaQueries.RETRIEVE_TRECHOS_BY_ID.getConsulta());
		pstm.setInt(1, id);
		ResultSet resultSet;
		try {
			resultSet = pstm.executeQuery();
		} catch (Exception ex) {
			throw new LogisticException("Erro no banco de dados.");
		}
		while (resultSet.next()) {
			Rota trecho = this.retrieveById(resultSet.getInt("idRotaTrecho"));
			trechos.add(trecho);
		}
		return trechos;
	}

	@Override
	public void update(Rota rota) throws Exception {
		PreparedStatement pstm = DBConnection.getConnection().prepareStatement(RotaQueries.UPDATE_ROTA.getConsulta());
		pstm.setInt(1, rota.getId());
		pstm.setString(2, rota.getNome());
		pstm.setObject(3, (rota.getOrigem() == null) ? null : rota.getOrigem().getId());
		pstm.setObject(4, (rota.getDestino() == null) ? null : rota.getDestino().getId());
		pstm.setDouble(7, rota.getCustoGrama());
		pstm.setInt(8, rota.getTempoEntrega());
		pstm.setInt(10, rota.getId());
		if (rota instanceof Direta) {
			Direta direta = (Direta) rota;
			pstm.setDouble(5, direta.getCapacidadeTotal());
			pstm.setDouble(6, direta.getCapacidadeAlocada());
			pstm.setString(9, "D");
		} else if (rota instanceof Fracional) {
			pstm.setObject(5, null);
			pstm.setObject(6, null);
			pstm.setString(9, "F");
		}
		try {
			pstm.executeUpdate();
			this.updateTrechos((Fracional) rota);
		} catch (Exception ex) {
			throw new LogisticException("Erro durante a rotina de atualização de rotas.");
		}
	}

	private int updateTrecho(int idRotaFracional, int idRotaTrecho, int ordem) throws Exception {
		PreparedStatement pstm = DBConnection.getConnection().prepareStatement(RotaQueries.UPDATE_TRECHO.getConsulta());
		pstm.setInt(1, idRotaFracional);
		pstm.setInt(2, idRotaTrecho);
		pstm.setInt(3, ordem);
		pstm.setInt(4, idRotaFracional);
		pstm.setInt(5, ordem);
		try {
			return pstm.executeUpdate();
		} catch (Exception ex) {
			throw new LogisticException("Erro durante a rotina de atualização de rotas.");
		}
	}

	private void updateTrechos(Fracional rota) throws Exception {
		try {
			for (int i = 0; i < rota.getTrechosSize(); i++) {
				int rowModified = this.updateTrecho(rota.getId(), rota.getTrecho(i).getId(), i);
				if (rowModified == 0)
					this.insertTrecho(rota.getId(), rota.getTrecho(i).getId(), i);
				this.update(rota.getTrecho(i));
			}
		} catch (Exception ex) {
			throw new LogisticException("Erro durante a rotina de atualização de rotas.");
		}
	}

}
