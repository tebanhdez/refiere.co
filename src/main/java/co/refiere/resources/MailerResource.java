package co.refiere.resources;

import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.refiere.resources.base.EmailRequest;
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
    public Response sendEmail(EmailRequest emailReq) {
    	
    	try {
    		List<String> recipients = emailReq.getRecipients();
    		List<String> attachments = emailReq.getAttachments();
    		RefiereServiceFactory.getMailService().generateAndSendEmail(Arrays.copyOf(recipients.toArray(), recipients.size(), String[].class), emailReq.getSubject(), 
    				emailReq.getBody(), Arrays.copyOf(attachments.toArray(), attachments.size(), String[].class));
		} catch (MessagingException e) {
			e.printStackTrace();
		}
    	return Response.ok().build(); 
    }
}
