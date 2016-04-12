package co.refiere.services.mailer;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Session;


public class RefiereServiceFactory {
	
	private static MailService mailService = null;
	static Properties mailServerProperties;
	
	public static MailService getMailService(){
		if(mailServerProperties == null)
			getMailServiceProperties();
		mailService = (mailService != null) ? mailService : (new MailService(getMailServiceSession(), mailServerProperties));
		return mailService;
	}
	
	private static Session getMailServiceSession(){
		return Session.getDefaultInstance(mailServerProperties, null);	
	}
	
	private static void getMailServiceProperties(){
		mailServerProperties = new Properties();
		try {
			mailServerProperties.load(ResourceManager.getResourceAsInputStream("mailservice.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
