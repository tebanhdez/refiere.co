package co.refiere.resources.util;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NoContentException;
import javax.ws.rs.core.Response;

import co.refiere.resources.base.PlanOrderObjectData;
import co.refiere.services.PlanOrderService;

@Path("v1/order")
public class OrderResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getOrders() {
        String response = "{\"status\": \"%s\", %s}";
        try {
            PlanOrderService planOrderService = new PlanOrderService();
            List<co.refiere.resources.base.PlanOrderObjectData> planOrders = planOrderService.getAllPlanOrders();
            GenericEntity<List<PlanOrderObjectData>> list = new GenericEntity<List<PlanOrderObjectData>>(planOrders) {};
            return Response.ok(list).build();
        } catch (NoContentException e) {
            response = String.format(response, "FAIL", e.getMessage());
        } catch (NullPointerException exception) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(200).entity(response).build();
    }
}
