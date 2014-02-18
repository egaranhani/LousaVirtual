package garanhani.lousa.trello;

import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloCardData {
	public String id;

//	public Badge badges;

	public List<String> checkItemStates;

	public boolean closed;

	public String dateLastActivity;

	public String desc;

//	public DescData descData;

	public String due;

	public String idBoard;

	public List<String> idChecklists;

	public String idList;

	public List<String> idMembers;

	public List<String> idMembersVoted;

	public String idShort;

	public String idAttachmentCover;

	public String manualCoverAttachment;

//	public List<Label> labels;

	public String name;

	public String pos;

	public String shortLink;

	public String shortUrl;

	public boolean subscribed;

	public String url;
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "{name:\"" + name + "\"}";
	}
	
	@Override
	public boolean equals(Object card){
		return this.id.equals(((TrelloCardData)card).id);
	}
}
