package garanhani.lousa.trello;


public class TrelloListData {
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
