package garanhani.lousavirtual.trello;

import org.json.JSONArray;
import org.json.JSONObject;

import garanhani.lousa.trello.TrelloBoard;
import garanhani.lousa.trello.TrelloOrganization;
import junit.framework.Assert;
import junit.framework.TestCase;

public class TrelloBoardTest extends TestCase {
	public void testReadBoard(){
		TrelloBoard board = new TrelloBoard();
		String boardTest = board.get();
		String expectedResponse = "{\"id\":\"5212b4def3e8b8ae33000aea\",\"name\":\"UnitTestBoard\"";
		Assert.assertTrue(boardTest.startsWith(expectedResponse));
	}
	
	public void testAllBoards(){
		TrelloOrganization org = new TrelloOrganization("unittestorg");
		String boards = org.getAllBoards();
		String expectedResponse = "[{\"id\":\"5212b4def3e8b8ae33000aea\",\"name\":\"UnitTestBoard\"";
		Assert.assertTrue(boards.startsWith(expectedResponse));
	}
	
	public void testAllBoardsJSON(){
//		TrelloOrganization org = new TrelloOrganization("unittestorg");
//		String boards = org.getAllBoards();
		JSONArray jo = new JSONArray("[{\"id\":\"5212b4def3e8b8ae33000aea\",\"name\":\"UnitTestBoard\"}]");
		Assert.assertTrue(jo != null);
	}
}
