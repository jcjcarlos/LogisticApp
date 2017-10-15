package LogisticApp.data.queries;

public enum LocalidadeQueries {
	
	CREATE_LOCALIDADE ("INSERT INTO LOCALIDADE(id, descricao) "
				     + "VALUES (?, ?)"),
	
	RETRIEVE_ALL ("SELECT id, "
				+ "descricao "
				+ "FROM LOCALIDADE"),
	
	RETRIEVE_BY_ID ("SELECT id, "
			      + "descricao "
			      + "FROM LOCALIDADE "
			      + "WHERE id = ?"),
	
	UPDATE_LOCALIDADE ("UPDATE LOCALIDADE "
			         + "SET id = ?, "
			         + "descricao = ? "
			         + "WHERE id = ?");
	
	private String consulta;
	
	private LocalidadeQueries(String consulta) {
		this.consulta = consulta;
	}
	
	public String getConsulta() {
		return this.consulta;
	}
	
}
