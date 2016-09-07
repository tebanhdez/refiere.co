package co.refiere.services.mailer;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sparkpost.Client;

import co.refiere.resources.base.EmailRequest;

public class MailService{


    SecurityManager security = System.getSecurityManager();

    private static final Log LOGGER = LogFactory.getLog(MailService.class);
    static Properties mailServerProperties;
    static Session getMailSession;

    public MailService(Session session, Properties properties) {
        MailService.mailServerProperties = properties;
        MailService.getMailSession = session;
    }

    public void generateAndSendEmail(String sender, String [] recipients, String subject, String body, String [] attachmentsFilesPaths) throws AddressException, MessagingException {

        try {
            String API_KEY = mailServerProperties.getProperty("mail.api.key");
            Client client = new Client(API_KEY);

            client.sendMessage(sender, Arrays.asList(recipients), subject, body, body);
        }
        catch (Exception mailerException)
        {
            LOGGER.error(mailerException);
            mailerException.printStackTrace();
        }
    }

    public void generateAndSendEmail(String [] recipients, String subject, String body, String [] attachmentsFilesPaths) throws AddressException, MessagingException {
        String sender = mailServerProperties.getProperty("mail.smtp.user");
        generateAndSendEmail(sender, recipients, subject, body, attachmentsFilesPaths);
    }

    public void emailWorker(final List<EmailRequest> emailRequests) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(EmailRequest request : emailRequests){
                    try {
                        LOGGER.info("[BACKGROUND PROCESS]::emailWorker");
                        LOGGER.info(emailRequests.toString());
                        generateAndSendEmail(request.getSenderAddress(),
                                request.getRecipientAsArray(),
                                request.getSubject(),
                                request.getBody(),
                                request.getAttachmentsAsArray());
                    } catch (MessagingException exception) {
                        LOGGER.error(exception);
                    }
                }
            }
        }).start();
    }
}
