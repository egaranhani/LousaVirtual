package garanhani.lousa.trello;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	generateAuthentication();
        System.out.println( "Hello World!" );
    }
    
    private static void generateAuthentication(){
    	TrelloConnect trello = new TrelloConnect("LousaVirtual");
    	try {
			trello.get(trello.getAuthorizarion());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
