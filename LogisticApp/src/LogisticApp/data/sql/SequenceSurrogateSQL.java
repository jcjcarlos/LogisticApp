package LogisticApp.data.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import LogisticApp.data.DBConnection;
import LogisticApp.data.interfaces.ISequenceSurrogate;
import LogisticApp.exception.CadastroException;

public class SequenceSurrogateSQL implements ISequenceSurrogate{
	
	private static String GENERATE = "SELECT NEXTVAL(?)";
	
	private static String RESTORE = "SELECT SETVAL(?, ?, false)";
	
	@Override
	public int generateKey(String sequenceName) throws Exception {
		PreparedStatement pstm = DBConnection.getConnection().prepareStatement(SequenceSurrogateSQL.GENERATE);
		pstm.setString(1, sequenceName);
		ResultSet rset = pstm.executeQuery();
		if(rset.next())
			return rset.getInt(1);
		throw new CadastroException("Erro ao gerar o ID do seu novo cadastro na base.");
	}
	
	@Override
	public void restoreKey(String sequenceName, int id) throws Exception {
		PreparedStatement pstm = DBConnection.getConnection().prepareStatement(SequenceSurrogateSQL.RESTORE);
		pstm.setString(1, sequenceName);
		pstm.setInt(2, id);
		pstm.executeQuery();
	}

}
