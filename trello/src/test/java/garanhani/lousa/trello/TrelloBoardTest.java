package garanhani.lousa.trello;

import java.util.List;

import garanhani.lousa.trello.TrelloBoard;
import garanhani.lousa.trello.TrelloListData;
import junit.framework.Assert;
import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONObject;

public class TrelloBoardTest extends TestCase {
	public void testStringGetAllListsFromABoard(){
		TrelloBoard board = new TrelloBoard();
		String boardTest = board.getAllListsString(TrelloTest.BOARD_SHORT_URL);
		JSONArray ja = new JSONArray(boardTest);
		TrelloListData list = new TrelloListData((JSONObject) ja.get(0));
		
		Assert.assertEquals("To Do",list.name());
	}

	public void testJSONGetAllListsFromABoard(){
		TrelloBoard board = new TrelloBoard();
		JSONArray ja = board.getAllListsJSON(TrelloTest.BOARD_SHORT_URL);
		TrelloListData list = new TrelloListData((JSONObject) ja.get(0));
		
		Assert.assertEquals("To Do",list.name());
	}

	public void testGetAllListsFromABoard(){
		TrelloBoard board = new TrelloBoard();
		List<TrelloListData> allLists = board.getAllLists(TrelloTest.BOARD_SHORT_URL);
		
		for (int i = 0; i < allLists.size(); i++) {
			Assert.assertEquals(listNames[i],allLists.get(i).name());
		}
	}

	static String [] listNames = {"To Do", "Doing", "Done"};
}
