package garanhani.lousa.trello;

import java.util.List;

import garanhani.lousa.trello.TrelloBoard;
import garanhani.lousa.trello.TrelloListData;
import junit.framework.Assert;
import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONObject;

public class TrelloBoardTest extends TestCase {
	public void testGetAllListsFromABoard(){
		TrelloBoard board = new TrelloBoard();
		List<TrelloListData> allLists = board.getAllLists(TrelloTest.BOARD_ID);
		
		for (int i = 0; i < allLists.size(); i++) {
			Assert.assertEquals(listNames[i],allLists.get(i).name);
		}
	}
	
	public void testGetAListFromABoardByName(){
		TrelloBoard board = new TrelloBoard();
		TrelloListData list = board.getList(TrelloTest.BOARD_ID, TrelloTest.LIST_NAME);
		Assert.assertEquals(TrelloTest.LIST_ID, list.id);
	}

	static String [] listNames = {"To Do", "Doing", "Done"};
}
