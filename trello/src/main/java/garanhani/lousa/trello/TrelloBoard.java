package garanhani.lousa.trello;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

public class TrelloBoard {

	public TrelloBoard( String id ){
		trello = new TrelloConnect("LousaVirtual");
		boardId = id;
	}
	
	public String getAllBoards() {
		String [] paramCommands = {allBoardsCommand};
		return trello.getPrivateCommand(boardCommand, paramCommands);
	}
	
	public List<TrelloListData> getAllLists() {
		JSONArray listsJSON = getAllListsJSON();
		List<TrelloListData> lists = new ArrayList<TrelloListData>();
		for(int i = 0; i < listsJSON.length(); i++)
			lists.add(new TrelloListData(listsJSON.getJSONObject(i)));
		
		return lists;
	}

	public TrelloListData getList(String listName) {
		List<TrelloListData> allLists = getAllLists();
		for (TrelloListData list : allLists) {
			if(list.name().equals(listName))
				return list;
		}
		return null;
	}

	protected String getAllCardsString(){
		String command = boardCommand + "/" + boardId + "/" + cardsCommand;
		return trello.getPrivateCommand(command);
	}
	
	protected String getAllListsString(){
		String command = boardCommand + "/" + boardId + "/" + listsCommand;
		return trello.getPrivateCommand(command);
	}
	
	protected JSONArray getAllListsJSON(){
		JSONArray ja = new JSONArray(getAllListsString());
		return ja;
	}
	
	public JSONArray getAllCardsJSON() {
		return new JSONArray(getAllCardsString());
	}

	private TrelloConnect trello;
	private String boardCommand = "boards";
	private String allBoardsCommand = "open";
	private String listsCommand = "lists";
	private String cardsCommand = "cards";
	private String boardId;
}
