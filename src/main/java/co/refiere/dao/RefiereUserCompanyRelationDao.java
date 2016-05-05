package co.refiere.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import co.refiere.models.SimpleUser;
import co.refiere.models.UserCompany;
import co.refiere.models.UserCompanyHome;
import co.refiere.resources.util.HibernateUtil;

public class RefiereUserCompanyRelationDao extends UserCompanyHome {

    private static final Log log = LogFactory.getLog(RefiereUserCompanyRelationDao.class);
    private final SessionFactory sessionFactory = getSessionFactory();

    public RefiereUserCompanyRelationDao() {
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
    
    public void save(UserCompany company){
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        persist(company);
        trans.commit();
    }
    
    public UserCompany findUserCompanyRelByUserIdAndCompanyId(int userId, int companyId) {
        log.debug("getting UserCompanyRelation instance with UserId: " + userId + " and CompanyId: " + companyId);
        try {
            Session session = sessionFactory.openSession();
            org.hibernate.Transaction trans= session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close.");
            Query query = session.createQuery("from UserCompany where user_id = :userId and company_id = :companyId");
            query.setParameter("userId", userId);
            query.setParameter("companyId", companyId);
            java.util.List results = query.list();
            trans.commit();
            UserCompany instance = (results != null && results.size() == 1) ? (UserCompany) results.get(0) : null;
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
}
