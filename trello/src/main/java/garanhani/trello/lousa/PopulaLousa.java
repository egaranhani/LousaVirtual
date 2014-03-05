package garanhani.trello.lousa;

import garanhani.trello.TrelloBoard;
import garanhani.trello.TrelloCard;
import garanhani.trello.TrelloClient;
import garanhani.trello.TrelloList;

import java.util.List;

public class PopulaLousa {
	TrelloClient trello;
	String boardName;
	String orgName;
	
	PopulaLousa(String org, String nome){
		boardName = nome;
		orgName = org;
		trello = new TrelloClient();
	}
	
	public void createIssue(String jiraIssueChamado, String issueDesc, List<String> jiraIssueBugs){
		TrelloBoard board = trello.getBoard(orgName, boardName);
		TrelloList list = trello.getList(board, "Fila");
		
		String desc = issueWithUrl("Chamado", jiraIssueChamado);
		for (String bug : jiraIssueBugs) {
			desc += "\n";
			desc += issueWithUrl("Bug", bug);
		}

		String name = jiraIssueChamado + " = " + issueDesc;

		TrelloClient client = new TrelloClient();
		client.createCard(list, name, desc, "red");
	}
	
	public void deleteTestCards(){
		TrelloBoard board = trello.getBoard(orgName, boardName);
		List<TrelloCard> cards = trello.getCards(board);
		for (TrelloCard card : cards) {
			if(card.name.equals("UnitTestCard 2"))
				trello.deleteCard(card);
		}
	}
	
	private String issueWithUrl(String type, String issue){
		StringBuilder builder = new StringBuilder();
		builder.append(type);
		builder.append(": [" + issue + "]");
		builder.append("(https://jira.objective.com.br/browse/" + issue + ")");
		return builder.toString();
	}

	public static void main(String[] args) {
		PopulaLousa populator = new PopulaLousa("unittestorg", "UnitTestBoard"); //"Copel - Build Abril");
//		LoadIssuesFile fileLoader = new LoadIssuesFile("F:\\Desenvolvimento\\Projetos\\LousaVirtual-git\\Teste carregamento bugs.csv");
////		LoadIssuesFile fileLoader = new LoadIssuesFile("C:\\Desenvolvimento\\taskboard-trello-client\\Teste carregamento bugs.csv");
//		FileContent line;
//		while((line = fileLoader.nextData()) != null){
//			populator.createIssue(line.issue, line.desc, Arrays.asList(line.bugIssues));
//		}
		
		populator.deleteTestCards();
	}

}
