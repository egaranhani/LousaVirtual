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
		TrelloBoard board = new TrelloBoard(TrelloTest.BOARD_ID);
		String boardTest = board.getAllListsString();
		JSONArray ja = new JSONArray(boardTest);
		TrelloListData list = new TrelloListData((JSONObject) ja.get(0));
		
		Assert.assertEquals("To Do",list.name());
	}

	public void testJSONGetAllListsFromABoard(){
		TrelloBoard board = new TrelloBoard(TrelloTest.BOARD_ID);
		JSONArray ja = board.getAllListsJSON();
		TrelloListData list = new TrelloListData((JSONObject) ja.get(0));
		
		Assert.assertEquals("To Do",list.name());
	}

	public void testGetAllListsFromABoard(){
		TrelloBoard board = new TrelloBoard(TrelloTest.BOARD_ID);
		List<TrelloListData> allLists = board.getAllLists();
		
		for (int i = 0; i < allLists.size(); i++) {
			Assert.assertEquals(listNames[i],allLists.get(i).name());
		}
	}
	
	public void testGetListByName(){
		TrelloBoard board = new TrelloBoard(TrelloTest.BOARD_ID);
		TrelloListData list = board.getList(TrelloTest.LIST_NAME);
		Assert.assertEquals(TrelloTest.LIST_ID, list.id());
	}

	public void testGetAllCardsString(){
		TrelloBoard board = new TrelloBoard(TrelloTest.BOARD_ID);
		String allCards = board.getAllCardsString();
		String expected = "[{\"id\":\"5240f49b675605852c0070c5\",\"badges\":{\"votes\"";
		Assert.assertTrue(allCards.startsWith(expected));
	}

	public void testGetAllCardsJSON(){
		TrelloBoard board = new TrelloBoard(TrelloTest.BOARD_ID);
		JSONArray ja = board.getAllCardsJSON();
		JSONObject card = ja.getJSONObject(0);
		
		Assert.assertEquals(1,ja.length());
		Assert.assertEquals("UnitTestCard",card.getString("name"));
	}

	static String [] listNames = {"To Do", "Doing", "Done"};
}
