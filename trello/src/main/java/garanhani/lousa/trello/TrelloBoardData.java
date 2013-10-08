package garanhani.lousa.trello;

import org.json.JSONObject;

public class TrelloBoardData {
	private String id;
	private String name;
	private String shortUrl;
//	private String desc;
//	private String descData;
//	private Boolean closed;
//	private String idOrganization;

	static private String ID = "id";
	static private String NAME = "name";
//	static private String DESC = "desc";
//	static private String DESC_DATA = "descData";
//	static private String CLOSED = "closed";
//	static private String ID_ORGANIZATION = "idOrganization";
//	static private String INVITED = "invited";
//	static private String PINNED = "pinned";
//	static private String URL = "url";
//	static private String PREFS = "prefs";
//	static private String invitations;
//	static private String memberships;
	static private String SHORT_LINK = "shortLink";
//	static private String subscribed;
//	static private String labelNames;
//	static private String powerUps;
//	static private String dateLastActivity;
//	static private String dateLastView;
//	static private String shortUrl;

	public TrelloBoardData( JSONObject jo ){
		id = (String) jo.get(ID);
		name = (String) jo.get(NAME);
		shortUrl = (String) jo.get(SHORT_LINK);
//		desc = (String) jo.get(DESC);
//		descData = (String) jo.get(DESC_DATA);
//		closed = (Boolean) jo.get(CLOSED);
//		idOrganization = (String) jo.get(ID_ORGANIZATION);
	}
	
	public String name(){
		return name;
	}
	
	public String id(){
		return id;
	}

	public String shortUrl() {
		return shortUrl;
	}
}
