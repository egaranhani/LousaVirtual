package garanhani.trello;

import garanhani.utils.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import com.sumersoft.jsonapihttpclient.DefaultJsonApiHttpClientFactory;
import com.sumersoft.jsonapihttpclient.JsonApiHttpClient;
import com.sumersoft.jsonapihttpclient.http.StatusCodeException;
import com.sumersoft.jsonapihttpclient.http.uri.DefaultUriBuilderHome;
import com.sumersoft.jsonapihttpclient.http.uri.UriBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

public class TrelloClient {
	public TrelloClient() {
		trelloUrl = "https://api.trello.com/1/";
		appKey = "6aeb54de579573127eb37a135c84f0b9";
//		authToken = "d5f79a22680c08fa03b70c9d1bcef035c7bdb7a911b99b14ed3757c81f201d27";
		authToken = "05dda0f56a0b3300f114e33ca0006f99201aa30b0793cd5cecabd077ae597224";
		jsonHttpClient = new DefaultJsonApiHttpClientFactory().create( trelloUrl );
	}
	
	public List<TrelloBoard> getAllBoards(String orgName){
		String command = TrelloOrganizationCommands.orgCommand + "/" + orgName + "/" + TrelloOrganizationCommands.allBoardsCommand;
		return Arrays.asList(getPrivate(TrelloBoard[].class, command, (String[])null));
	}
	
	public TrelloBoard getBoard(String orgName, String boardName){
		for (TrelloBoard board : getAllBoards(orgName))
			if(board.name.equals(boardName))
				return board;
		return null;
	}

	public TrelloBoard getBoard(String boardId) {
		String command = composeCommand(TrelloBoardCommands.boardCommand, boardId);
		return getPrivate(TrelloBoard.class, command, (String[])null);
	}

	public List<TrelloList> getAllLists(TrelloBoard board) {
		String command = TrelloBoardCommands.boardCommand + "/" + board.id + "/" + TrelloBoardCommands.listsCommand;
		return Arrays.asList(getPrivate(TrelloList[].class, command, (String[])null));
	}

	protected List<TrelloCard> getAllCards(TrelloList list) {
		String command = TrelloListCommands.listCommand + "/" + list.id + "/" + TrelloListCommands.allCardsCommand;
		return Arrays.asList(getPrivate(TrelloCard[].class, command, (String[])null));
	}
		
	public List<TrelloCard> getAllCards(TrelloBoard board) {
		String command = TrelloBoardCommands.boardCommand + "/" + board.id + "/" + TrelloBoardCommands.cardsCommand;
		return Arrays.asList(getPrivate(TrelloCard[].class, command, (String[])null));
	}

	public TrelloList getList(String listId){
		return getPrivate(TrelloList.class, TrelloListCommands.listCommand, (String[])null);
	}
	
	public TrelloList getList(TrelloBoard board, String listName){
		List<TrelloList> lists = getAllLists(board);
		for (TrelloList list : lists) {
			if(list.name.equals(listName))
				return list;
		}
		return null;
	}
	
	public TrelloCard createCard(TrelloList list, String name, String desc, String ... labels){
		if(labels == null || labels.length == 0)
			return post(TrelloCard.class, TrelloCardsCommands.cardCommand, "name", name, "idList", list.id, "desc", desc);
		return post(TrelloCard.class, TrelloCardsCommands.cardCommand, "name", name, "idList", list.id, "desc", desc, "labels", StringUtils.toStringCommaSeparated(labels));
	}

	public TrelloCard getCard(String id){
		return getPrivate(TrelloCard.class, composeCommand(TrelloCardsCommands.cardCommand, id));
	}
	
	public void deleteCard(TrelloCard card){
		delete(TrelloCardsCommands.cardCommand, card.id);
	}
	
	public void addComment(TrelloCard card, String comment){
		String command = composeCommand(TrelloCardsCommands.cardCommand, card.id, TrelloCardsCommands.commentsCommand);
		post(TrelloCard.class, command, "text", comment);
	}
	
	protected String composeCommand(String ... params){
		StringBuilder builder = new StringBuilder(params[0]);
		for (int i = 1; i < params.length; i++) {
			builder.append("/" + params[i]);
		}
		return builder.toString();
	}
	
	protected <T> T getPrivate( Class<T> t, String command, String ... args){
		int argsLenght = (args == null ? 0 : args.length);
		String [] getArgs = new String[argsLenght+4];
		getArgs[0] = "key";
		getArgs[1] = appKey;
		getArgs[2] = "token";
		getArgs[3] = authToken;
		for (int i = 0; i < argsLenght; i++) {
			getArgs[i+4] = args[0];
		}
		return jsonHttpClient.get(t, command, getArgs);
	}
	
	protected <T> T getPublic(Class<T> t, String command, String ... args){
		String [] getArgs = new String[args.length + 2];
		getArgs[0] = "key";
		getArgs[1] = appKey;
		for (int i = 0; i < args.length; i++) {
			getArgs[i+2] = args[0];
		}
		return jsonHttpClient.get(t, command, getArgs);
	}
	
	protected <T> T post(Class<T> t, String command, String ... args){
		String [] postArgs = new String[args.length+4];
		postArgs[0] = "key";
		postArgs[1] = appKey;
		postArgs[2] = "token";
		postArgs[3] = authToken;
		for (int i = 0; i < args.length; i++) {
			postArgs[i+4] = args[i];
		}
		return jsonHttpClient.postWithQueryParams(t, command, postArgs);
	}
	
	protected void delete(String command, String object){
		DefaultUriBuilderHome uriBuilderHome = new DefaultUriBuilderHome();
		String url = jsonHttpClient.getBaseUrl() + command + "/" + object;
		
	    UriBuilder uriBuilder = uriBuilderHome.create(url);
        uriBuilder.addParameter("key", this.appKey);
        uriBuilder.addParameter("token", this.authToken);

        URI uri = uriBuilder.build();
		HttpDelete deleteRequest = new HttpDelete(uri);
	    System.err.println("DELETE URL: " + uri);
	    
	    execute(deleteRequest);
	}
	
	private String execute(HttpUriRequest request)
	{
		try
		{
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(request, new BasicHttpContext());
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				return EntityUtils.toString(response.getEntity());
			}
			if (statusCode == 204) {
				return null;
			}
			throw new StatusCodeException(statusCode, EntityUtils.toString(response.getEntity()));
		}
		catch (ClientProtocolException e)
		{
			throw new RuntimeException(e);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	  
	protected final JsonApiHttpClient jsonHttpClient;
	private String appKey;
	private String authToken;
	private String trelloUrl;
}
