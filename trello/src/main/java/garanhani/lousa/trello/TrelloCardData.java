package garanhani.lousa.trello;

import org.json.JSONObject;

public class TrelloCardData {
	private static String NAME = "name";
	private static String ID = "id";

	private String name;
	private String id;

	TrelloCardData(JSONObject card){
		id = card.getString(ID);
		name = card.getString(NAME);
	}

	public String name() {
		return name;
	}

	public String id() {
		return id;
	}
}
