package garanhani.trello;


public class TrelloList {
	public String id;
	
	public String name;
	
	public boolean closed;
	
	public String idBoard;
	
	public int pos;
	
	public boolean subscribed;
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "{name:\"" + name + "\"}";
	}
}
