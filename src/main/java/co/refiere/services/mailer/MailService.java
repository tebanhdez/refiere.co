package co.refiere.services.mailer;


import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

            MimeMessage email = new MimeMessage(getMailSession);

            for (String recipient : recipients) {
                email.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            }
            email.setFrom(new InternetAddress(sender));
            email.setSubject(subject);

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(body, "text/html; charset=utf-8");

            // Create a multipart message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            for (String filename : attachmentsFilesPaths) {
                File attachment = new File(filename);
                if (attachment.exists()) {
                    DataSource source = new FileDataSource(attachment);
                    messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(attachment.getName());
                    multipart.addBodyPart(messageBodyPart);
                }
            }
            // Send the complete message parts
            email.setContent(multipart);
            Transport.send(email);
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