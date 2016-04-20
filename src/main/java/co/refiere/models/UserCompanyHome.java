package co.refiere.models;
// Generated Apr 19, 2016 11:27:45 AM by Hibernate Tools 4.3.1.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class UserCompany.
 * @see co.refiere.models.UserCompany
 * @author Hibernate Tools
 */
public class UserCompanyHome {

    private static final Log log = LogFactory.getLog(UserCompanyHome.class);

    private final SessionFactory sessionFactory = getSessionFactory();

    protected SessionFactory getSessionFactory() {
        try {
            return (SessionFactory) new InitialContext().lookup("SessionFactory");
        } catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }

    public void persist(UserCompany transientInstance) {
        log.debug("persisting UserCompany instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        } catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }

    public void attachDirty(UserCompany instance) {
        log.debug("attaching dirty UserCompany instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(UserCompany instance) {
        log.debug("attaching clean UserCompany instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void delete(UserCompany persistentInstance) {
        log.debug("deleting UserCompany instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public UserCompany merge(UserCompany detachedInstance) {
        log.debug("merging UserCompany instance");
        try {
            UserCompany result = (UserCompany) sessionFactory.getCurrentSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public UserCompany findById(int id) {
        log.debug("getting UserCompany instance with id: " + id);
        try {
            UserCompany instance = (UserCompany) sessionFactory.getCurrentSession().get("co.refiere.models.UserCompany",
                    id);
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

    public List findByExample(UserCompany instance) {
        log.debug("finding UserCompany instance by example");
        try {
            List results = sessionFactory.getCurrentSession().createCriteria("co.refiere.models.UserCompany")
                    .add(Example.create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
