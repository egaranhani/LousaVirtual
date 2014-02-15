package garanhani.lousa.trello;

import java.util.Arrays;
import java.util.List;

public class TrelloOrganization extends TrelloClient {

	public TrelloOrganization( String name ) {
		super();
		orgName = name;
	}
	
	public List<TrelloBoardData> getAllBoards(){
		String command = orgCommand + "/" + orgName + "/" + allBoardsCommand;
		return Arrays.asList(getPrivate(TrelloBoardData[].class, command, (String[])null));
	}
	
	public TrelloBoardData getBoard(String name){
		for (TrelloBoardData board : getAllBoards()) {
			if(board.name.equals(name))
				return board;
		}
		return null;
	}
	
	private String orgCommand = "organizations";
	private String allBoardsCommand = "boards";
	private String orgName;
}
