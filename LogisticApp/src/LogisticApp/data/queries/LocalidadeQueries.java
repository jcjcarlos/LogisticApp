package LogisticApp.data.queries;

public enum LocalidadeQueries {
	
	CREATE_LOCALIDADE ("INSERT INTO LOCALIDADE(id, descricao) VALUES (?, ?)"),
	UPDATE_LOCALIDADE ("UPDATE LOCALIDADE SET id = ?, descricao = ? WHERE id = ?"),
	RETRIEVE_BY_DESCRICAO ("SELECT id, descricao FROM LOCALIDADE WHERE descricao = ?"),
	RETRIEVE_BY_ID ("SELECT id, descricao FROM LOCALIDADE WHERE id = ?"),
	DELETE_BY_ID ("DELETE FROM LOCALIDADE WHERE id = ?"),
	RETRIEVE_ALL ("SELECT id, descricao FROM LOCALIDADE"),
	DELETE_LOCALIDADE ("DELETE FROM LOCALIDADE WHERE id = ? AND descricao = ?");
	
	private String consulta;
	
	private LocalidadeQueries(String consulta) {
		this.consulta = consulta;
	}
	
	public String getConsulta() {
		return this.consulta;
	}
	
}
