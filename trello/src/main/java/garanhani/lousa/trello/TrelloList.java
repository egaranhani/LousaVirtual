package garanhani.lousa.trello;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;


public class TrelloList {
	
	public TrelloList(String id) {
		trello = new TrelloConnect("LousaVirtual");
		listId = id;
	}

	protected String getAllCardsString() {
		String command = listCommand + "/" + listId + "/" + allCardsCommand;
		return trello.getPrivateCommand(command, null);
	}
	
	protected JSONArray getAllCardsJSON() {
		return new JSONArray(getAllCardsString());
	}
	
	public List<TrelloCardData> getAllCards(){
		List<TrelloCardData> allCards = new ArrayList<TrelloCardData>();
		JSONArray arr = getAllCardsJSON();
		for(int i = 0; i < arr.length(); i++){
			allCards.add(new TrelloCardData(arr.getJSONObject(i)));
		}
		return allCards;
	}
	
	private String listCommand = "lists";
	private String allCardsCommand = "cards";
	private String listId;
	private TrelloConnect trello;
}
