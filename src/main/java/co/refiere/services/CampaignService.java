package co.refiere.services;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.refiere.dao.CampaignDao;
import co.refiere.models.Campaign;
import co.refiere.resources.base.CampaignRequest;

public class CampaignService {

    public Response createCampaign(CampaignRequest campaign) {
        String jsonResponse = "{\"status\":\"OK\", \"campaignId\": %d}";
        CampaignDao campaignDao= new CampaignDao();

        Campaign campaignModel = new Campaign();
        campaignModel.setName(campaign.getCampaignName());

        //campaignModel.setCompany(null);
        campaignModel.setPrizeByPrizeForRefereeId(null);
        campaignModel.setPrizeByPrizeForRefiereId(null);
        campaignModel.setPrizeForReferee(campaign.getPrizeForRefieree());
        campaignModel.setPrizeForRefiere(campaign.getPrizeForRefiere());

        campaignDao.save(campaignModel);
        return Response.ok(String.format(jsonResponse, 1), MediaType.APPLICATION_JSON).build();
	}

}
