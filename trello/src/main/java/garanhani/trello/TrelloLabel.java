package garanhani.trello;

public class TrelloLabel {
	public String color;
	public String name;

	@Override
	public String toString() {
		return getClass().getSimpleName() + " - " + color + ": " + name;
	}
}
