package co.refiere.models;

import org.junit.Assert;
import org.junit.Test;

import co.refiere.dao.RefierePlanDao;

public class RefierePlanTest {
    
    @Test
    public void testRefierePlanStructure() {

        RefierePlanDao planDao = new RefierePlanDao();

        Plan basic = planDao.findByPlanById(DefaultPlan.BASIC.getPlanId());
        Plan enterprice = planDao.findByPlanById(DefaultPlan.ENTERPRICE.getPlanId());
        Plan corporate = planDao.findByPlanById(DefaultPlan.CORPORATE.getPlanId());

        Assert.assertNotNull("Basic plan not found", basic);
        Assert.assertNotNull("Enterprice plan not found", enterprice);
        Assert.assertNotNull("Corporate plan not found", corporate);

        Assert.assertEquals("Basic plan expect to have bimonthly campaigns lapse", basic.getLapseByCampaignLapseRef().getName(), DefaultLapse.BIMONTHLY.getLapseName());
        Assert.assertEquals("Enterprice plan expect to have bimonthly campaigns lapse", enterprice.getLapseByCampaignLapseRef().getName(), DefaultLapse.BIMONTHLY.getLapseName());
        Assert.assertEquals("Corporate plan expect to have monthly campaigns lapse", corporate.getLapseByCampaignLapseRef().getName(), DefaultLapse.MONTHLY.getLapseName());

        Assert.assertEquals("Basic plan expect to have monthly report lapse", basic.getLapseByReportLapseId().getName(), DefaultLapse.MONTHLY.getLapseName());
        Assert.assertEquals("Enterprice plan expect to have monthly report lapse", basic.getLapseByReportLapseId().getName(), DefaultLapse.MONTHLY.getLapseName());
        Assert.assertEquals("Corporate plan expect to have monthly report lapse", basic.getLapseByReportLapseId().getName(), DefaultLapse.MONTHLY.getLapseName());

    }
}