package garanhani.trello;

import java.util.List;

public class TrelloMember extends TrelloModel {
	
	String avatarHash;
	
	String bio;
	
	String bioData;
	
	boolean confirmed;
	
	String fullName;
	
	String idPremOrgsAdmin;
	
	String initials;
	
	String memberType;
	
	List<Integer> products;
	
	String status;
	
	String url;
	
	String username;
	
	String avatarSource;
	
	String email;
	
	String gravatarHash;
	
	List<String> idBoards;
	
	List<String> idBoardsInvited;
	
	List<String> idBoardsPinned;
	
	List<String> idOrganizations;
	
	List<String> idOrganizationsInvited;
	
	String loginTypes;
	
	String newEmail;
	
	String oneTimeMessagesDismissed;
	
	List<String> prefs;
	
	List<String> trophies;
	
	String uploadedAvatarHash;
	
	List<String> premiumFeatures;

	@Override
	protected String prettyName() {
		return username;
	}
}
