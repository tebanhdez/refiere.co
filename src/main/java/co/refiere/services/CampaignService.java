package co.refiere.services;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import co.refiere.resources.base.CampaignRequest;

public class CampaignService {

	public Response createCampaign(CampaignRequest campaign) {
		String jsonResponse = "{\"status\":\"OK\", \"campaignId\": %d}";
		
		return Response.ok(String.format(jsonResponse, 1), MediaType.APPLICATION_JSON).build();
	}

}
