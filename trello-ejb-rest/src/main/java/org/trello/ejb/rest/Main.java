
package org.trello.ejb.rest;

import com.sun.jersey.api.container.grizzly2.GrizzlyWebContainerFactory;

import org.glassfish.grizzly.http.server.HttpServer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.core.UriBuilder;


public class Main {

    static URI getBaseURI() {
		return UriBuilder.fromUri(getURL()).port(getPort()).build();
    }

    private static int getPort() {
        String httpPort = System.getProperty("garanhani.trello.ejb.server.port");
        if (null != httpPort) {
            try {
                return Integer.parseInt(httpPort);
            } catch (NumberFormatException e) {
            }
        }
        return defaultPort;
    }

    private static String getURL(){
        String url = System.getProperty("garanhani.trello.ejb.server.url");
        return null == url ? defaultUrl : url;
    }
    
    protected static HttpServer startServer() throws IOException {
    	if(baseUri == null)
    		baseUri = getBaseURI();

    	final Map<String, String> initParams = new HashMap<String, String>();

        initParams.put("com.sun.jersey.config.property.packages", "org.trello.ejb.rest");

        System.out.println("Starting grizzly2...");
        return GrizzlyWebContainerFactory.create(baseUri, initParams);
    }
    
    private static void loadProperties(){
    	String path = System.getProperty("configFile");
        FileInputStream propFile;
		try {
			propFile = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			System.out.println("Property file not found");
			return;
		}
        Properties p = new Properties(System.getProperties());
        try {
			p.load(propFile);
		} catch (IOException e) {
			System.err.println("Property file not loaded");
			return;
		}

        System.setProperties(p);
    }
    
    private static final String defaultUrl = "http://localhost/";
    private static final int defaultPort = 8080;
    private static URI baseUri = null;
    
    public static void main(String[] args) throws IOException {
    	loadProperties();
   	
    	// Grizzly 2 initialization
        HttpServer httpServer = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...",
                baseUri));
        System.in.read();
        httpServer.stop();
    }    
}
