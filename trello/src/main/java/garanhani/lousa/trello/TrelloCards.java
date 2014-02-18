package garanhani.lousa.trello;

public class TrelloCards extends TrelloClient {

	public TrelloCards() {
		super();
	}
	
	public TrelloCardData createCard(String listId, String name, String desc){
		return post(TrelloCardData.class, cardCommand, "name", name, "idList", listId, "desc", desc);
	}

	public void delete(TrelloCardData card){
		super.delete(cardCommand, card.id);
	}
	
	public void addComment(TrelloCardData card, String comment){
		String command = composeCommand(cardCommand, card.id, commentsCommand);
		post(TrelloCardData.class, command, "text", comment);
	}
	
	String cardCommand = "cards";
	String commentsCommand = "actions/comments";
}
