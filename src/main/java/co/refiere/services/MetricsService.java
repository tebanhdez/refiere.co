package co.refiere.services;

import java.util.List;

import javax.ws.rs.core.NoContentException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.tool.schema.internal.exec.GenerationTargetToScript;

import co.refiere.dao.CampaignDao;
import co.refiere.dao.ReferencesCodesDao;
import co.refiere.models.ReferencesCodes;
import co.refiere.resources.base.ReferredObjectData;

public class MetricsService {
    private static final Log LOGGER = LogFactory.getLog(MetricsService.class);
    private ReferencesCodesDao referencesCodesDao;
    private CampaignDao campaignDao ;
    
    public MetricsService(){
        referencesCodesDao = new ReferencesCodesDao();
        campaignDao = new CampaignDao();
    }
    
    public int getReferredAmount() {
        return referencesCodesDao.findAll().size();
    }

    public int getCompanyAmount(int companyId) {
        return campaignDao.getCampaignsByUserId(companyId).size();
    }

    


}
