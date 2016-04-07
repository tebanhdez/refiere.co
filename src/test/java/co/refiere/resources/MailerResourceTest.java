package co.refiere.resources;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;

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
    	JsonReader jsonReader = Json.createReader(new StringReader("{\"recipients\": [\"ehernandez@pernix.cr\"], \"subject\": \"TESTING\",\"body\": \"<h1>MAILER END_POINT</h1>\",\"attachmentsFilesPath\": []}"));
    	JsonObject jsonobject = jsonReader.readObject();
    	jsonReader.close();
        final Response mailerResponse = target().path("v1/mailer").request().post(Entity.json(jsonobject.toString()));

        assertEquals(200, mailerResponse.getStatus());
    }
}
