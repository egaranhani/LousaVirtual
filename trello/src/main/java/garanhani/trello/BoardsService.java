package garanhani.trello;

import java.util.List;



public interface BoardsService {
	public TrelloBoard getBoard(String id);
	
	public TrelloBoard getBoard(String orgName, String boardName);
	
	public List<TrelloBoard> getBoards(String orgName);
	
	static String boardCommand = "boards";
	static String listsCommand = "lists";
	static String cardsCommand = "cards";
}
