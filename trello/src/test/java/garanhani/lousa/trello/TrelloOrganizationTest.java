package garanhani.lousa.trello;

import java.util.List;

import garanhani.lousa.trello.TrelloBoardData;
import garanhani.lousa.trello.TrelloOrganization;
import junit.framework.Assert;
import junit.framework.TestCase;

import org.json.JSONArray;

public class TrelloOrganizationTest extends TestCase {
	public void testGetAllBoardsString(){
		TrelloOrganization org = new TrelloOrganization(TrelloTest.ORGANIZATION);
		String boards = org.getAllBoardsString();
		String expectedResponse = "[{\"id\":\"5212b4def3e8b8ae33000aea\",\"name\":\"UnitTestBoard\"";
		Assert.assertTrue(boards.startsWith(expectedResponse));
	}
	
	public void testGetAllBoardsJSON(){
		TrelloOrganization org = new TrelloOrganization(TrelloTest.ORGANIZATION);
		String boards = org.getAllBoardsString();
		JSONArray joArray = new JSONArray(boards);
		
		Assert.assertTrue(joArray != null);
	}
	
	public void testGetAllBoards(){
		TrelloOrganization org = new TrelloOrganization(TrelloTest.ORGANIZATION);
		List<TrelloBoardData> allBoards = org.getAllBoards();
		
		Assert.assertEquals(1, allBoards.size());
		Assert.assertEquals(TrelloTest.BOARD_NAME, allBoards.get(0).name());
		Assert.assertEquals(TrelloTest.BOARD_ID, allBoards.get(0).id());
		Assert.assertEquals(TrelloTest.BOARD_SHORT_URL, allBoards.get(0).shortUrl());
	}
	
	public void testGetBoardByName(){
		TrelloOrganization org = new TrelloOrganization(TrelloTest.ORGANIZATION);
		String searchName = TrelloTest.BOARD_NAME;
		TrelloBoardData board = org.getBoard(searchName);
		
		Assert.assertEquals(TrelloTest.BOARD_NAME, board.name());
		Assert.assertEquals(TrelloTest.BOARD_ID, board.id());
		Assert.assertEquals(TrelloTest.BOARD_SHORT_URL, board.shortUrl());
	}

}
