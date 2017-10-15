package LogisticApp.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static Connection connection = null;
	
	public static Connection getConnection() throws SQLException, IOException {
		if (connection == null) {
			DriverManager.registerDriver(new org.postgresql.Driver());
			connection = DriverManager.getConnection(DBParams.getURI(), DBParams.getUSER(), DBParams.getPWD());
		}
		return connection;
	}

	private DBConnection(){ }

}
