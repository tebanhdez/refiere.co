package co.refiere.models;
// Generated Apr 15, 2016 11:32:27 AM by Hibernate Tools 4.3.1.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class RefierePlan.
 * @see co.refiere.models.RefierePlan
 * @author Hibernate Tools
 */
public class RefierePlanHome {

    private static final Log log = LogFactory.getLog(RefierePlanHome.class);

    private final SessionFactory sessionFactory = getSessionFactory();

    protected SessionFactory getSessionFactory() {
        try {
            return (SessionFactory) new InitialContext().lookup("SessionFactory");
        } catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }

    public void persist(RefierePlan transientInstance) {
        log.debug("persisting RefierePlan instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        } catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }

    public void attachDirty(RefierePlan instance) {
        log.debug("attaching dirty RefierePlan instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(RefierePlan instance) {
        log.debug("attaching clean RefierePlan instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void delete(RefierePlan persistentInstance) {
        log.debug("deleting RefierePlan instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public RefierePlan merge(RefierePlan detachedInstance) {
        log.debug("merging RefierePlan instance");
        try {
            RefierePlan result = (RefierePlan) sessionFactory.getCurrentSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public RefierePlan findById(int id) {
        log.debug("getting RefierePlan instance with id: " + id);
        try {
            RefierePlan instance = (RefierePlan) sessionFactory.getCurrentSession().get("co.refiere.models.RefierePlan",
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

    public List findByExample(RefierePlan instance) {
        log.debug("finding RefierePlan instance by example");
        try {
            List results = sessionFactory.getCurrentSession().createCriteria("co.refiere.models.RefierePlan")
                    .add(Example.create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}
