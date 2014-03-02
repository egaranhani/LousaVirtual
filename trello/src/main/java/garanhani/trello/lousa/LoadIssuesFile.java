package garanhani.trello.lousa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoadIssuesFile {
	private FileReader file;
	private BufferedReader reader;

	LoadIssuesFile(String filePath){
		try {
			file = new FileReader(filePath);
			reader = new BufferedReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			file = null;
		}
	}

	public FileContent nextData(){
		try {
			FileContent data = new FileContent();
			String line = reader.readLine();
			
			if(line == null)
				return null;

			data.setData(line);
			return data;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
