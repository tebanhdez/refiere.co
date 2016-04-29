package co.refiere.dao;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import co.refiere.models.CompanyDatabase;
import co.refiere.models.CompanyDatabaseHome;
import co.refiere.resources.util.HibernateUtil;

public class CompanyDatabaseDao extends CompanyDatabaseHome {

    private static final Log log = LogFactory.getLog(CompanyDatabaseDao.class);
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
    
    public List<CompanyDatabase> findAllDatabasesByCompanyId(int companyId){
        log.debug("getting company's databases: " + companyId);
        java.util.List results = new LinkedList<>();
        try {
            Session session = sessionFactory.getCurrentSession();
            org.hibernate.Transaction trans= session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close.");
            Query query = session.createQuery("from CompanyDatabase cdb inner join cdb.campaign ca where ca.company.id = :companyId");
            query.setParameter("companyId", companyId);
            results = query.list();
            trans.commit();
            return results;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public void save(CompanyDatabase dataBase) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        persist(dataBase);
        trans.commit();
    }

    public CompanyDatabase findDatabaseById(int companyDataBaseId) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        CompanyDatabase dataBase = session.get(CompanyDatabase.class, companyDataBaseId);
        trans.commit();
        return dataBase;
    }

    public void deleteCompanyDatabase(CompanyDatabase dataBase) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        delete(dataBase);
        trans.commit();
    }
}
