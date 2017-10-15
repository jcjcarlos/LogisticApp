package LogisticApp.data.interfaces;

import java.util.Collection;

import LogisticApp.business.entities.Localidade;

public interface LocalidadeDAO {

	public abstract void create(Localidade localidade) throws Exception;

	public abstract Collection<Localidade> retrieveAll() throws Exception;

	public abstract Localidade retrieveById(int id) throws Exception;

	public abstract void update(Localidade localidade) throws Exception;

}
