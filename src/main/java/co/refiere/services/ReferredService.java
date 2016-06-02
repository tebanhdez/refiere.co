package co.refiere.services;

import java.util.List;

import javax.ws.rs.core.NoContentException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.tool.schema.internal.exec.GenerationTargetToScript;

import co.refiere.models.ReferencesCodes;
import co.refiere.resources.base.ReferredObjectData;

public class ReferredService {
    private static final Log LOGGER = LogFactory.getLog(ReferredService.class);

    public java.util.List<ReferredObjectData> getAllReferred() throws NoContentException{
        ReferencesCodesDao referecesCodesDao = ReferencesCodesDao();
        List<ReferredObjectData> referrals = getSimplifiedReferred(referecesCodesDao.findAllReferred());
        if(referrals == null){
            LOGGER.error("Referrals not found");
            throw new NoContentException("\"Error \": \"Referrals not found\"");
        }
        return referrals;
    } 


}
