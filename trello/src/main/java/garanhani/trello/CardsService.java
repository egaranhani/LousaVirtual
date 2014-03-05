package garanhani.trello;

import java.util.List;



public interface CardsService {
	public TrelloCard getCard(String id);
	
	public List<TrelloCard> getCards(TrelloList list);
	
	public List<TrelloCard> getCards(TrelloBoard board);
	
	public TrelloCard createCard(TrelloList list, String name, String description, String ... labels);
	
	public void addComment(TrelloCard card, String comment);
	
	static String cardCommand = "cards";
	static String commentsCommand = "actions/comments";
}
