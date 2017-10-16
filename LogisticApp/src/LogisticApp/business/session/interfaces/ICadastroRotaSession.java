package LogisticApp.business.session.interfaces;

import java.util.List;

public interface ICadastroRotaSession {

	public abstract void createRota(int id, String nome, char tipo, int idOrigem, int idDestino, double capacidadeTotal,
			double custoGrama, int tempoEntrega, List<Integer> trechos) throws Exception;

}