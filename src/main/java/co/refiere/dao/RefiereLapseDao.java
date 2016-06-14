package co.refiere.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import co.refiere.models.Lapse;
import co.refiere.models.LapseHome;
import co.refiere.models.UserRoles;
import co.refiere.resources.util.HibernateUtil;

import java.util.List;

public class RefiereLapseDao extends LapseHome {

    private static final Log log = LogFactory.getLog(RefiereLapseDao.class);
    private final SessionFactory sessionFactory = getSessionFactory();
    
    public RefiereLapseDao() {
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
    
    public void save(Lapse lapse){
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        persist(lapse);
        trans.commit();
    }

    public List<Lapse> getAllLapses(){
        log.debug("getting all Lapse's instances");
        try {
            Session session = sessionFactory.getCurrentSession();
            org.hibernate.Transaction trans = session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close");
            Query query = session.createQuery("from Lapse");
            java.util.List results = query.list();
            trans.commit();
            if (results != null && !results.isEmpty()) {
                log.debug("get successful, instance found");
            } else {
                log.debug("get successfull, no instance found");
            }
            return results;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public Lapse findBylapseById(int id) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        Lapse instance = findById(id);
        trans.commit();
        return instance;
    }
}
