package co.refiere.services.mailer;


import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import co.refiere.resources.base.EmailRequest;
import co.refiere.resources.util.RefiereInterceptor;

public class MailService{

    private static final Log log = LogFactory.getLog(RefiereInterceptor.class);
    static Properties mailServerProperties;
    static Session getMailSession;

    public MailService(Session session, Properties properties) {
        MailService.mailServerProperties = properties;
        MailService.getMailSession = session;
    }
 
    public void generateAndSendEmail(String [] recipients, String subject, String body, String [] attachmentsFilesPaths) throws AddressException, MessagingException {
 
        MimeMessage generateMailMessage = new MimeMessage(getMailSession);
        
        for(String recipient : recipients){
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        }
        
        generateMailMessage.setSubject(subject);
        
        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(body, "text/html; charset=utf-8");
        
        // Create a multipart message
        Multipart multipart = new MimeMultipart();
        
        // Set text message part
        multipart.addBodyPart(messageBodyPart);
        
        // Part two is attachment
        
        for(String filename : attachmentsFilesPaths){
            File attachment = new File(filename);
            if(attachment.exists()){
                DataSource source = new FileDataSource(attachment);
                messageBodyPart = new MimeBodyPart();
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(attachment.getName());
                multipart.addBodyPart(messageBodyPart);
            }
        }
        // Send the complete message parts
        generateMailMessage.setContent(multipart);
        Transport transport = getMailSession.getTransport(mailServerProperties.getProperty("refiere.email.protocol"));
         
        transport.connect(mailServerProperties.getProperty("refiere.email.server"), mailServerProperties.getProperty("refiere.email.user"), mailServerProperties.getProperty("refiere.email.password"));
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
    

    public void emailWorker(final List<EmailRequest> emailRequests) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(EmailRequest request : emailRequests){
                    try {
                        log.info("[BACKGROUND PROCESS]::emailWorker");
                        log.info(emailRequests.toString());
                        generateAndSendEmail(request.getRecipientAsArray(),
                                request.getSubject(),
                                request.getBody(),
                                request.getAttachmentsAsArray());
                    } catch (MessagingException exception) {
                        log.error(exception);
                    }
                }
            }
        }).start();
    }
}