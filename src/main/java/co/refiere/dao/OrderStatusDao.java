package co.refiere.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import co.refiere.models.OrderStatus;
import co.refiere.models.OrderStatusHome;
import co.refiere.models.PlanOrder;
import co.refiere.resources.util.HibernateUtil;

public class OrderStatusDao extends OrderStatusHome {

    private static final Log log = LogFactory.getLog(OrderStatusDao.class);
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
    
    public void save(OrderStatus orderStatus){
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans = session.beginTransaction();
        persist(orderStatus);
        trans.commit();
    }

    public OrderStatus findOrderStatusById(int id) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans = session.beginTransaction();
        OrderStatus instance = findById(id);
        trans.commit();
        return instance;
    }
}
