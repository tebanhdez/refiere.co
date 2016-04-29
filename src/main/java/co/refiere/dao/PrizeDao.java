package co.refiere.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import co.refiere.models.Prize;
import co.refiere.models.PrizeHome;
import co.refiere.resources.util.HibernateUtil;

public class PrizeDao extends PrizeHome {

    private static final Log log = LogFactory.getLog(PrizeDao.class);
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
    
    public void save(Prize prize){
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        persist(prize);
        trans.commit();
    }
    
    public Prize findPrizeById(int id) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        Prize instance = findById(id);
        trans.commit();
        return instance;
    }
}
