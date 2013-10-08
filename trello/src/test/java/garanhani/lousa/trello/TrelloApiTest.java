package garanhani.lousa.trello;

import garanhani.lousa.trello.TrelloApi;
import junit.framework.Assert;
import junit.framework.TestCase;

public class TrelloApiTest extends TestCase {
	public void testGetBoardIdByName() {
		TrelloApi trello = new TrelloApi();
		String boardId = trello.getBoardId(TrelloTest.BOARD_NAME, TrelloTest.ORGANIZATION);
		
		Assert.assertEquals(TrelloTest.BOARD_ID, boardId);
	}
}
