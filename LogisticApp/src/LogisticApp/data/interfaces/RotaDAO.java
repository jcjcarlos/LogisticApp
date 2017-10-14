package LogisticApp.data.interfaces;

import java.sql.SQLException;
import java.util.Collection;

import LogisticApp.business.entities.Localidade;
import LogisticApp.business.entities.Rota;

public interface RotaDAO {

	public abstract void update(Rota rota) throws SQLException;

	public abstract void create(Rota rota) throws SQLException;

	public abstract Collection<Rota> retrieveAll() throws SQLException;

	public abstract Rota retrieveById(int id) throws SQLException;

	public abstract Collection<Rota> retrieveByOriginDestiny(Localidade origem, Localidade destino) throws SQLException;

}
