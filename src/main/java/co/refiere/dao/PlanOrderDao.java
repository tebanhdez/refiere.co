package co.refiere.dao;

import co.refiere.models.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import co.refiere.resources.util.HibernateUtil;

import java.util.List;

public class PlanOrderDao extends PlanOrderHome {
    private static final Log log = LogFactory.getLog(PlanOrderDao.class);
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
        PlanOrder instance = session.get(PlanOrder.class, id);
        trans.commit();
        return instance;
    }

    public List<PlanOrder> findPlanOrdersByCompanyId(int companyId){
        log.debug("getting PlanOrder instances by companyId: " + companyId);
        try {
            Session session = sessionFactory.openSession();
            org.hibernate.Transaction trans= session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close.");
            Query query = session.createQuery("from PlanOrder where company_id = :companyId");
            query.setParameter("companyId", companyId);
            java.util.List results = query.list();
            trans.commit();
            if (results != null && results.size() > 0) {
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
    
    public List<PlanOrder> findAllPlanOrders(){
        log.debug("getting all PlanOrder instances");
        try {
            Session session = sessionFactory.openSession();
            org.hibernate.Transaction trans= session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close.");
            Query query = session.createQuery("from PlanOrder");
            java.util.List results = query.list();
            trans.commit();
            if (results != null && results.size() > 0) {
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
