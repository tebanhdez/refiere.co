package co.refiere.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import co.refiere.models.Currency;
import co.refiere.models.CurrencyHome;
import co.refiere.models.PaymentType;
import co.refiere.resources.util.HibernateUtil;

public class CurrencyDao extends CurrencyHome {

    private static final Log log = LogFactory.getLog(CurrencyDao.class);
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
    
    public void save(Currency currency){
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        persist(currency);
        trans.commit();
    }
    
    public Currency findCurrencyTypeById(int id) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        Currency instance = (Currency) findById(id);
        trans.commit();
        return instance;
    }
}
