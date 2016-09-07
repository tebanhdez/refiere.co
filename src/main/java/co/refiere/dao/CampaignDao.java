package co.refiere.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import co.refiere.models.Campaign;
import co.refiere.models.CampaignHome;
import co.refiere.models.Prize;
import co.refiere.resources.base.ClientListObjectData;
import co.refiere.resources.base.PrizeObjectData;
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
    
    public List<Campaign> getReferres(int companyId) {
        log.debug("getting references_codes instances");
        try {
            Session session = sessionFactory.getCurrentSession();
            org.hibernate.Transaction trans= session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close.");
            Query query = session.createSQLQuery("select rf.campaign_id, rf.person_id, rf.referrer_id, rf.code from campaign c join references_codes rf on c.id = rf.campaign_id where rf.referrer_id IS NULL and c.company_id =:companyId");
            query.setParameter("companyId", companyId);
            java.util.List<Campaign> results = query.list();
            trans.commit();
            return results;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public List<Campaign> getRedeemedCodes(int companyId) {
        log.debug("getting references_codes instances");
        try {
            Session session = sessionFactory.getCurrentSession();
            org.hibernate.Transaction trans= session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close.");
            Query query = session.createSQLQuery("select rf.campaign_id, rf.person_id, rf.referrer_id, rf.code from campaign c join references_codes rf on c.id = rf.campaign_id where rf.referrer_id IS NOT NULL and c.company_id =:companyId");
            query.setParameter("companyId", companyId);
            java.util.List<Campaign> results = query.list();
            trans.commit();
            return results;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public Iterator getCompanyClientList(int campaignId) {
        log.debug("getting company client instances");
        try {
            Session session = sessionFactory.getCurrentSession();
            org.hibernate.Transaction trans= session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close.");
            Query query = session.createSQLQuery("select p.id, p.identification_card_number, p.name, p.last_name, tmp.count, rf.code, pr.description, c.prize_for_refiere from person p join (select rc.referrer_id, count(rc.referrer_id) as count from references_codes rc where referrer_id is not null group by rc.referrer_id) as tmp on tmp.referrer_id = p.id join references_codes rf on rf.person_id = tmp.referrer_id join campaign c on rf.campaign_id = c.id join prize pr on pr.id = c.prize_for_refiere_id where c.id = :campaignId");
            query.setParameter("campaignId", campaignId);
            Iterator<?> results = query.list().iterator();
            trans.commit();
            return results;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public List<ClientListObjectData> getSimplifiedClientList(Iterator queryResult) {
        List<ClientListObjectData> simpleClientList = new ArrayList<>();
        while(queryResult.hasNext()){
            ClientListObjectData client = new ClientListObjectData();
            Object[] tuple = (Object[]) queryResult.next();
            client.setId(tuple[0].toString());
            client.setIdentificationCardNumber(tuple[1].toString());
            client.setName(tuple[2].toString());
            client.setLastName(tuple[3].toString());
            client.setCount(tuple[4].toString());
            client.setCode(tuple[5].toString());
            client.setDescription(tuple[6].toString());
            client.setPrizeForRefiere(tuple[7].toString());
            simpleClientList.add(client);
        }
        return simpleClientList;
    }

    public StatelessSession getStatelessSession() {
        return sessionFactory.openStatelessSession();
    }

}
