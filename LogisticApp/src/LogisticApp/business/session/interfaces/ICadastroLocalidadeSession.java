package LogisticApp.business.session.interfaces;

import LogisticApp.exception.CadastroException;

public interface ICadastroLocalidadeSession {
	
	public abstract void createLocalidade(int id, String nome) throws CadastroException;

}
