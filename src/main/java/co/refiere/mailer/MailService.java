package co.refiere.mailer;


import java.io.File;
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

public class MailService{

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
        Transport transport = getMailSession.getTransport(mailServerProperties.getProperty("icanbuy.email.protocol"));
         
        transport.connect(mailServerProperties.getProperty("icanbuy.email.server"), mailServerProperties.getProperty("icanbuy.email.user"), mailServerProperties.getProperty("icanbuy.email.password"));
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
}