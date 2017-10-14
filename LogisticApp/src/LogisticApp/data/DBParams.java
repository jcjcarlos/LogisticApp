package LogisticApp.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DBParams {
	
	private static String URI = null;
	private static String USER = null;
	private static String PWD = null;
	
	private static BufferedReader openReader() throws IOException {
		return new BufferedReader(new FileReader("dbconfigs.txt"));
	}
	
	public static String getURI() throws IOException {
		if(URI == null){
			BufferedReader reader = DBParams.openReader();
			String line = reader.readLine().trim();
			while(line != null){
				if(line.startsWith("URI:")){
					URI = line.substring(4, line.length());
					break;
				}
				line = reader.readLine().trim();
			}
			reader.close();
		}
		return URI;
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

}
