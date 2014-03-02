package garanhani.trello.lousa;

import java.util.StringTokenizer;


public class FileContent {

	public void setData(String line) {
		String[] fields = line.split(";");
		issue = new String(fields[ISSUE_FIELD]);
		desc = new String(fields[DESC_FIELD]);
		
		StringTokenizer st = new StringTokenizer(fields[BUGS_FIELD]);
		bugIssues = new String [st.countTokens()];
		for(int i = 0; i < st.countTokens(); i++)
			bugIssues[i] = new String(st.nextToken());
	}

	String issue;
	String desc;
	String [] bugIssues;
	
	final int ISSUE_FIELD = 0;
	final int DESC_FIELD = 6;
	final int BUGS_FIELD = 2;

}
