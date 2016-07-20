package co.refiere.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import co.refiere.models.Campaign;
import co.refiere.models.CampaignHome;
import co.refiere.models.ReferencesCodes;
import co.refiere.resources.util.HibernateUtil;

public class CampaignDao extends CampaignHome {

    private static final Log log = LogFactory.getLog(CampaignDao.class);
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

    public void save(Campaign campaign){
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        persist(campaign);
        trans.commit();
    }

    public Campaign getCampaigById(int id) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        Campaign instance = findById(id);
        trans.commit();
        return instance;
    }

    public void deleteCampaign(Campaign campaign) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        delete(campaign);
        trans.commit();
    }

    public List<Campaign> getCampaignsByUserId(int companyId) {
        log.debug("getting references_codes instances");
        try {
            Session session = sessionFactory.getCurrentSession();
            org.hibernate.Transaction trans= session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close.");
            Query query = session.createQuery("from Campaign where company_id=:companyId");
            query.setParameter("companyId", companyId);
            java.util.List<Campaign> results = query.list();
            trans.commit();
            return results;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

}
