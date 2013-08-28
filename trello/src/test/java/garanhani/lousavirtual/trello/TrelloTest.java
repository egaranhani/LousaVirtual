package garanhani.lousavirtual.trello;

import garanhani.lousa.trello.TrelloConnect;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class TrelloTest extends TestSuite {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TrelloTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        TestSuite testSuite = new TestSuite( TrelloConnectTest.class );
        testSuite.addTestSuite( TrelloBoardTest.class );
		return testSuite;
    }
    
    public static TrelloConnect initializeTrello() {
    	return trelloConect == null ? trelloConect = new TrelloConnect("Lousa Virtual") : trelloConect;
    }

    private static TrelloConnect trelloConect;
}
