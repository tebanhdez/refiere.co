package co.refiere.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import co.refiere.models.Plan;
import co.refiere.models.PlanHome;
import co.refiere.models.UserRoles;
import co.refiere.resources.util.HibernateUtil;

public class RefierePlanDao extends PlanHome {

    private static final Log log = LogFactory.getLog(RefierePlanDao.class);
    private final SessionFactory sessionFactory = getSessionFactory();

    public RefierePlanDao() {
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
    
    public void save(Plan plan){
        log.debug("saving RefiereLapse");
        try {
            Session session = sessionFactory.openSession();
            org.hibernate.Transaction trans= session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close.");
            session.saveOrUpdate(plan);
            trans.commit();
            log.debug("persist successful");
        } catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public Plan findByPlanById(int id) {
        log.debug("getting UserRoles instance with login: " + id);
        try {
            Session session = sessionFactory.openSession();
            org.hibernate.Transaction trans= session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close.");
            Plan instance = (Plan) session.get("co.refiere.models.Plan", id);
            trans.commit();
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
