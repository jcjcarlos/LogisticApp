package LogisticApp.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DBParams {
	
	private static String DATABASE = null;
	private static String DRIVER = null;
	private static String HOST = null;
	private static String PORT = null;
	private static String PWD = null;
	private static String SGBD = null;
	private static String USER = null;
	
	private static String getDATABASE() throws IOException {
		if(DATABASE == null){
			BufferedReader reader = DBParams.openReader();
			String line = reader.readLine().trim();
			while(line != null){
				if(line.startsWith("DATABASE:")){
					DATABASE = line.substring(9, line.length());
					break;
				}
				line = reader.readLine().trim();
			}
			reader.close();
		}
		return DATABASE;
	}
	
	private static String getDRIVER() throws IOException {
		if(DRIVER == null){
			BufferedReader reader = DBParams.openReader();
			String line = reader.readLine().trim();
			while(line != null){
				if(line.startsWith("DRIVER:")){
					DRIVER = line.substring(7, line.length());
					break;
				}
				line = reader.readLine().trim();
			}
			reader.close();
		}
		return DRIVER;
	}

	private static String getHOST() throws IOException {
		if(HOST == null){
			BufferedReader reader = DBParams.openReader();
			String line = reader.readLine().trim();
			while(line != null){
				if(line.startsWith("HOST:")){
					HOST = line.substring(5, line.length());
					break;
				}
				line = reader.readLine().trim();
			}
			reader.close();
		}
		return HOST;
	}
	
	private static String getPORT() throws IOException {
		if(PORT == null){
			BufferedReader reader = DBParams.openReader();
			String line = reader.readLine().trim();
			while(line != null){
				if(line.startsWith("PORT:")){
					PORT = line.substring(5, line.length());
					break;
				}
				line = reader.readLine().trim();
			}
			reader.close();
		}
		return PORT;
	}
	
	public static String getPWD() throws IOException {
		if(PWD == null){
			BufferedReader reader = DBParams.openReader();
			String line = reader.readLine().trim();
			while(line != null){
				if(line.startsWith("PWD:")){
					PWD = line.substring(4, line.length());
					break;
				}
				line = reader.readLine().trim();
			}
			reader.close();
		}
		return PWD;
	}
	
	private static String getSGBD() throws IOException {
		if(SGBD == null){
			BufferedReader reader = DBParams.openReader();
			String line = reader.readLine().trim();
			while(line != null){
				if(line.startsWith("SGBD:")){
					SGBD = line.substring(5, line.length());
					break;
				}
				line = reader.readLine().trim();
			}
			reader.close();
		}
		return SGBD;
	}
	
	public static String getURI() throws IOException {
		return String.format("%1$s:%2$s://%3$s:%4$s/%5$s", DBParams.getDRIVER(),
				     				   DBParams.getSGBD(),
				     				   DBParams.getHOST(),
				     				   DBParams.getPORT(),
				     				   DBParams.getDATABASE());
	}

	public static String getUSER() throws IOException {
		if(USER == null){
			BufferedReader reader = DBParams.openReader();
			String line = reader.readLine().trim();
			while(line != null){
				if(line.startsWith("USER:")){
					USER = line.substring(5, line.length());
					break;
				}
				line = reader.readLine().trim();
			}
			reader.close();
		}
		return USER;
	}

	private static BufferedReader openReader() throws IOException {
		return new BufferedReader(new FileReader(System.getProperty("user.dir") + "/src/LogisticApp/data/dbconfigs.txt"));
	}

	private DBParams(){ }

}
