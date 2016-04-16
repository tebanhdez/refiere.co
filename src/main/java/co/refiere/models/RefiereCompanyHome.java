package co.refiere.models;
// Generated Apr 15, 2016 11:32:27 AM by Hibernate Tools 4.3.1.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class RefiereCompany.
 * @see co.refiere.models.RefiereCompany
 * @author Hibernate Tools
 */
public class RefiereCompanyHome {

    private static final Log log = LogFactory.getLog(RefiereCompanyHome.class);

    private final SessionFactory sessionFactory = getSessionFactory();

    protected SessionFactory getSessionFactory() {
        try {
            return (SessionFactory) new InitialContext().lookup("SessionFactory");
        } catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }

    public Session getOpenSession(){
        return sessionFactory.openSession();
    }

    public void persist(RefiereCompany transientInstance) {
        log.debug("persisting RefiereCompany instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        } catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }

    public void attachDirty(RefiereCompany instance) {
        log.debug("attaching dirty RefiereCompany instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(RefiereCompany instance) {
        log.debug("attaching clean RefiereCompany instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void delete(RefiereCompany persistentInstance) {
        log.debug("deleting RefiereCompany instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public RefiereCompany merge(RefiereCompany detachedInstance) {
        log.debug("merging RefiereCompany instance");
        try {
            RefiereCompany result = (RefiereCompany) sessionFactory.getCurrentSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public RefiereCompany findById(int id) {
        log.debug("getting RefiereCompany instance with id: " + id);
        try {
            RefiereCompany instance = (RefiereCompany) sessionFactory.getCurrentSession()
                    .get("co.refiere.models.RefiereCompany", id);
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

    public List findByExample(RefiereCompany instance) {
        log.debug("finding RefiereCompany instance by example");
        try {
            List results = sessionFactory.getCurrentSession().createCriteria("co.refiere.models.RefiereCompany")
                    .add(Example.create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
