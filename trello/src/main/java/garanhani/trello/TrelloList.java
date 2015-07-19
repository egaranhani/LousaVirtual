package garanhani.trello;

public class TrelloList extends TrelloModel {
	
	public String name;
	
	public boolean closed;
	
	public String idBoard;
	
	public int pos;
	
	public boolean subscribed;
	
	@Override
	protected String prettyName() {
		return name;
	}
}
