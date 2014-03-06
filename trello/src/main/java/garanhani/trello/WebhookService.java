package garanhani.trello;

public interface WebhookService {
	public TrelloWebhook createWebhook(String description, String callbackUrl, String idModel);
	
//	public void deleteWebhook(String id);
	
	static public String webhookComand = "webhooks";
}
