package co.refiere.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

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
        session.saveOrUpdate(person);
        trans.commit();
    }

    public Person findPersonsById(int id) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        Person instance = findById(id);
        trans.commit();
        return instance;
    }
    
    public int getPersonsAmountByCampaign(int idCampaign) {
        log.debug("getting references_codes instances");
        try {
            Session session = sessionFactory.getCurrentSession();
            org.hibernate.Transaction trans= session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close.");
            Query query = session.createQuery("from Person where company_database_id=:idCampaign");
            query.setParameter("idCampaign", idCampaign);
            java.util.List<Person> results = query.list();
            trans.commit();
            return results.size();
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}
