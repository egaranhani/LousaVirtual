package garanhani.lousa.trello;

import java.io.IOException;

public class TrelloOrganization {

	public TrelloOrganization( String name ) {
		trello = new TrelloConnect("Lousa Virtual");
		orgName = name;
	}
	
	public void setOrganization( String name ) {
		orgName = name;
	}
	
	public String getAllBoards(){
		String command = trello.assembleCommand(orgCommand, orgName, allBoardsCommand);
		try {
			return trello.getPrivateData(command, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private TrelloConnect trello;
	private String orgCommand = "organizations";
	private String allBoardsCommand = "boards";
	private String orgName;
}
