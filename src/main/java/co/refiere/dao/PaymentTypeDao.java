package co.refiere.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import co.refiere.models.PaymentType;
import co.refiere.models.PaymentTypeHome;
import co.refiere.resources.util.HibernateUtil;

public class PaymentTypeDao extends PaymentTypeHome {

    private static final Log log = LogFactory.getLog(PaymentTypeDao.class);
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
    
    public void save(PaymentType paymentType){
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        persist(paymentType);
        trans.commit();
    }
    
    public PaymentType findPaymentTypeById(int id) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        PaymentType instance = (PaymentType) findById(id);
        trans.commit();
        return instance;
    }
}
