package co.refiere.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.NoContentException;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import co.refiere.dao.RefierePlanDao;
import co.refiere.models.Plan;
import co.refiere.resources.base.PlanObjectData;

public class PlanService {

    private static final Log LOGGER = LogFactory.getLog(PlanService.class);
    
    public java.util.List<PlanObjectData> getAllPlans() throws NoContentException{
        RefierePlanDao planDao = new RefierePlanDao();
        List<PlanObjectData> plans = getSimplifiedPlans(planDao.findAllPlan());
        if(plans == null){
            LOGGER.error("Plans not found");
            throw new NoContentException("\"Error \": \"Plans not found\"");
        }
        return plans;
    }
    
    private List<PlanObjectData> getSimplifiedPlans(List<Plan> allPlans) {
        List<PlanObjectData> simplePlans = new ArrayList<>();
        RefierePlanDao planDao = new RefierePlanDao();
        for(Plan planInstance : allPlans){
            PlanObjectData plan = new PlanObjectData(planInstance.getId());
            plan.setName(planInstance.getName());
            plan.setReportsPeriodicity(buildPeriodicityString(1, "Reporte(s)", planInstance.getLapseByReportLapseId().getName()));
            plan.setCampaignPeriodicity(buildPeriodicityString(planInstance.getCampaignAmount(), "Campaña(s)", planInstance.getLapseByReportLapseId().getName()));
            plan.setPlanPrice(Float.valueOf(planInstance.getSalesPercentaje().floatValue()));
            simplePlans.add(plan);
        }
        return simplePlans;
    }

    private String buildPeriodicityString(int periodicityValue, String periodicity, String periodicityName) {
        String reportPeriodicity = "%d %s %s";
        return String.format(reportPeriodicity, periodicityValue, periodicity, periodicityName);
    }
}
