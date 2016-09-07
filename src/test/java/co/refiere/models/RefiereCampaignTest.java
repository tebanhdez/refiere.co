package co.refiere.models;

import org.junit.Assert;
import org.junit.Test;

import co.refiere.dao.CampaignDao;

public class RefiereCampaignTest {

    CampaignDao campaignDao = new CampaignDao();

    @Test
    public void testCreateRefiereCompany() {
        Assert.assertTrue(campaignDao.getSimplifiedClientList(campaignDao.getCompanyClientList(3)).size() > 0);
    }

}
