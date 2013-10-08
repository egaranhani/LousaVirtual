package garanhani.lousa.trello;

import org.json.JSONArray;
import org.json.JSONObject;

public class TrelloApi {
	public String getBoardId(String boardName, String orgName){
		TrelloOrganization trelloOrg = new TrelloOrganization(orgName);
		JSONArray allBoards = trelloOrg.getAllBoardsJSON();
		TrelloBoardData board = null;
		for(int i = 0; i < allBoards.length(); i++){
			board = new TrelloBoardData( (JSONObject) allBoards.get(i) );
			if(board.name().equals(boardName))
				break;
		}
		if(board==null)
			return null;
		return board.id();
	}
	
	public void createCard(String cardName, String boardName, String orgName){
		
	}
}
