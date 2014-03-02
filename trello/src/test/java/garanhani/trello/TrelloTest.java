package garanhani.trello;

import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
class TrelloTest extends TestSuite {

	public TrelloTest( String testName ) {
        super( testName );
    }

	static final String ORGANIZATION = "unittestorg";
	static final String BOARD_NAME = "UnitTestBoard";
	static final String BOARD_ID = "5212b4def3e8b8ae33000aea";
	static final String BOARD_SHORT_URL = "https://trello.com/b/ywdXTuGe";
	static final String LIST_NAME = "To Do";
	static final String LIST_ID = "5212b4def3e8b8ae33000aeb";
	static final String EXISTING_CARD_ID = "5308fc5805e4c6337fdc395d";
	static final String EXISTING_CARD_NAME = "UnitTestCard";
	static final String EXISTING_CARD_DESC = "";
	static final String CARD_NAME = "UnitTestCard 2";
	static final String CARD_DESCRIPTION = "UnitTestCardDesc";
	static final String [] LIST_NAMES = {"To Do", "Doing", "Done"};

}
