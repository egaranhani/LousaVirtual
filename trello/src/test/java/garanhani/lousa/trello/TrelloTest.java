package garanhani.lousa.trello;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class TrelloTest extends TestSuite {

	public TrelloTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        TestSuite testSuite = new TestSuite();
        testSuite.addTestSuite(TrelloOrganizationTest.class);
        testSuite.addTestSuite(TrelloBoardTest.class);
        testSuite.addTestSuite(TrelloListTest.class);
        testSuite.addTestSuite(TrelloCardTest.class);
        testSuite.addTestSuite(TrelloApiTest.class);
		return testSuite;
    }
    
	public static final String ORGANIZATION = "unittestorg";
	public static final String BOARD_NAME = "UnitTestBoard";
	public static final String BOARD_ID = "5212b4def3e8b8ae33000aea";
	public static final String BOARD_SHORT_URL = "https://trello.com/b/ywdXTuGe";
	public static final String LIST_NAME = "To Do";
	public static final String LIST_ID = "5212b4def3e8b8ae33000aeb";
	public static final String CARD_NAME = "UnitTestCard 2";
	public static final String CARD_DESCRIPTION = "UnitTestCardDesc";

}
