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
 * Home object for domain model class RefiereLapse.
 * @see co.refiere.models.RefiereLapse
 * @author Hibernate Tools
 */
public class RefiereLapseHome {

	private static final Log log = LogFactory.getLog(RefiereLapseHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(RefiereLapse transientInstance) {
		log.debug("persisting RefiereLapse instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(RefiereLapse instance) {
		log.debug("attaching dirty RefiereLapse instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RefiereLapse instance) {
		log.debug("attaching clean RefiereLapse instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(RefiereLapse persistentInstance) {
		log.debug("deleting RefiereLapse instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RefiereLapse merge(RefiereLapse detachedInstance) {
		log.debug("merging RefiereLapse instance");
		try {
			RefiereLapse result = (RefiereLapse) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RefiereLapse findById(int id) {
		log.debug("getting RefiereLapse instance with id: " + id);
		try {
			RefiereLapse instance = (RefiereLapse) sessionFactory.getCurrentSession()
					.get("co.refiere.models.RefiereLapse", id);
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

	public List findByExample(RefiereLapse instance) {
		log.debug("finding RefiereLapse instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("co.refiere.models.RefiereLapse")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
