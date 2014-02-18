package garanhani.lousa.trello.lousa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoadIssuesFile {
	private FileReader file;

	LoadIssuesFile(String filePath){
		try {
			file = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			file = null;
		}
	}

	public FileContent nextData(){
		try {
			FileContent data = new FileContent();
			BufferedReader reader = new BufferedReader(file);
			String line = reader.readLine();
			if(line != null){
				data.setData(line);
			}
			return data;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
