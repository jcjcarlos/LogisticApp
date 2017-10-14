package LogisticApp.data.queries;

public enum RotaQueries {
	
	RETRIEVE_BY_ID ("SELECT id, nome, idLocalidadeOrigem, idLocalidadeDestino, capacidadeTotal, capacidadeAlocada, custoGrama, tempoEntrega, tipo FROM ROTA WHERE id = ?");
	
	private String consulta;
	
	private RotaQueries(String consulta){
		this.consulta = consulta;
	}
	
	public String getConsulta(){
		return this.consulta;
	}
}
