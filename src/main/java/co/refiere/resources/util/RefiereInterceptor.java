package co.refiere.resources.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;

import co.refiere.dao.ReferencesCodesDao;
import co.refiere.models.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.io.FileUtils;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import co.refiere.dao.CampaignDao;
import co.refiere.dao.PersonDao;
import co.refiere.resources.base.EmailRequest;
import co.refiere.services.mailer.RefiereServiceFactory;
import co.refiere.services.mailer.ResourceManager;
import co.refiere.services.qrcode.QRCodeService;

public class RefiereInterceptor extends EmptyInterceptor {

    /**
     * onSave – Called when you save an object, the object is not save into
     * database yet. onFlushDirty – Called when you update an object, the object
     * is not update into database yet. onDelete – Called when you delete an
     * object, the object is not delete into database yet. preFlush – Called
     * before the saved, updated or deleted objects are committed to database
     * (usually before postFlush). postFlush – Called after the saved, updated
     * or deleted objects are committed to database.
     */
    private static final long serialVersionUID = 1L;
    private static final Log  LOGGER           = LogFactory.getLog(RefiereInterceptor.class);
    private static Properties properties       = null;

    static {
        properties = new Properties();
        try {
            properties.load(ResourceManager.getResourceAsInputStream("mailservice.properties"));
        } catch (IOException exception) {
            LOGGER.error("Error loading properties file", exception);
        }
    }

    public String getStringfontTemplate(String fileName) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        File file = new File(classloader.getResource(fileName).getFile());

        String stringFile = "";
        try {
            stringFile = FileUtils.readFileToString(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringFile;
    }

    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types)
            throws CallbackException {

        if (entity instanceof Company) {
            Company company = (Company) entity;
            if ("".equals(company.getEmail())) {
                LOGGER.error("ERROR: RefiereInterceptor::Sending email >> Company email -null-");
            } else {
                String[] recipients = { company.getEmail() };
                String[] attachments = {};
                try {
                    RefiereServiceFactory.getMailService().generateAndSendEmail(recipients, "Bienvenido a Refiere.co",
                            getStringfontTemplate("RefiereTemplateInvoice.html"), attachments);
                } catch (MessagingException e) {
                    LOGGER.error("ERROR: RefiereInterceptor::Sending email", e);
                }
            }
        }

        if (entity instanceof Campaign) {
            Campaign campaign = (Campaign) entity;
            int dataBase = campaign.getCompanyDatabase().getId();
            String query = "from Person where company_database_id = %d";
            PersonDao personDao = new PersonDao();
            ReferencesCodesDao referencesCodesDao = new ReferencesCodesDao();
            StatelessSession statelessSession = personDao.getStatelessSession();
            statelessSession.beginTransaction();

            try {
                ScrollableResults scrollableResults = statelessSession.createQuery(String.format(query, dataBase))
                        .scroll(ScrollMode.FORWARD_ONLY);

                int CHUNK_SIZE = 100;
                List<EmailRequest> campaignTargets = new ArrayList<>();
                while (scrollableResults.next()) {
                    Object personObj = scrollableResults.get()[0];
                    Person person = (Person) personObj;
                    List<String> recipients = new ArrayList<>();
                    recipients.add(person.getEmail());
                    List<String> attachmentsFilesPaths = new ArrayList<>();

                    String html = getStringfontTemplate("RefiereTemplateCode.html");
                    String prizeName = campaign.getPrizeByPrizeForRefiereId().getDescription();
                    html = html.replace("XXXX", prizeName);
                    String prizeforReferrerAmount = campaign.getPrizeForRefiere();
                    html = html.replace("ZZZZ", prizeforReferrerAmount);
                    String prizeAmount = campaign.getPrizeForReferee();
                    html = html.replace("YYYY", prizeAmount);
                    String newCode = QRCodeService.generateQRCode();
                    html = html.replace("CCCC", newCode);

                    ReferencesCodesId referencesCodesId = new ReferencesCodesId(campaign.getId(), person.getId());
                    ReferencesCodes referencesCode = new ReferencesCodes(referencesCodesId, null, newCode);
                    referencesCodesDao.persist(referencesCode);

                    EmailRequest request = new EmailRequest();
                    request.setRecipients(recipients);
                    request.setSenderAddress(properties.get("mail.smtp.user").toString());
                    request.setSubject(properties.get("refiere.email.subject").toString());
                    request.setBody(html);
                    request.setAttachments(attachmentsFilesPaths);
                    campaignTargets.add(request);
                    if (campaignTargets.size() == CHUNK_SIZE) {
                        LOGGER.info("processing EmailQueue::Queue Size: " + campaignTargets.size());
                        RefiereServiceFactory.getMailService().emailWorker(campaignTargets);
                        campaignTargets.clear();
                    }
                }
                if (campaignTargets.size() > 0) {
                    LOGGER.info("processing EmailQueue::Queue Size: " + campaignTargets.size());
                    RefiereServiceFactory.getMailService().emailWorker(campaignTargets);
                }
                statelessSession.getTransaction().commit();
            } finally {
                try {
                    statelessSession.close();
                } catch (org.hibernate.SessionException exception) {
                    LOGGER.error(exception.getMessage());
                }
            }
        }
        return false;
    }
}
