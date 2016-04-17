package co.refiere.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.TypedValue;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import co.refiere.models.RefiereUser;
import co.refiere.models.RefiereUserHome;
import co.refiere.resources.util.HibernateUtil;

public class RefiereUserDao extends RefiereUserHome {

    private static final Log log = LogFactory.getLog(RefiereUserHome.class);
    private final SessionFactory sessionFactory = getSessionFactory();

    public RefiereUserDao() {

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
    
    public void save(RefiereUser user){
        log.debug("saving RefiereUser");
        try {
            Session session = sessionFactory.openSession();
            org.hibernate.Transaction trans= session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close.");
            session.persist(user);
            trans.commit();
            log.debug("persist successful");
        } catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }


    public RefiereUser findByLogin(String login) {
        log.debug("getting RefiereUser instance with login: " + login);
        try {
            Session session = sessionFactory.openSession();
            org.hibernate.Transaction trans= session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close.");
            Query query = session.createQuery("from RefiereUser where login = :login");
            query.setParameter("login", login);
            java.util.List results = query.list();
            trans.commit();
            RefiereUser instance = (results != null && results.size() == 1) ? (RefiereUser) results.get(0) : null;
            if (instance == null) {
                log.debug("get successful, no instance found");
            } else {
                log.debug("get successful, instance found");
            }
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}
