package garanhani.lousa.trello;

import java.io.IOException;

public class TrelloBoard {

	public TrelloBoard(){
		trello = new TrelloConnect("Lousa Virtual");
	}
	
	public String get() {
		try {
			return trello.getPrivateData("boards/ywdXTuGe", null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getAllBoards() {
		String [] paramCommands = {allBoardsCommand};
		try {
			return trello.getPrivateData(boardCommad, paramCommands);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private TrelloConnect trello;
	private String boardCommad = "boards";
	private String allBoardsCommand = "open";

}
