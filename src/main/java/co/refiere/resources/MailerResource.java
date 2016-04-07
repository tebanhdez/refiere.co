package co.refiere.resources;

import java.io.StringReader;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.mail.MessagingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.refiere.services.mailer.RefiereServiceFactory;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("v1/mailer")
public class MailerResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendEmail(String jsonEmail) {

    	JsonReader jsonReader = Json.createReader(new StringReader(jsonEmail));
    	JsonObject jsonobject = jsonReader.readObject();
    	jsonReader.close();
    	JsonArray recipientsJson = jsonobject.getJsonArray("recipients");
    	JsonArray attachmentsJson = jsonobject.getJsonArray("attachmentsFilesPath");
    	
    	ArrayList<String> recipients = new ArrayList<>();
    	ArrayList<String> attachments = new ArrayList<>();
    	for(JsonValue recipient : recipientsJson){
    		recipients.add(recipient.toString());
    	}
    	for(JsonValue attachment : attachmentsJson){
    		recipients.add(attachment.toString());
    	}
    	//String [] recipients = recipientsJson.toArray(new String [recipientsJson.size()]);
    	//String [] attachments = attachmentsJson.toArray(new String[attachmentsJson.size()]);
    	
    	try {
			RefiereServiceFactory.getMailService().generateAndSendEmail(recipients.toArray(new String[recipients.size()]), 
					jsonobject.getString("subject"), jsonobject.getString("body"), attachments.toArray(new String[attachments.size()]));
		} catch (MessagingException e) {
			e.printStackTrace();
		}
    	return Response.ok().build(); 
    }
}
