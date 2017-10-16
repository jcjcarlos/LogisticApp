package LogisticApp.business.session.interfaces;

import java.util.Map;

public interface IContratacaoTransporteSession {
	
	public abstract void atualizarRota(int idRota, double pesoVolume) throws Exception;

	public abstract Map<Integer, String> getInfoRotasCapacitadas(int idOrigem, int idDestino, double pesoVolume) throws Exception;

}
