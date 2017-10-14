package LogisticApp.data.interfaces;

import java.sql.SQLException;
import java.util.Collection;
import LogisticApp.business.entities.Localidade;

public interface LocalidadeDAO {
	
	public abstract void create(Localidade localidade) throws SQLException;
	public abstract void update(Localidade localidade) throws SQLException;
	public abstract Localidade retrieveById(int id);
	public abstract Collection<Localidade> retrieveAll();
	public abstract void delete(Localidade localidade);
	public abstract void deleteById(int id);

}
