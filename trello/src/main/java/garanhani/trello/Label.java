package garanhani.trello;

public class Label {
	public String color;
	public String name;
	
	@Override
	public boolean equals(Object obj) {
		return 
			color.equals(((Label)obj).color) && 
			name.equals(((Label)obj).name);
	}
}
