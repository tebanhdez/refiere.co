package co.refiere.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.NoContentException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import co.refiere.dao.PlanOrderDao;
import co.refiere.models.PlanOrder;
import co.refiere.resources.base.PlanOrderObjectData;

public class PlanOrderService {
    
    private static final Log LOGGER = LogFactory.getLog(PlanOrderService.class);
        
    public java.util.List<PlanOrderObjectData> getAllPlanOrders() throws NoContentException{
        PlanOrderDao planOrderDao = new PlanOrderDao();
        List<PlanOrderObjectData> planOrders = getPlanOrders(planOrderDao.findAllPlanOrders());
        if(planOrders == null){
            LOGGER.error("Plan Orders not found");
            throw new NoContentException("\"Error \": \"Plan Orders not found\"");
        }
        return planOrders;
    }
    
    private List<PlanOrderObjectData> getPlanOrders(List<PlanOrder> allPlanOrders) {
        List<PlanOrderObjectData> simplePlanOrders = new ArrayList<>();
        for(PlanOrder planOrderInstance : allPlanOrders){
            PlanOrderObjectData planOrder = new PlanOrderObjectData(planOrderInstance.getId());
            planOrder.setPlanId(planOrderInstance.getId());
            planOrder.setPlanOrderCompanyId(planOrderInstance.getCompany().getId());
            planOrder.setStatusId(planOrderInstance.getOrderStatus().getId());
            planOrder.setPaymentId(planOrderInstance.getPayment().getId());
            planOrder.setPlanId(planOrderInstance.getPlan().getId());
            planOrder.setApprovedBy(planOrderInstance.getApprovedBy());
            planOrder.setStartDate((Date) planOrderInstance.getStartDate());
            planOrder.setEndDate((Date) planOrderInstance.getEndDate());
            simplePlanOrders.add(planOrder);
        }
        return simplePlanOrders;
    }

}
