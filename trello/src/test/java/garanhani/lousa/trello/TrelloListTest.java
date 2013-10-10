package garanhani.lousa.trello;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TrelloListTest extends TestCase {

	public void testGetAllCardsString() {
		TrelloList list = new TrelloList(TrelloTest.LIST_ID);
		String allCardsString = list.getAllCardsString();
		String expected = "[{\"id\":\"5240f49b675605852c0070c5\",\"badges\":{\"votes\":0,\"viewingMemberVoted\"";
		Assert.assertTrue(allCardsString.startsWith(expected));
    }

	public void testGetAllCardsJSON() {
		TrelloList list = new TrelloList(TrelloTest.LIST_ID);
		JSONArray arr = list.getAllCardsJSON();
		
		Assert.assertEquals(1, arr.length());
		
		JSONObject jo = arr.getJSONObject(0);
		Assert.assertEquals("UnitTestCard", jo.getString("name"));
    }

	public void testGetAllCards() {
		TrelloList list = new TrelloList(TrelloTest.LIST_ID);
		List<TrelloListData> allCards = list.getAllCards();
		
		Assert.assertEquals(1, allCards.size());
		
		TrelloListData testCard = allCards.get(0);
		Assert.assertEquals("UnitTestCard", testCard.name());
    }
}
