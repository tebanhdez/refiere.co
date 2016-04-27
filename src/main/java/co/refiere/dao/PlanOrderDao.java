package co.refiere.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import co.refiere.models.PaymentType;
import co.refiere.models.PlanOrder;
import co.refiere.models.PlanOrderHome;
import co.refiere.resources.util.HibernateUtil;

public class PlanOrderDao extends PlanOrderHome {
    private static final Log log = LogFactory.getLog(RefierePlanDao.class);
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
    
    public void save(PlanOrder planOrder){
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        session.save(planOrder);
        trans.commit();
    }
    
    public void update(PlanOrder planOrder){
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        session.saveOrUpdate(planOrder);
        trans.commit();
    }
    
    public PlanOrder findPlanOrderById(int id) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        PlanOrder instance = (PlanOrder) session.get("co.refiere.models.PlanOrder", id);
        trans.commit();
        return instance;
    }
}
