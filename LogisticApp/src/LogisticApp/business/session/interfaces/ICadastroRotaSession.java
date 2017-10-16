package LogisticApp.business.session.interfaces;

import java.util.List;
import java.util.Map;

public interface ICadastroRotaSession {

	public abstract void createRota(int id, String nome, char tipo, int idOrigem, int idDestino, double capacidadeTotal,
			double custoGrama, int tempoEntrega, List<Integer> trechos) throws Exception;

	public abstract Map<Integer, String> recuperarRotaPorNome(String nome) throws Exception;

	public abstract Map<Integer, String> recuperarLocalidades() throws Exception;

}