package co.refiere.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NoContentException;
import javax.ws.rs.core.Response;

import co.refiere.resources.base.ReferredObjectData;
import co.refiere.services.MetricsService;

@Path("v1/metrics")
public class MetricsResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("referredAmount")
    public Response getAllReferred(){
        String response = "{\"referredAmount\": \"%s\"}";
        MetricsService referredService = new MetricsService();
        int referralsAmount = referredService.getReferredAmount();
        response= String.format(response, referralsAmount);
        return Response.status(200).entity(response).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/company/{companyId}/campaignsAmount")
    public Response getCampaignsAmount(@PathParam("companyId") int companyId){
        String response = "{\"campaignsAmount\": \"%s\"}";
        MetricsService metricsService = new MetricsService();
        int referralsAmount = metricsService.getCompanyAmount(companyId);
        response= String.format(response, referralsAmount);
        return Response.status(200).entity(response).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/company/{companyId}/campaignsAmountPrize")
    public Response getCampaignsAmountPrize(@PathParam("companyId") int companyId){
        String response = "{\"campaignsAmountPrize\": \"%s\"}";
        MetricsService metricsService = new MetricsService();
        int referralsAmount = metricsService.getCompanyAmountPrize(companyId);
        response= String.format(response, referralsAmount);
        return Response.status(200).entity(response).build();
    }

}
