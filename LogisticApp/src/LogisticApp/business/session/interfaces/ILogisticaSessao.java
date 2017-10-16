package LogisticApp.business.session.interfaces;

import java.util.Map;

public interface ILogisticaSessao {
	
	public void atualizarRota(int idRota) throws Exception;

	public Map<Integer, String> getInfoRotasCapacitadas() throws Exception;

}
