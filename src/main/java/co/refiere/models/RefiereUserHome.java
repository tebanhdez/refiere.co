package co.refiere.models;
// Generated Apr 15, 2016 11:32:27 AM by Hibernate Tools 4.3.1.Final

import java.util.List;
import javax.naming.InitialContext;
import javax.transaction.Transaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import co.refiere.models.RefiereUser;
import co.refiere.resources.util.HibernateUtil;

/**
 * Home object for domain model class RefiereUser.
 * @see co.refiere.models.RefiereUser
 * @author Hibernate Tools
 */
public class RefiereUserHome {

    private static final Log log = LogFactory.getLog(RefiereUserHome.class);

    private final SessionFactory sessionFactory = getSessionFactory();

    protected SessionFactory getSessionFactory() {
        try {
            return (SessionFactory) new InitialContext().lookup("SessionFactory");
        } catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }

    public void persist(RefiereUser transientInstance) {
        log.debug("persisting RefiereUser instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        } catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }

    public void attachDirty(RefiereUser instance) {
        log.debug("attaching dirty RefiereUser instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(RefiereUser instance) {
        log.debug("attaching clean RefiereUser instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void delete(RefiereUser persistentInstance) {
        log.debug("deleting RefiereUser instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public RefiereUser merge(RefiereUser detachedInstance) {
        log.debug("merging RefiereUser instance");
        try {
            RefiereUser result = (RefiereUser) sessionFactory.getCurrentSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public RefiereUser findById(java.io.Serializable id) {
        log.debug("getting RefiereUser instance with id: " + id);
        try {
            RefiereUser instance = (RefiereUser) sessionFactory.getCurrentSession()
                    .get("co.refiere.models.RefiereUser", id);
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

    public List findByExample(RefiereUser instance) {
        log.debug("finding RefiereUser instance by example");
        try {

            Session session = sessionFactory.openSession();
            org.hibernate.Transaction trans= session.beginTransaction();

            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close.");

            List results = session.createCriteria("co.refiere.models.RefiereUser")
                    .add(Example.create(instance)).list();

            trans.commit();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
