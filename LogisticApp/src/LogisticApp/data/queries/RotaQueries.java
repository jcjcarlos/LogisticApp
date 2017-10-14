package LogisticApp.data.queries;

public enum RotaQueries {
	
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
	
	RETRIEVE_ALL ("SELECT id FROM ROTA"),
	
	RETRIEVE_TRECHOS_BY_ID ("SELECT idRotaTrecho"
					+ "FROM TRECHO "
					+ "WHERE idRotaFracionada = ?"
					+ "ORDER BY ordem"),
	
	RETRIEVE_BY_ORIGIN_DESTINY ("SELECT id "
			                  + "FROM ROTA "
			                  + "WHERE idLocalidadeOrigem = ? "
			                  + "AND idLocalidadeDestino = ?"),
	
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
	
	private RotaQueries(String consulta){
		this.consulta = consulta;
	}
	
	public String getConsulta(){
		return this.consulta;
	}
}
