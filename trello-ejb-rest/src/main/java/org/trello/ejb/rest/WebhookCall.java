
package org.trello.ejb.rest;

import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/webhooks")
public class WebhookCall {

    @GET
    @Produces("text/plain")
    public String getIt() {
        return "Got it!";
    }
    
    @HEAD
    @Produces("text/plain")
    public String head(){
    	return "200";
    }

    @POST
    public void cardAction( String parameters ){
    	System.out.println(parameters);
    }
}
