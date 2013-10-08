package garanhani.lousa.trello;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class TrelloConnect {
	private String appKey;
	private String authToken;
	private String trelloUrl;
	private String trelloAppName;

	public TrelloConnect(String appName) {
		this.trelloUrl = "https://api.trello.com/1/";
		this.appKey = "6aeb54de579573127eb37a135c84f0b9";
		this.authToken = "d5f79a22680c08fa03b70c9d1bcef035c7bdb7a911b99b14ed3757c81f201d27";
		this.trelloAppName = appName;
	}

	public String getAuthorizarion() throws IOException{
		// cria uma autorização (authToken) com permissão de read/write e que não expira
		String command = "authorize";
		String nameParam = "name=" + trelloAppName;
		String expirationParam = "expiration=never";
		String responseTypeParam = "response_type=token";
		String permissions = "scope=read,write";
		String [] params = {nameParam, expirationParam, responseTypeParam, permissions};
		return composePublicUrl(command, params);
	}
	
	private String getAppKey() {
		return appKey;
	}

	private String getBaseURL() {
		return trelloUrl;
	}
	
	private String getAuthToken() throws IOException{
		return authToken;
	}
	
	public String getPrivateCommand(String command){
		return getPrivateCommand(command, null);
	}

	public String getPrivateCommand(String command, String [] params){
		try {
			return get(composePrivateUrl(command, params));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String get(String url) throws IOException {
		URL urlApi = new URL(url);
		HttpsURLConnection trelloApi = (HttpsURLConnection) urlApi.openConnection();
		trelloApi.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(trelloApi.getInputStream()));
		String response = in.readLine();
		in.close();
		
		return response;
	}

	public String composePublicUrl(String command, String [] params) throws IOException{
		String url = getBaseURL() + command + "?key=" +  getAppKey();
		for (String param : (params==null ? new String [0] : params))
			url += ("&" + param);

		return url;
	}
	
	public String composePrivateUrl(String command, String [] params) throws IOException{
		String url = getBaseURL() + command + "?key=" +  getAppKey() + "&token=" + getAuthToken();
		for (String param : (params==null ? new String [0] : params))
			url += ("&" + param);

		return url;
	}

	public String post(String url, String urlParameters) throws IOException {
		URL urlApi = new URL(url);
		HttpsURLConnection trelloApi = (HttpsURLConnection) urlApi.openConnection();
		trelloApi.setRequestMethod("POST");
		
		trelloApi.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(trelloApi.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(trelloApi.getInputStream()));
		String response = in.readLine();
		in.close();

		return response;
	}
	
	public String assembleCommand(String ... commands) {
		String command = null;
		for (String partCommand : commands) {
			if(command == null)
				command = partCommand;
			else
				command += "/" + partCommand;
		}
		return command;
	}

}
