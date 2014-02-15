package garanhani.lousa.trello;

import java.util.Arrays;
import java.util.List;


public class TrelloList extends TrelloClient {
	
	public TrelloList() {
		super();
	}

	protected List<TrelloCardData> getAllCards( String listId ) {
		String command = listCommand + "/" + listId + "/" + allCardsCommand;
		return Arrays.asList(getPrivate(TrelloCardData[].class, command, (String[])null));
	}
		
	private String listCommand = "lists";
	private String allCardsCommand = "cards";
}
