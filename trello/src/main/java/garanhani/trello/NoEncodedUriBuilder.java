package garanhani.trello;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.sumersoft.jsonapihttpclient.http.uri.UriBuilder;

public class NoEncodedUriBuilder implements UriBuilder {

	public NoEncodedUriBuilder(String aUrl) {
		url = aUrl;
		parameters = new ArrayList<String>();
	}

	public void addParameter(String arg0, String arg1) {
		parameters.add(arg0);
		parameters.add(arg1);
	}

	public URI build() {
		try {
			if (parameters.isEmpty())
				return new URI(this.url);

			StringBuilder sb = new StringBuilder(this.url);
			if (!urlIsEmpty()) {
				sb.append("?");
			}
			sb.append(build2());
			return new URI(sb.toString());
		}
		catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}


	private boolean urlIsEmpty() {
		return (this.url == null) || (this.url.trim().length() == 0);
	}

	public String build2() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < parameters.size(); i+=2)
		{
			if (i > 0)
				sb.append("&");

			sb.append(parameters.get(i));
			sb.append("=");
			sb.append(parameters.get(i+1));
		}
		return sb.toString();
	}

	private String url;
	private List<String> parameters;
}
