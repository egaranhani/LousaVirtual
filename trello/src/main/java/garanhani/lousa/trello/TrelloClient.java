package garanhani.lousa.trello;

import com.sumersoft.jsonapihttpclient.DefaultJsonApiHttpClientFactory;
import com.sumersoft.jsonapihttpclient.JsonApiHttpClient;

public class TrelloClient {
	public TrelloClient() {
		trelloUrl = "https://api.trello.com/1/";
		appKey = "6aeb54de579573127eb37a135c84f0b9";
		authToken = "d5f79a22680c08fa03b70c9d1bcef035c7bdb7a911b99b14ed3757c81f201d27";
		jsonHttpClient = new DefaultJsonApiHttpClientFactory().create( trelloUrl );
	}
	
	public void setApplication(String appName){
		trelloAppName = appName;
	}
	
	protected <T> T getPrivate( Class<T> t, String command, String ... args){
		int argsLenght = (args == null ? 0 : args.length);
		String [] getArgs = new String[argsLenght+4];
		getArgs[0] = "key";
		getArgs[1] = appKey;
		getArgs[2] = "token";
		getArgs[3] = authToken;
		for (int i = 0; i < argsLenght; i++) {
			getArgs[i+4] = args[0];
		}
		return jsonHttpClient.get(t, command, getArgs);
	}
	
	protected <T> T getPublic( Class<T> t, String command, String ... args){
		String [] getArgs = new String[args.length + 2];
		getArgs[0] = "key";
		getArgs[1] = appKey;
		for (int i = 0; i < args.length; i++) {
			getArgs[i+2] = args[0];
		}
		return jsonHttpClient.get(t, command, getArgs);
	}
	
	protected final JsonApiHttpClient jsonHttpClient;
	private String appKey;
	private String authToken;
	private String trelloUrl;
	private String trelloAppName;

}
