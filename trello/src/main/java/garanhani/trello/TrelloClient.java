package garanhani.trello;

import garanhani.utils.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

import com.sumersoft.jsonapihttpclient.DefaultJsonApiHttpClientFactory;
import com.sumersoft.jsonapihttpclient.JsonApiHttpClient;
import com.sumersoft.jsonapihttpclient.http.StatusCodeException;
import com.sumersoft.jsonapihttpclient.http.uri.DefaultUriBuilderHome;
import com.sumersoft.jsonapihttpclient.http.uri.UriBuilder;
import com.sumersoft.jsonapihttpclient.json.JsonDeserializer;

public class TrelloClient implements BoardsService, CardsService, ListService, OrganizationService, WebhookService {
	public TrelloClient() {
		trelloUrl = "https://api.trello.com/1/";
		appKey = "6aeb54de579573127eb37a135c84f0b9";
//		authToken = "d5f79a22680c08fa03b70c9d1bc ef035c7bdb7a911b99b14ed3757c81f201d27";
		authToken = "05dda0f56a0b3300f114e33ca0006f99201aa30b0793cd5cecabd077ae597224";
		jsonHttpClient = new DefaultJsonApiHttpClientFactory().create( trelloUrl );
		jsonDeserializer = new JsonDeserializer();
	}
	
	public List<TrelloBoard> getBoards(String orgName){
		String command = composeCommand(OrganizationService.orgCommand, orgName, OrganizationService.allBoardsCommand);
		return Arrays.asList(getPrivate(TrelloBoard[].class, command, (String[])null));
	}
	
	public TrelloBoard getBoard(String orgName, String boardName){
		for (TrelloBoard board : getBoards(orgName))
			if(board.name.equals(boardName))
				return board;
		return null;
	}

	public TrelloBoard getBoard(String boardId) {
		String command = composeCommand(BoardsService.boardCommand, boardId);
		return getPrivate(TrelloBoard.class, command, (String[])null);
	}

	public List<TrelloList> getLists(TrelloBoard board) {
		String command = composeCommand(BoardsService.boardCommand, board.id, BoardsService.listsCommand);
		return Arrays.asList(getPrivate(TrelloList[].class, command, (String[])null));
	}

	public List<TrelloCard> getCards(TrelloList list) {
		String command = composeCommand(ListService.listCommand, list.id, ListService.allCardsCommand);
		return Arrays.asList(getPrivate(TrelloCard[].class, command, (String[])null));
	}

	public List<TrelloCard> getCards(TrelloBoard board) {
		String command = composeCommand(BoardsService.boardCommand, board.id, BoardsService.cardsCommand);
		return Arrays.asList(getPrivate(TrelloCard[].class, command, (String[])null));
	}

	public TrelloList getList(String listId){
		String command = composeCommand(ListService.listCommand, listId);
		return getPrivate(TrelloList.class, command, (String[])null);
	}
	
	public TrelloList getList(TrelloBoard board, String listName){
		List<TrelloList> lists = getLists(board);
		for (TrelloList list : lists) {
			if(list.name.equals(listName))
				return list;
		}
		return null;
	}
	
	public TrelloCard createCard(TrelloList list, String name, String desc, String ... labels){
		if(labels == null || labels.length == 0)
			return post(TrelloCard.class, CardsService.cardCommand, "name", name, "idList", list.id, "desc", desc);
		return post(TrelloCard.class, CardsService.cardCommand, "name", name, "idList", list.id, "desc", desc, "labels", StringUtils.stringCommaSeparated(labels));
	}

	public TrelloCard getCard(String id){
		return getPrivate(TrelloCard.class, composeCommand(CardsService.cardCommand, id));
	}
	
	public void deleteCard(TrelloCard card){
		delete(CardsService.cardCommand, card.id);
	}
	
	public void addComment(TrelloCard card, String comment){
		String command = composeCommand(CardsService.cardCommand, card.id, CardsService.commentsCommand);
		post(TrelloCard.class, command, "text", comment);
	}
	
	public TrelloWebhook createWebhook(String description, String callbackUrl, String idModel){
		String command = composeCommand("webhooks");
		String [] args = new String[6];
		args[0] = "description";
		args[1] = description;
		args[2] = "callbackURL";
		args[3] = callbackUrl;
		args[4] = "idModel";
		args[5] = idModel;
		
		TrelloWebhook wh = put(TrelloWebhook.class, command, args);
		return wh;
	}
	
	public void deleteWebhook(String id){
		String command = composeCommand("webhooks");
		delete(command, id);
	}
	
	private String composeCommand(String ... params){
		return StringUtils.stringSlashSeparated(params);
	}
	
	protected <T> T getPrivate( Class<T> t, String command, String ... args){
		String[] getArgs = argumentsWithKeyAndToken(args);
		return jsonHttpClient.get(t, command, getArgs);
	}

	protected <T> T getPublic(Class<T> t, String command, String ... args){
		String[] getArgs = argumentsWithKey(args);
		return jsonHttpClient.get(t, command, getArgs);
	}

	protected <T> T post(Class<T> t, String command, String ... args){
		String [] postArgs = argumentsWithKeyAndToken(args);
		return jsonHttpClient.postWithQueryParams(t, command, postArgs);
	}
	
	protected <T> T put(Class<T> t, String command, String ... args){
		String [] putArgs = argumentsWithKeyAndToken(args);
		return putWithQueryParams(t, command, putArgs);
	}
	
	private <T> T putWithQueryParams(Class<T> t, String command, String ... putArgs){
		String url = jsonHttpClient.getBaseUrl() + command;

		UriBuilder uriBuilder = new NoEncodedUriBuilder(url);
	    for (int i = 0; i < putArgs.length; i += 2)
	    	uriBuilder.addParameter(putArgs[i], putArgs[(i + 1)]);
	    
        URI uri = uriBuilder.build();
		HttpPut putRequest = new HttpPut(uri);

		System.err.println("PUT URL: " + uri);
	    
	    String responseString = execute(putRequest);
	    if(responseString == null)
	    	return null;
	    
		return jsonDeserializer.toObject(t, responseString);
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
	
	private String[] argumentsWithKey(String... args) {
		String [] getArgs = new String[args.length + 2];
		getArgs[0] = "key";
		getArgs[1] = appKey;
		for (int i = 0; i < args.length; i++) {
			getArgs[i+2] = args[0];
		}
		return getArgs;
	}
	
	private String[] argumentsWithKeyAndToken(String... args) {
		int argsLenght = (args == null ? 0 : args.length);

		if(argsLenght%2 != 0)
			throw new RuntimeException("Lista de parâmetros deve conter número par de argumentos");

		String [] getArgs = new String[argsLenght+4];
		getArgs[0] = "key";
		getArgs[1] = appKey;
		getArgs[2] = "token";
		getArgs[3] = authToken;
		for (int i = 0; i < argsLenght; i++)
			getArgs[i+4] = args[i];
		return getArgs;
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
	private JsonDeserializer jsonDeserializer;
	private String appKey;
	private String authToken;
	private String trelloUrl;
}
