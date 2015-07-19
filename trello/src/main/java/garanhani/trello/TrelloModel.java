package garanhani.trello;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class TrelloModel {
	public String id;
	
	public String toString(){
		return getClass().getSimpleName() + " - " + prettyName();
	}
	
	protected abstract String prettyName();

	public boolean equals(Object o){
		return id.equals(((TrelloModel)o).id);
	};
}
