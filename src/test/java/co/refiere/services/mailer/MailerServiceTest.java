package co.refiere.services.mailer;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import co.refiere.services.mailer.RefiereServiceFactory;

public class MailerServiceTest {

private final String [] EMAIL_ADDRESS_TEST = {"info@refiere.co"};

  @Ignore
	@Test
    public void testGetMailService() throws AddressException, MessagingException, IOException {

        //create a temporal files for testing.
        File temp1 = File.createTempFile("temp-file-name1", ".pdf");
        File temp2 = File.createTempFile("temp-file-name2", ".pdf");
        Assert.assertNotNull("No attachment found", temp1);
        String [] attachments = {temp1.getAbsolutePath(), temp2.getAbsolutePath()};
        RefiereServiceFactory.getMailService().generateAndSendEmail(EMAIL_ADDRESS_TEST, "Email Test 2 Attachments", "<h1>Testing mail</h1>", attachments);
    }

    @Ignore
    @Test
    public void testGetMailServiceNoAttachments() throws AddressException, MessagingException, IOException {
        String [] attachments = {};
        RefiereServiceFactory.getMailService().generateAndSendEmail(EMAIL_ADDRESS_TEST, "Email Test No Attachments", "<h1>Testing mail</h1>", attachments);
    }

    @Ignore
    @Test
    public void testGetMailServiceFileNotFound() throws AddressException, MessagingException, IOException {
        String [] attachments = {"thisFileNotExist"};
        RefiereServiceFactory.getMailService().generateAndSendEmail(EMAIL_ADDRESS_TEST, "Email Test No Attachments - FileNotFound", "<h1>Testing mail</h1>", attachments);
    }
    @Ignore
    @Test
    public void testGetMailServiceEmailInvalidEmail() throws AddressException, MessagingException, IOException {
        String [] attachments = {};
        String [] invalidEmail = {"invalidEmail@invalid", "invalidEmail@invalid2"};
        RefiereServiceFactory.getMailService().generateAndSendEmail(invalidEmail, "Email Test No Attachments - FileNotFound", "<h1>Testing mail</h1>", attachments);
    }
    @Ignore
    @Test(expected = AddressException.class)
    public void testGetMailServiceEmailEmptyEmailAddress() throws AddressException, MessagingException, IOException {
        String [] attachments = {};
        String [] invalidEmail = {"", null};
        RefiereServiceFactory.getMailService().generateAndSendEmail(invalidEmail, "Email Test No Attachments - FileNotFound", "<h1>Testing mail</h1>", attachments);
    }
    @Ignore
    @Test(expected = NullPointerException.class)
    public void testGetMailServiceEmailEmptyMessage() throws AddressException, MessagingException, IOException {
        String [] attachments = {};
        RefiereServiceFactory.getMailService().generateAndSendEmail(EMAIL_ADDRESS_TEST, "Empty Body", null, attachments);
    }
    @Ignore
    @Test
    public void testGetMailServiceEmailEmptySubject() throws AddressException, MessagingException, IOException {
        String [] attachments = {};
        RefiereServiceFactory.getMailService().generateAndSendEmail(EMAIL_ADDRESS_TEST, null, "No subject", attachments);
    }
}
