package co.refiere.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

import co.refiere.models.Currency;
import co.refiere.models.Person;
import co.refiere.models.PersonHome;
import co.refiere.models.Prize;
import co.refiere.resources.util.HibernateUtil;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import co.refiere.models.Person;
import co.refiere.models.PersonHome;
import co.refiere.resources.util.HibernateUtil;


public class PersonDao extends PersonHome {
    
    private static final Log log = LogFactory.getLog(PersonDao.class);
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

    public StatelessSession getStatelessSession(){
        return sessionFactory.openStatelessSession();
    }
    
    public void save(Person person){
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        persist(person);
        trans.commit();
    }
    
    public Person findPersonsByDatabaseId(int id) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        Person instance = findById(id);
        trans.commit();
        return instance;
    }
}
