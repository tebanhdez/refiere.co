package co.refiere.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import co.refiere.resources.base.CampaignRequest;
import co.refiere.services.CampaignService;

@Path("v1/campaign")
public class CampaignResource {

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        public Response createCampaign(CampaignRequest campaign) {
            CampaignService campaignService = new CampaignService();
            return campaignService.createCampaign(campaign);
        }
}
