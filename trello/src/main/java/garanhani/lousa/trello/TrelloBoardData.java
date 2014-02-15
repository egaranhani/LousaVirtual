package garanhani.lousa.trello;

import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloBoardData {
	public String id;

	public String name;

	public String desc;

	public String descData;

	public boolean closed;

	public String idOrganization;

	public String pinned;

	public String url;

	public String shortUrl;

//	public Prefs prefs;

//	public LabelNames labelNames;

	public boolean invited;

	public List<String> invitations;

//	public List<Membership> memberships;

	public String shortLink;

	public boolean subscribed;

	public List<String> powerUps;

	public String dateLastActivity;

	public String dateLastView;
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "{name:\"" + name + "\"}";
	}
}
