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
 * Home object for domain model class PlanOrder.
 * @see co.refiere.models.PlanOrder
 * @author Hibernate Tools
 */
public class PlanOrderHome {

    private static final Log log = LogFactory.getLog(PlanOrderHome.class);

    private final SessionFactory sessionFactory = getSessionFactory();

    protected SessionFactory getSessionFactory() {
        try {
            return (SessionFactory) new InitialContext().lookup("SessionFactory");
        } catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }

    public void persist(PlanOrder transientInstance) {
        log.debug("persisting PlanOrder instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        } catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }

    public void attachDirty(PlanOrder instance) {
        log.debug("attaching dirty PlanOrder instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(PlanOrder instance) {
        log.debug("attaching clean PlanOrder instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void delete(PlanOrder persistentInstance) {
        log.debug("deleting PlanOrder instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public PlanOrder merge(PlanOrder detachedInstance) {
        log.debug("merging PlanOrder instance");
        try {
            PlanOrder result = (PlanOrder) sessionFactory.getCurrentSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public PlanOrder findById(java.lang.String id) {
        log.debug("getting PlanOrder instance with id: " + id);
        try {
            PlanOrder instance = (PlanOrder) sessionFactory.getCurrentSession().get("co.refiere.models.PlanOrder", id);
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

    public List findByExample(PlanOrder instance) {
        log.debug("finding PlanOrder instance by example");
        try {
            List results = sessionFactory.getCurrentSession().createCriteria("co.refiere.models.PlanOrder")
                    .add(Example.create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
