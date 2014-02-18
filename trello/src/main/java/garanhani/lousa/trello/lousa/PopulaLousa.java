package garanhani.lousa.trello.lousa;

import garanhani.lousa.trello.TrelloBoard;
import garanhani.lousa.trello.TrelloBoardData;
import garanhani.lousa.trello.TrelloCards;
import garanhani.lousa.trello.TrelloListData;
import garanhani.lousa.trello.TrelloOrganization;

import java.util.List;

public class PopulaLousa {
	String boardName;
	
	PopulaLousa(String nome){
		boardName = nome;
	}
	
	public void createIssue(String jiraIssueChamado, String issueDesc, List<String> jiraIssueBugs){
		TrelloOrganization org = new TrelloOrganization("Objective");
		TrelloBoardData board = org.getBoard(boardName);
		
		TrelloBoard boardClient = new TrelloBoard();
		TrelloListData list = boardClient.getList(board.id, "Fila");
		
		String desc = issueWithUrl("Chamado", jiraIssueChamado);
		for (String bug : jiraIssueBugs) {
			desc += "\n";
			desc += issueWithUrl("Bug", bug);
		}

		String name = jiraIssueChamado + " = " + issueDesc;
		TrelloCards client = new TrelloCards();
		client.createCard(list.id, name, desc);
	}
	
	private String issueWithUrl(String type, String issue){
		StringBuilder builder = new StringBuilder();
		builder.append(type);
		builder.append(": [" + issue + "]");
		builder.append("(" + "(https://jira.objective.com.br/browse/" + issue + ")");
		return builder.toString();
	}

	public static void main(String[] args) {
		
	}
}
