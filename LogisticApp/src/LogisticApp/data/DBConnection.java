package LogisticApp.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static final String URI = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PWD = "";
	
	private static Connection connection = null;
	
	public static Connection getConnection() throws SQLException {
		if(connection == null){
			DriverManager.registerDriver(new org.postgresql.Driver());
			connection = DriverManager.getConnection(URI, USER, PWD);
		}
		return connection;
	}

}
