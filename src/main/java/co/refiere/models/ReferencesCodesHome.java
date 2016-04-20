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
 * Home object for domain model class ReferencesCodes.
 * @see co.refiere.models.ReferencesCodes
 * @author Hibernate Tools
 */
public class ReferencesCodesHome {

    private static final Log log = LogFactory.getLog(ReferencesCodesHome.class);

    private final SessionFactory sessionFactory = getSessionFactory();

    protected SessionFactory getSessionFactory() {
        try {
            return (SessionFactory) new InitialContext().lookup("SessionFactory");
        } catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }

    public void persist(ReferencesCodes transientInstance) {
        log.debug("persisting ReferencesCodes instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        } catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }

    public void attachDirty(ReferencesCodes instance) {
        log.debug("attaching dirty ReferencesCodes instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(ReferencesCodes instance) {
        log.debug("attaching clean ReferencesCodes instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void delete(ReferencesCodes persistentInstance) {
        log.debug("deleting ReferencesCodes instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public ReferencesCodes merge(ReferencesCodes detachedInstance) {
        log.debug("merging ReferencesCodes instance");
        try {
            ReferencesCodes result = (ReferencesCodes) sessionFactory.getCurrentSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public ReferencesCodes findById(co.refiere.models.ReferencesCodesId id) {
        log.debug("getting ReferencesCodes instance with id: " + id);
        try {
            ReferencesCodes instance = (ReferencesCodes) sessionFactory.getCurrentSession()
                    .get("co.refiere.models.ReferencesCodes", id);
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

    public List findByExample(ReferencesCodes instance) {
        log.debug("finding ReferencesCodes instance by example");
        try {
            List results = sessionFactory.getCurrentSession().createCriteria("co.refiere.models.ReferencesCodes")
                    .add(Example.create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
