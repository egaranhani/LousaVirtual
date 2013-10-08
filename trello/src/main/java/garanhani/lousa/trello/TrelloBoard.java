package garanhani.lousa.trello;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

public class TrelloBoard {

	public TrelloBoard(){
		trello = new TrelloConnect("LousaVirtual");
	}
	
	public String getAllBoards() {
		String [] paramCommands = {allBoardsCommand};
		return trello.getPrivateCommand(boardCommand, paramCommands);
	}
	
	public List<TrelloListData> getAllLists(String boardShortUrl) {
		JSONArray listsJSON = getAllListsJSON(boardShortUrl);
		List<TrelloListData> lists = new ArrayList<TrelloListData>();
		for(int i = 0; i < listsJSON.length(); i++)
			lists.add(new TrelloListData(listsJSON.getJSONObject(i)));
		
		return lists;
	}

	public String getAllCardsString(String boardShortUrl){
		String command = boardCommand + "/" + boardShortUrl + "/" + cardsCommand;
		return trello.getPrivateCommand(command);
	}
	
	public String getAllListsString(String boardShortUrl){
		String command = boardCommand + "/" + boardShortUrl + "/" + listsCommand;
		return trello.getPrivateCommand(command);
	}
	
	public JSONArray getAllListsJSON(String boardShortUrl){
		JSONArray ja = new JSONArray(getAllListsString(boardShortUrl));
		return ja;
	}
	
	private TrelloConnect trello;
	private String boardCommand = "boards";
	private String allBoardsCommand = "open";
	private String listsCommand = "lists";
	private String cardsCommand = "cards";
}
