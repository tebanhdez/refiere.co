package co.refiere.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import co.refiere.models.Company;
import co.refiere.models.CompanyHome;
import co.refiere.resources.util.HibernateUtil;

public class RefiereCompanyDao extends CompanyHome {

    private static final Log log = LogFactory.getLog(RefiereCompanyDao.class);
    private final SessionFactory sessionFactory = getSessionFactory();

    public RefiereCompanyDao() {

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
    
    public void save(Company company){
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        persist(company);
        trans.commit();
    }

	public Company findCompanyById(int companyId) {
	    Session session = sessionFactory.getCurrentSession();
	    org.hibernate.Transaction trans = session.beginTransaction();
	    Company instance = findById(companyId);
	    trans.commit();
	    return instance;
	}

    public List<Company> findAll() {
        log.debug("getting Company instances");
        try {
            Session session = sessionFactory.getCurrentSession();
            org.hibernate.Transaction trans= session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close.");
            Query query = session.createQuery("from Company");
            java.util.List<Company> results = query.list();
            trans.commit();
            return results;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public void deleteCompany(Company company) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans = session.beginTransaction();
        delete(company);
        trans.commit();
    }
}
