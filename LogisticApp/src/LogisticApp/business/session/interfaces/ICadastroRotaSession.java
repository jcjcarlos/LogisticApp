package LogisticApp.business.session.interfaces;

import java.util.List;

import LogisticApp.view.vo.LocalidadeVO;
import LogisticApp.view.vo.RotaVO;

public interface ICadastroRotaSession {

	public abstract void createRota(int id, String nome, char tipo, int idOrigem, int idDestino, double capacidadeTotal,
			double custoGrama, int tempoEntrega, List<Integer> trechos) throws Exception;

	public abstract RotaVO recuperarRotaPorNome(String nome) throws Exception;

	public abstract List<LocalidadeVO> recuperarLocalidades() throws Exception;

}