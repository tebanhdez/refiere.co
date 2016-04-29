package co.refiere.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import co.refiere.models.ReferencesCodes;
import co.refiere.models.ReferencesCodesHome;
import co.refiere.resources.util.HibernateUtil;

public class ReferencesCodesDao extends ReferencesCodesHome {


	private static final Log log = LogFactory.getLog(CurrencyDao.class);
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

	public void save(ReferencesCodes referencesCodes){
		Session session = sessionFactory.getCurrentSession();
		org.hibernate.Transaction trans= session.beginTransaction();
		persist(referencesCodes);
		trans.commit();
	}

}

