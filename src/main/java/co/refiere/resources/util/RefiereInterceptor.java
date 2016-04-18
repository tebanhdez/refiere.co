package co.refiere.resources.util;

import java.io.Serializable;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import co.refiere.models.RefiereCompany;
import co.refiere.models.RefiereUserHome;
import co.refiere.services.mailer.RefiereServiceFactory;

public class RefiereInterceptor extends EmptyInterceptor {

    /**
     * onSave – Called when you save an object, the object is not save into database yet.
     * onFlushDirty – Called when you update an object, the object is not update into database yet.
     * onDelete – Called when you delete an object, the object is not delete into database yet.
     * preFlush – Called before the saved, updated or deleted objects are committed to database (usually before postFlush).
     * postFlush – Called after the saved, updated or deleted objects are committed to database.
     */
    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(RefiereUserHome.class);

    public RefiereInterceptor() {
    }

    public boolean onSave(Object entity,Serializable id,
            Object[] state,String[] propertyNames,Type[] types)
                    throws CallbackException {

        if (entity instanceof RefiereCompany){
            RefiereCompany company = (RefiereCompany) entity;
            if("".equals(company.getEmail())){
                log.error("ERROR: RefiereInterceptor::Sending email >> Company email -null-");
            }else{
                String [] recipients = {company.getEmail()};
                String [] attachments = {};
                try {
                    RefiereServiceFactory.getMailService().generateAndSendEmail(recipients,
                            "Bienvenido a Refiere.co",
                            "<h1> Hey Bienvenido!</h1>", attachments);
                } catch (MessagingException e) {
                    log.error("ERROR: RefiereInterceptor::Sending email", e);
                }
            }
        }
        return false;

    }
}
