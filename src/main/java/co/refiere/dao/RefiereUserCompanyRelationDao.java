package co.refiere.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import co.refiere.models.RefiereUserCompany;
import co.refiere.models.RefiereUserCompanyHome;
import co.refiere.models.RefiereUserHome;
import co.refiere.resources.util.HibernateUtil;

public class RefiereUserCompanyRelationDao extends RefiereUserCompanyHome {

    private static final Log log = LogFactory.getLog(RefiereUserHome.class);
    private final SessionFactory sessionFactory = getSessionFactory();

    public RefiereUserCompanyRelationDao() {
    }

    @Override
    public SessionFactory getSessionFactory(){
        try {
            return (SessionFactory) HibernateUtil.getSessionFactory();
        } catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }
    
    public void save(RefiereUserCompany company){
        log.debug("saving RefiereCompany");
        try {
            Session session = sessionFactory.openSession();
            org.hibernate.Transaction trans= session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close.");
            session.persist(company);
            trans.commit();
            log.debug("persist successful");
        } catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
}
