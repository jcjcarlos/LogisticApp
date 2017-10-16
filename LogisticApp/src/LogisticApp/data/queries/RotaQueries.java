package LogisticApp.data.queries;

public enum RotaQueries {
	
	INSERT_ROTA ("INSERT INTO ROTA(id, "
								+ "nome, "
								+ "idLocalidadeOrigem, "
								+ "idLocalidadeDestino, "
								+ "capacidadeTotal, "
								+ "capacidadeAlocada, "
								+ "custoGrama, "
								+ "tempoEntrega, "
								+ "tipo) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"),
	
	INSERT_TRECHO ("INSERT INTO TRECHO(idRotaFracionada, "
									+ "idRotaTrecho, "
									+ "ordem) "
				 + "VALUES (?, ?, ?)"),
	
	RETRIEVE_ALL ("SELECT id FROM ROTA"),
	
	RETRIEVE_BY_ID ("SELECT id, "
				  + "nome, "
				  + "idLocalidadeOrigem, "
				  + "idLocalidadeDestino, "
				  + "capacidadeTotal, "
				  + "capacidadeAlocada, "
				  + "custoGrama, "
				  + "tempoEntrega, "
				  + "tipo "
				  + "FROM ROTA WHERE id = ?"),
	
	RETRIEVE_BY_NAME ("SELECT id, "
			+ "nome, "
			+ "idLocalidadeOrigem, "
			+ "idLocalidadeDestino, "
			+ "capacidadeTotal, "
			+ "capacidadeAlocada,"
			+ " custoGrama, "
			+ "tempoEntrega, "
			+ "tipo "
			+ "FROM ROTA "
			+ "WHERE nome = ?"),
	
	RETRIEVE_BY_ORIGIN_DESTINY ("SELECT id "
			                  + "FROM ROTA "
			                  + "WHERE idLocalidadeOrigem = ? "
			                  + "AND idLocalidadeDestino = ?"),
	
	RETRIEVE_TRECHOS_BY_ID ("SELECT idRotaTrecho"
					+ "FROM TRECHO "
					+ "WHERE idRotaFracionada = ?"
					+ "ORDER BY ordem"),
	
	UPDATE_ROTA ("UPDATE ROTA "
			   + "SET id = ?, "
			   + "nome = ?, "
			   + "idLocalidadeOrigem = ?, "
			   + "idLocalidadeDestino = ?,"
			   + "capacidadeTotal = ?,"
			   + "capacidadeAlocada = ?,"
			   + "custoGrama = ?,"
			   + "tempoEntrega = ?,"
			   + "tipo = ?"
			   + "WHERE id = ?"),
	
	UPDATE_TRECHO ("UPDATE TRECHO"
			     + "SET idRotaFracionada = ?,"
			     + "idRotaTrecho = ?,"
			     + "ordem = ?"
			     + "WHERE idRotaFracionada = ?"
			     + "AND ordem = ?");

	private String consulta;

	private RotaQueries(String consulta) {
		this.consulta = consulta;
	}

	public String getConsulta() {
		return this.consulta;
	}
}
