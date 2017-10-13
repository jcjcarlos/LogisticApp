package LogisticApp.data.interfaces;

import java.util.Collection;

import LogisticApp.business.entities.Localidade;
import LogisticApp.business.entities.Rota;

public interface RotaDAO {
	
	public abstract void update(Rota rota);
	public abstract void create(Rota rota);
	public abstract Collection<Rota> retrieveAll();
	public abstract Rota retrieveById(int id);
	public abstract Rota retrieveByNome(String nome);
	public abstract Collection<Rota> retrieveByOriginDestiny(Localidade origem, Localidade destino);
	public abstract void delete(Rota rota);
	public abstract void deleteById(int id);
	public abstract void deleteByNome(String nome);

}
