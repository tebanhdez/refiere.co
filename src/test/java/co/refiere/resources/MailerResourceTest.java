package co.refiere.resources;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import co.refiere.resources.base.EmailRequest;

public class MailerResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(MailerResource.class);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testSendEmail() {
        
    	EmailRequest email = new EmailRequest();
    	List<String> recipients = new LinkedList<String>();
    	recipients.add("jpblo.3105@gmail.com");
    	List<String> attachments = new LinkedList<String>();
    	email.setRecipients(recipients);
    	email.setBody("<h1>EMAIL END-POINT TEST</h1>");
    	email.setSubject("--- TESTING ---");
    	email.setAttachments(attachments);
    	final Response mailerResponse = target().path("v1/mailer").request().post(Entity.json(email));

        assertEquals(200, mailerResponse.getStatus());
    }
}
