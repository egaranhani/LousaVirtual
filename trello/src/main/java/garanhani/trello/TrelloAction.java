package garanhani.trello;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloAction extends TrelloModel {
	String id;
	
	String idMemberCreator;
	
	boolean member;
	
	TrelloActionData data;
	
	String type;
	
	String date;
	
	TrelloMember memberCreator;

	@Override
	protected String prettyName() {
		return data.prettyName();
	}
	
}

class TrelloActionData {
	TrelloBoard board;
	
	TrelloCard card;

	protected String prettyName() {
		String name = "";
		if(board != null)
			name += ("Board: " + board.prettyName());
		
		if(card != null){
			if(name.length() > 0)
				name += " ";
			name += ("Card " + card.prettyName());
		}
		
		return name;
	}
}