package LogisticApp.data.interfaces;

import java.util.Collection;
import LogisticApp.business.entities.Localidade;

public interface LocalidadeDAO {
	
	public abstract void create(Localidade localidade);
	public abstract void update(Localidade localidade);
	public abstract Localidade retrieveByDescricao(String descricao);
	public abstract Localidade retrieveById(int id);
	public abstract Collection<Localidade> retrieveAll();
	public abstract void delete(Localidade localidade);
	public abstract void deleteById(int id);
	public abstract void deleteByDescricao(String descricao);

}
