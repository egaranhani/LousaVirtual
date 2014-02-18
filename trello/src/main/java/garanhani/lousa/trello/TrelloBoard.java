package garanhani.lousa.trello;

import java.util.Arrays;
import java.util.List;

public class TrelloBoard extends TrelloClient {

	public TrelloBoard(){
		super();
	}
	
	public List<TrelloListData> getAllLists(String boardId) {
		String command = composeCommand(boardCommand, boardId, listsCommand);
		return Arrays.asList(getPrivate(TrelloListData[].class, command, (String[])null));
	}

	public List<TrelloCardData> getAllCards(String boardId) {
		String command = composeCommand(boardCommand, boardId, cardsCommand);
		return Arrays.asList(getPrivate(TrelloCardData[].class, command, (String[])null));
	}

	public TrelloListData getList(String boardId, String listName){
		List<TrelloListData> lists = getAllLists(boardId);
		for (TrelloListData list : lists) {
			if(list.name.equals(listName))
				return list;
		}
		return null;
	}
	
	private String boardCommand = "boards";
	private String listsCommand = "lists";
	private String cardsCommand = "cards";
}
