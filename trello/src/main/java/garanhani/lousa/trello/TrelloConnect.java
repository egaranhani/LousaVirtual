package garanhani.lousa.trello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class TrelloConnect {
	private String appKey;
	private String authToken;
//	private String appSecret;
	private String trelloUrl;
	private String trelloAppName;

	public TrelloConnect(String appName) {
		this.trelloUrl = "https://api.trello.com/1/";
		this.appKey = "6aeb54de579573127eb37a135c84f0b9";
//		this.appSecret = "bd4e8d0153b948e3829cf6dd03d047994bc748f668abe368d21352740df1b8f7";
		this.authToken = "236c345673f5f3d13e166d690e5324d9c103e998c596e8f4d7ac6aa7b2713b0e";
		this.trelloAppName = appName;
	}

	private String getAuthorizarion() throws IOException{
		String command = "authorize";
		String nameParam = "name=" + trelloAppName;
		String expirationParam = "expiration=never";
		String responseTypeParam = "response_type=token";
		String [] params = {nameParam, expirationParam, responseTypeParam};
		return getPublicDate(command, params);
	}

	private String getAppKey() {
		return appKey;
	}

	private String getBaseURL() {
		return trelloUrl;
	}
	
	private String getAuthToken() throws IOException{
		return authToken == null ? authToken = getAuthorizarion() : authToken;
	}
	
	private String get(String url) throws MalformedURLException,
	IOException {
		URL trelloApi = new URL(url);
		BufferedReader in = new BufferedReader(new InputStreamReader(trelloApi.openStream()));
		String inputLine = in.readLine();
		in.close();
		return inputLine;
	}

	public String getPublicDate(String command, String [] params) throws IOException{
		String url = getBaseURL() + command + "?key=" +  getAppKey();
		for (String param : (params==null ? new String [0] : params))
			url += ("&" + param);

		return get(url);
	}
	
	public String getPrivateData(String command, String [] params) throws IOException{
		String url = getBaseURL() + command + "?key=" +  getAppKey() + "&token=" + getAuthToken();
		for (String param : (params==null ? new String [0] : params))
			url += ("&" + param);

		return get(url);
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
