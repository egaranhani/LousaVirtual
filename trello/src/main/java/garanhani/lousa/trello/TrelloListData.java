package garanhani.lousa.trello;

import org.json.JSONObject;

public class TrelloListData {
	private String name;
	
	private static String NAME = "name";

	public TrelloListData( JSONObject jo ) {
		name = jo.getString(NAME);
	}
	
	public String name(){
		return name;
	}
}
