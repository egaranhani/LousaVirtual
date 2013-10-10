package garanhani.lousa.trello;

import org.json.JSONObject;

public class TrelloListData {
	private static String NAME = "name";
	private static String ID = "id";

	private String name;
	private String id;
	
	public TrelloListData( JSONObject jo ) {
		id = jo.getString(ID);
		name = jo.getString(NAME);
	}
	
	public String name(){
		return name;
	}
	
	public String id(){
		return id;
	}
}
