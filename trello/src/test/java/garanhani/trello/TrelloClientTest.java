package garanhani.trello;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TrelloClientTest extends TestCase{
	public void testGetAllBoards(){
		List<TrelloBoard> allBoards = trello.getAllBoards(TrelloTest.ORGANIZATION);
		
		Assert.assertEquals(1, allBoards.size());
		Assert.assertEquals(TrelloTest.BOARD_NAME, allBoards.get(0).name);
		Assert.assertEquals(TrelloTest.BOARD_ID, allBoards.get(0).id);
		Assert.assertEquals(TrelloTest.BOARD_SHORT_URL, allBoards.get(0).shortUrl);
	}
	
	public void testGetBoardByName(){
		TrelloBoard board = trello.getBoard(TrelloTest.ORGANIZATION, TrelloTest.BOARD_NAME);
		
		Assert.assertEquals(TrelloTest.BOARD_NAME, board.name);
		Assert.assertEquals(TrelloTest.BOARD_ID, board.id);
		Assert.assertEquals(TrelloTest.BOARD_SHORT_URL, board.shortUrl);
	}
	
	public void testGetBoardById(){
		TrelloBoard board = trello.getBoard(TrelloTest.BOARD_ID);
		
		Assert.assertEquals(TrelloTest.BOARD_NAME, board.name);
		Assert.assertEquals(TrelloTest.BOARD_ID, board.id);
		Assert.assertEquals(TrelloTest.BOARD_SHORT_URL, board.shortUrl);
	}
	
	public void testGetAllListsFromABoard(){
		TrelloBoard board = trello.getBoard(TrelloTest.BOARD_ID);
		List<TrelloList> allLists = trello.getAllLists(board);
		
		for (int i = 0; i < allLists.size(); i++) {
			Assert.assertEquals(TrelloTest.LIST_NAMES[i],allLists.get(i).name);
		}
	}
	
	public void testGetAllCardsFromAList() {
		TrelloBoard board = trello.getBoard(TrelloTest.BOARD_ID);
		TrelloList list = trello.getList(board, TrelloTest.LIST_NAME);
		List<TrelloCard> allCards = trello.getAllCards(list);
		
		Assert.assertEquals(1, allCards.size());
		
		TrelloCard testCard = allCards.get(0);
		Assert.assertEquals("UnitTestCard", testCard.name);
    }

	public void testGetAListFromABoardByName(){
		TrelloBoard board = trello.getBoard(TrelloTest.BOARD_ID);
		TrelloList list = trello.getList(board, TrelloTest.LIST_NAME);
		Assert.assertEquals(TrelloTest.LIST_ID, list.id);
	}

	public void testCreateCardWithNoLabels(){
		TrelloCard card = trello.createCard(listTest(), TrelloTest.CARD_NAME, TrelloTest.CARD_DESCRIPTION, (String[])null);
		Assert.assertEquals(TrelloTest.CARD_NAME, card.name);
		Assert.assertEquals(TrelloTest.CARD_DESCRIPTION, card.desc);

		TrelloBoard board = new TrelloBoard();
		board.id = TrelloTest.BOARD_ID;
		
		List<TrelloCard> allCards = trello.getAllCards(listTest());
		Assert.assertEquals(2, allCards.size());
		Assert.assertEquals(true,allCards.contains(card));

		deleteCard();
	}

	private TrelloList listTest() {
		TrelloList list = new TrelloList();
		list.id = TrelloTest.LIST_ID;
		return list;
	}

	public void testGetCardById(){
		TrelloCard card = trello.getCard(TrelloTest.EXISTING_CARD_ID);

		Assert.assertEquals(TrelloTest.EXISTING_CARD_NAME, card.name);
		Assert.assertEquals(TrelloTest.EXISTING_CARD_DESC, card.desc);
		Assert.assertEquals(0, card.labels.size());
	}

	public void testCreateCardWithRedLabel(){
		TrelloCard card = trello.createCard(listTest(), TrelloTest.CARD_NAME, TrelloTest.CARD_DESCRIPTION, "red");
		Assert.assertEquals(TrelloTest.CARD_NAME, card.name);
		Assert.assertEquals(TrelloTest.CARD_DESCRIPTION, card.desc);

		card = trello.getCard(card.id);
		Assert.assertEquals(TrelloTest.CARD_NAME, card.name);
		Assert.assertEquals(TrelloTest.CARD_DESCRIPTION, card.desc);
		Assert.assertEquals("red", card.labels.get(0).color);

		deleteCard();
	}

	public void testCreateCardWithRedAndBlueLabel(){
		TrelloCard card = trello.createCard(listTest(), TrelloTest.CARD_NAME, TrelloTest.CARD_DESCRIPTION, "red", "blue");
		Assert.assertEquals(TrelloTest.CARD_NAME, card.name);
		Assert.assertEquals(TrelloTest.CARD_DESCRIPTION, card.desc);

		List<TrelloCard> allCards = trello.getAllCards(listTest());
		Assert.assertEquals(2, allCards.size());
		Assert.assertEquals(true,allCards.contains(card));

		for (TrelloCard cardData : allCards) {
			if(card.name.equals(TrelloTest.CARD_NAME)){
				cardData.labels.contains("red");
				cardData.labels.contains("blue");
			}
		}
		deleteCard();
	}

	private void deleteCard(){
		List<TrelloCard> allCards = trello.getAllCards(listTest());
		Assert.assertEquals(2, allCards.size());

		TrelloCard card = allCards.get(1);
		Assert.assertEquals(card,card);

		trello.deleteCard(card);

		allCards = trello.getAllCards(listTest());
		Assert.assertEquals(1, allCards.size());
	}

	public void testAddCommentToACard(){
		List<TrelloCard> allCards = trello.getAllCards(listTest());
		Assert.assertEquals(1, allCards.size());

		TrelloCard card = allCards.get(0);

		trello.addComment(card, "UnitTestComment");

		allCards = trello.getAllCards(listTest());
		Assert.assertEquals(1, allCards.size());
	}

	TrelloClient trello = new TrelloClient();
	
}