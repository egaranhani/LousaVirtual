package garanhani.lousa.trello;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class TrelloOrganization {

	public TrelloOrganization( String name ) {
		trello = new TrelloConnect("LousaVirtual");
		orgName = name;
	}
	
	public List<TrelloBoardData> getAllBoards(){
		JSONArray allBoards = new JSONArray(getAllBoardsString());
		List<TrelloBoardData> allBoardsData = new ArrayList<TrelloBoardData>();
		for(int i = 0; i < allBoards.length(); i++){
			allBoardsData.add( new TrelloBoardData(allBoards.getJSONObject(i)) );
		}
		return allBoardsData;
	}

	public TrelloBoardData getBoard(String searchName) {
		JSONArray allBoards = getAllBoardsJSON();
		for(int i = 0; i < allBoards.length(); i++){
			JSONObject board = allBoards.getJSONObject(i);
			TrelloBoardData boardData = new TrelloBoardData(board);
			if(boardData.name().equals(searchName))
				return boardData;
		}
		return null;
	}
	
	protected JSONArray getAllBoardsJSON(){
		JSONArray allBoards = new JSONArray(getAllBoardsString());
		return allBoards;
	}

	protected String getAllBoardsString(){
		String command = trello.assembleCommand(orgCommand, orgName, allBoardsCommand);
		try {
			return trello.get(trello.composePrivateUrl(command, null));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setOrganization( String name ) {
		orgName = name;
	}
	
	private TrelloConnect trello;
	private String orgCommand = "organizations";
	private String allBoardsCommand = "boards";
	private String orgName;
}
