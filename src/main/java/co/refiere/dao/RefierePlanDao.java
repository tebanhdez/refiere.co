package co.refiere.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import co.refiere.models.Plan;
import co.refiere.models.PlanHome;
import co.refiere.models.SimpleUser;
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
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        session.saveOrUpdate(plan);
        trans.commit();
    }
    
    public Plan findByPlanById(int id) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        Plan instance = session.get(Plan.class, id);
        trans.commit();
        return instance;
    }

    public List<Plan> findAllPlan() {
        log.debug("getting RefierePlan's instances");
        try {
            Session session = sessionFactory.openSession();
            org.hibernate.Transaction trans= session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close.");
            Query query = session.createQuery("from Plan pl join fetch pl.lapseByReportLapseId join fetch pl.lapseByCampaignLapseRef");
            java.util.List results = query.list();
            trans.commit();
            if (results != null && !results.isEmpty()) {
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
