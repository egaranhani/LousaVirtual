package garanhani.trello;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloWebhook {
	public String id;

	public String description;
	
	public String callbackURL;
	
	public String idModel;
	
	public boolean active;
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "{name:\"" + description + "\"}";
	}
	
	@Override
	public boolean equals(Object wh) {
		return id.equals(((TrelloWebhook)wh).id);
	}
}
