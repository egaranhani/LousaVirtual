package garanhani.trello;

import java.util.List;

public interface ListService {
	public TrelloList getList(String id);
	
	public TrelloList getList(TrelloBoard boad, String id);
	
	public List<TrelloList> getLists(TrelloBoard board);
	
	static String listCommand = "lists";
	static String allCardsCommand = "cards";
}
