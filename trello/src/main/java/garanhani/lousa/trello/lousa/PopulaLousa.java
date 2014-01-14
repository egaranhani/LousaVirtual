package garanhani.lousa.trello.lousa;

import java.util.List;

public class PopulaLousa {
	String nomeLousa;
	
	PopulaLousa(String nome){
		nomeLousa = nome;
	}
	
	public boolean createCard(String type, String jiraIssue, List<String> jiraIssueLinked){
		return true;
	}
}
