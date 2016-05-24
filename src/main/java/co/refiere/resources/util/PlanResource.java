package co.refiere.resources.util;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NoContentException;
import javax.ws.rs.core.Response;

import co.refiere.resources.base.PlanObjectData;
import co.refiere.services.PlanService;

@Path("v1/plan")
public class PlanResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllPlans() {
        String response = "{\"status\": \"%s\", %s}";
        try {
            PlanService planService = new PlanService();
            List<co.refiere.resources.base.PlanObjectData> plans = planService.getAllPlans();
            GenericEntity<List<PlanObjectData>> list = new GenericEntity<List<PlanObjectData>>(plans) {};
            return Response.ok(list).build();
        } catch (NoContentException e) {
            response = String.format(response, "FAIL", e.getMessage());
        } catch (NullPointerException exception) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(200).entity(response).build();
    }
}
