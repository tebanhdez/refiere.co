package co.refiere.dao;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import co.refiere.models.Company;
import co.refiere.models.CompanyDatabase;
import co.refiere.models.ReferencesCodes;
import co.refiere.models.ReferencesCodesHome;
import co.refiere.resources.util.HibernateUtil;

public class ReferencesCodesDao extends ReferencesCodesHome{
    private static final Log log = LogFactory.getLog(ReferencesCodesDao.class);
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

    public void save(ReferencesCodes referencesCodes){
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        persist(referencesCodes);
        trans.commit();
    }

    public void deleteReferencesCodes(ReferencesCodes referencesCodes) {
        Session session = sessionFactory.getCurrentSession();
        org.hibernate.Transaction trans= session.beginTransaction();
        delete(referencesCodes);
        trans.commit();
    }

    public List<ReferencesCodes> findAll() {
        log.debug("getting references_codes instances");
        try {
            Session session = sessionFactory.getCurrentSession();
            org.hibernate.Transaction trans= session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close.");
            Query query = session.createQuery("from ReferencesCodes");
            java.util.List<ReferencesCodes> results = query.list();
            trans.commit();
            return results;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<ReferencesCodes> findReferalCode(String referenceCode){
        log.debug("getting references_codes instances");
        try {
            Session session = sessionFactory.getCurrentSession();
            org.hibernate.Transaction trans= session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close.");
            Query query = session.createQuery("from ReferencesCodes where code=:referenceCode");
            query.setParameter("referenceCode", referenceCode);
            java.util.List<ReferencesCodes> results = query.list();
            trans.commit();
            return results;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<ReferencesCodes> findCampaignReferalCodesByPerson(int campaignId, int personId){
        log.debug("getting references_codes instances");
        try {
            Session session = sessionFactory.getCurrentSession();
            org.hibernate.Transaction trans= session.beginTransaction();
            if(trans.getStatus().equals(TransactionStatus.NOT_ACTIVE))
                log.debug(" >>> Transaction close.");
            Query query = session.createQuery("from ReferencesCodes where referrer_id = :personId");
            query.setInteger("personId", personId);
            java.util.List<ReferencesCodes> results = query.list();
            trans.commit();
            List<ReferencesCodes> filteredResult = getReferencesCodesByCampaign(campaignId, results);
            return filteredResult;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    private List<ReferencesCodes> getReferencesCodesByCampaign(final int campaignId, List<ReferencesCodes> results) {
        return results.stream()
                        .filter(code -> code.getId().getCampaignId() == campaignId).collect(Collectors.toList());
    }
}
