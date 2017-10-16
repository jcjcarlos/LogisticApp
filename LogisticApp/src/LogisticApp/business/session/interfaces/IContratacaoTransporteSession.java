package LogisticApp.business.session.interfaces;

import java.util.List;

import LogisticApp.view.vo.LocalidadeVO;
import LogisticApp.view.vo.RotaVO;

public interface IContratacaoTransporteSession {
	
	public abstract void atualizarRota(int idRota, double pesoVolume) throws Exception;

	public abstract List<RotaVO> getInfoRotasCapacitadas(int idOrigem, int idDestino, double pesoVolume) throws Exception;

	public abstract List<LocalidadeVO> recuperarLocalidades() throws Exception;

}
