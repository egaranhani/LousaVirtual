package garanhani.lousa.trello;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TrelloCardTest extends TestCase {
	public void testCreateCard(){
		TrelloCards cardClient = new TrelloCards();
		TrelloCardData card = cardClient.createCard(TrelloTest.LIST_ID, TrelloTest.CARD_NAME, TrelloTest.CARD_DESCRIPTION);
		Assert.assertEquals(TrelloTest.CARD_NAME, card.name);
		Assert.assertEquals(TrelloTest.CARD_DESCRIPTION, card.desc);
		
		TrelloList listClient = new TrelloList();
		List<TrelloCardData> allCards = listClient.getAllCards(TrelloTest.LIST_ID);
		Assert.assertEquals(2, allCards.size());
		Assert.assertEquals(true,allCards.contains(card));
	}
	
	public void testDeleteCard(){
		TrelloList listClient = new TrelloList();
		List<TrelloCardData> allCards = listClient.getAllCards(TrelloTest.LIST_ID);
		Assert.assertEquals(2, allCards.size());
		
		TrelloCardData card = allCards.get(1);
		Assert.assertEquals(card,card);

		TrelloCards cardClient = new TrelloCards();
		cardClient.delete(card);
		
		allCards = listClient.getAllCards(TrelloTest.LIST_ID);
		Assert.assertEquals(1, allCards.size());
	}
	
	public void testAddCommentToACard(){
		TrelloList listClient = new TrelloList();
		List<TrelloCardData> allCards = listClient.getAllCards(TrelloTest.LIST_ID);
		Assert.assertEquals(1, allCards.size());
		
		TrelloCardData card = allCards.get(0);
		
		TrelloCards cardClient = new TrelloCards();
		cardClient.addComment(card, "UnitTestComment");
		
		allCards = listClient.getAllCards(TrelloTest.LIST_ID);
		Assert.assertEquals(1, allCards.size());
	}
	
}
