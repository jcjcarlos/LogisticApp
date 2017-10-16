package LogisticApp.business.session.interfaces;

import java.util.Map;

public interface IContratacaoTransporteSession {
	
	public abstract void atualizarRota(int idRota) throws Exception;

	public abstract Map<Integer, String> getInfoRotasCapacitadas() throws Exception;

}
