package co.refiere.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

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
}
