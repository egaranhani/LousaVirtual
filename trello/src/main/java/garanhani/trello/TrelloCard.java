package garanhani.trello;

import java.util.List;

public class TrelloCard extends TrelloModel {

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

	public List<TrelloLabel> labels;

	public String name;

	public String pos;

	public String shortLink;

	public String shortUrl;

	public boolean subscribed;

	public String url;
	
	@Override
	protected String prettyName() {
		return name;
	}
}
