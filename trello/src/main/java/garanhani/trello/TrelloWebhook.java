package garanhani.trello;

public class TrelloWebhook {
	public String id;

	public String description;
	
	public String callbackURL;
	
	public String idModel;
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "{name:\"" + description + "\"}";
	}
	
	@Override
	public boolean equals(Object wh) {
		return id.equals(((TrelloWebhook)wh).id);
	}
}
