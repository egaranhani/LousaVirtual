package garanhani.lousa.trello;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TrelloOrganizationTest extends TestCase {
	public void testGetAllBoards(){
		TrelloOrganization org = new TrelloOrganization(TrelloTest.ORGANIZATION);
		List<TrelloBoardData> allBoards = org.getAllBoards();
		
		Assert.assertEquals(1, allBoards.size());
		Assert.assertEquals(TrelloTest.BOARD_NAME, allBoards.get(0).name);
		Assert.assertEquals(TrelloTest.BOARD_ID, allBoards.get(0).id);
		Assert.assertEquals(TrelloTest.BOARD_SHORT_URL, allBoards.get(0).shortUrl);
	}
	
	public void testGetBoardByName(){
		TrelloOrganization org = new TrelloOrganization(TrelloTest.ORGANIZATION);
		String searchName = TrelloTest.BOARD_NAME;
		TrelloBoardData board = org.getBoard(searchName);
		
		Assert.assertEquals(TrelloTest.BOARD_NAME, board.name);
		Assert.assertEquals(TrelloTest.BOARD_ID, board.id);
		Assert.assertEquals(TrelloTest.BOARD_SHORT_URL, board.shortUrl);
	}

}
