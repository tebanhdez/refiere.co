package co.refiere.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import co.refiere.models.Prize;
import co.refiere.models.PrizeHome;
import co.refiere.resources.util.HibernateUtil;

public class PrizeDao extends PrizeHome {

    private static final Log log = LogFactory.getLog(PrizeDao.class);
    private final SessionFactory sessionFactory = getSessionFactory();

    @Override
    public SessionFactory getSessionFactory(){
        try {
            return (SessionFactory) HibernateUtil.getSessionFactory();
        } catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }
    
    public void save(Prize prize){
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        session.saveOrUpdate(prize);
        trans.commit();
    }
    
    public Prize findPrizeById(int id) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        Prize instance = findById(id);
        trans.commit();
        return instance;
    }
    
    public List<Prize> findAllPrizes() {
        log.debug("getting RefierePrize's instances");
        try {
            Session session = sessionFactory.openSession();
            org.hibernate.Transaction trans = session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close");
            Query query = session.createQuery("from Prize");
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
}
