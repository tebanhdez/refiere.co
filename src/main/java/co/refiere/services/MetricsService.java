package co.refiere.services;

import java.util.List;

import javax.ws.rs.core.NoContentException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.tool.schema.internal.exec.GenerationTargetToScript;

import co.refiere.dao.CampaignDao;
import co.refiere.dao.PersonDao;
import co.refiere.dao.ReferencesCodesDao;
import co.refiere.models.ReferencesCodes;
import co.refiere.resources.base.ReferredObjectData;

public class MetricsService {
    private static final Log LOGGER = LogFactory.getLog(MetricsService.class);
    private ReferencesCodesDao referencesCodesDao;
    private CampaignDao campaignDao ;
    private PersonDao personDao;

    public MetricsService(){
        referencesCodesDao = new ReferencesCodesDao();
        campaignDao = new CampaignDao();
        personDao = new PersonDao();
    }

    public int getReferredAmount() {
        return referencesCodesDao.findAll().size();
    }

    public int getCompanyAmount(int companyId) {
        return campaignDao.getCampaignsByUserId(companyId).size();
    }
    
    public int getCompanyRedeemedCodes(int companyId) {
        return campaignDao.getReferres(companyId).size();
    }
    
    public int getCompanyNotRedeemedCodes(int companyId) {
        return campaignDao.getRedeemedCodes(companyId).size();
    }

    public int getCompanyAmountPrize(int companyId) {
        return 2*campaignDao.getCampaignsByUserId(companyId).size();
    }

}
