package garanhani.lousavirtual.trello;

import garanhani.lousa.trello.TrelloConnect;
import junit.framework.TestCase;

public class TrelloConnectTest extends TestCase {
	
    private String trelloPublicGet(String command) {
    	TrelloConnect test = TrelloTest.initializeTrello();
    	String response = null;
    	try {
    		response = test.getPublicDate(command, null);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return response;
    }
    
    private String trelloPrivateGet(String command) {
    	TrelloConnect test = TrelloTest.initializeTrello();
    	String response = null;
    	try {
    		response = test.getPrivateData(command, null);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return response;
    }
    
    public void testConnectionToPublicData() {
    	String response = trelloPublicGet("members/eduardogaranhani");
		assertTrue(response != null);
		
		String expectedResponseId = "{\"id\":\"4f38fa5f650b40235545a4d4\"";
		String expectedResponseName = "\"fullName\":\"Eduardo Garanhani\"";
		
		assertTrue(response.startsWith(expectedResponseId));
		assertTrue(response.contains(expectedResponseName));
    }

    public void testConnectionToPrivateData(){
    	String response = trelloPrivateGet("boards/ywdXTuGe");
		assertTrue(response != null);
		
		String expectedResponse = "{\"id\":\"5212b4def3e8b8ae33000aea\",\"name\":\"UnitTestBoard\"";
		assertTrue(response.startsWith(expectedResponse));
    }
    
}
