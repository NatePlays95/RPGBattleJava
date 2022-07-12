package src.managers;

import java.io.*;
import java.util.Vector;

public class DataManager {

	public static String searchCsvLine(String filepath, int searchColumnIndex, String searchString) {
		String resultRow = null;
		
		try{
			InputStream inputStream = DataManager.class.getResourceAsStream(filepath);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(inputStreamReader);
			String line;
			while ( (line = br.readLine()) != null ) {
					String[] values = line.split(",");
					if(values[searchColumnIndex].equals(searchString)) {
							resultRow = line;
							break;
					}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultRow;
	}

	public static String searchEnemy(String name){
		return searchCsvLine("/data/enemies.csv", 0, name);
	}

	public static Vector<String> getCsvEnemies(){
		Vector<String> retVec = new Vector<String>();
		try{
			InputStream inputStream = DataManager.class.getResourceAsStream("/data/enemies.csv");
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(inputStreamReader);
			String line; 
			int lineCount = 0;
			while ( (line = br.readLine()) != null ) {
				lineCount += 1;
				if (lineCount <= 2) continue; //skip header line and template line
				String[] values = line.split(",");
				retVec.add(values[0]);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVec;
	}
	

}