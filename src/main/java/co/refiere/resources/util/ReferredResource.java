package co.refiere.resources.util;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NoContentException;
import javax.ws.rs.core.Response;

import co.refiere.resources.base.ReferredObjectData;
import co.refiere.services.ReferredService;

@Path("v1/referred")
public class ReferredResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllReferred(){
        String response = "{\"status\": \"%s\", %s}";
        try {
            ReferredService referredService = new ReferredService();
            List<co.refiere.resources.base.ReferredObjectData> referrals = referredService.getAllReferred();
            GenericEntity<List<ReferredObjectData>> list = new GenericEntity<List<ReferredObjectData>>(referrals) {};
            return Response.ok(list).build();
        }catch (NoContentException e) {
            response = String.format(response, "FAIL", e.getMessage());
        }catch (NullPointerException exception) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(200).entity(response).build();

    }
}
