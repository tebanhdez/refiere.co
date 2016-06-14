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
    
    public List<CompanyDatabase> findAllDatabases(){
        log.debug("getting Company_database's instances");
        try {
            Session session = sessionFactory.openSession();
            org.hibernate.Transaction trans = session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close");
            Query query = session.createQuery("from CompanyDatabase");
            java.util.List results = query.list();
            trans.commit();
            if (results !=  null && !results.isEmpty()) {
                log.debug("get successful, instance found");
            } else {
                log.debug("get successful, no instance found");
            }
            return results;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public List<CompanyDatabase> findAllDatabasesByCompany(String userName){
        log.debug("getting Company_database's instances");
        try {
            Session session = sessionFactory.openSession();
            org.hibernate.Transaction trans = session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close");
            Query query = session.createSQLQuery("select company_database.id as id, company_database.name as name, company_database.company_id as company_id from simple_user INNER JOIN user_company ON simple_user.id = user_company.user_id INNER JOIN company ON user_company.company_id = company.id INNER JOIN company_database ON company.id = company_database.company_id AND simple_user.login = :userName")
                    .addEntity(CompanyDatabase.class)
                    .setParameter("userName", userName);
            java.util.List results = query.list();
            trans.commit();
            if (results !=  null && !results.isEmpty()) {
                log.debug("get successful, instance found");
            } else {
                log.debug("get successful, no instance found");
            }
            return results;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}
