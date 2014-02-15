package garanhani.lousa.trello;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TrelloListTest extends TestCase {

	public void testGetAllCards() {
		TrelloList list = new TrelloList();
		List<TrelloCardData> allCards = list.getAllCards(TrelloTest.LIST_ID);
		
		Assert.assertEquals(1, allCards.size());
		
		TrelloCardData testCard = allCards.get(0);
		Assert.assertEquals("UnitTestCard", testCard.name);
    }
}
