package LogisticApp.business.session.interfaces;

import java.util.Collection;
import java.util.List;

import LogisticApp.exception.LogisticException;
import LogisticApp.view.vo.LocalidadeVO;
import LogisticApp.view.vo.RotaCapacitadaVO;

public interface IContratacaoTransporteSession {

	public abstract void atualizarRota(int idRota, double pesoVolume) throws LogisticException;

	public abstract Collection<RotaCapacitadaVO> getInfoRotasCapacitadas(int idOrigem, int idDestino, double pesoVolume)
			throws Exception;

	public abstract List<LocalidadeVO> recuperarLocalidades() throws Exception;

}
