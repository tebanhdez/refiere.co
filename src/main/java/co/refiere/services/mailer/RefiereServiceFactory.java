package co.refiere.services.mailer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;


public class RefiereServiceFactory {

    private static final Log LOGGER = LogFactory.getLog(RefiereServiceFactory.class);

    private static MailService mailService = null;
    static Properties mailServerProperties;
    static{
        getMailServiceProperties();
    }

    private static void getMailServiceProperties() {
        mailServerProperties = new Properties();
        try {
            mailServerProperties.load(ResourceManager.getResourceAsInputStream("mailservice.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MailService getMailService(){
        if(mailServerProperties == null)
            getMailServiceProperties();
        mailService = (mailService != null) ? mailService : (new MailService(getMailServiceSession(), mailServerProperties));
        return mailService;
    }

    private static Session getMailServiceSession(){

        Authenticator auth = new SMTPAuthenticator();

        Session session = Session.getInstance(mailServerProperties, auth);
        session.setDebug(true);
        return session;
    }

    private static class SMTPAuthenticator extends javax.mail.Authenticator
    {
        private final String username = mailServerProperties.getProperty("mail.smtp.user");
        private final String password = mailServerProperties.getProperty("info.email.pass");

        public PasswordAuthentication getPasswordAuthentication()
        {
            if(org.apache.commons.lang.StringUtils.isNotEmpty(username) &&
                    org.apache.commons.lang.StringUtils.isNotEmpty(password))
                return new PasswordAuthentication(username, password);
            else
                LOGGER.error("User credentials not loaded.");
            return null;
        }
    }
}
