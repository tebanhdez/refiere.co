package co.refiere.resources;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NoContentException;
import javax.ws.rs.core.Response;

import co.refiere.resources.base.NewUserRequest;
import co.refiere.services.ReferalCodeService;


@Path("v1/redeemCode")
public class RedeemCodeResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/registerUser")
    public Response registerUser(NewUserRequest newUser){
        ReferalCodeService referalService = new ReferalCodeService();
        String response = "{\"status\": \"%s\", %s}";
        try {
            response = String.format(response, "OK", "\"result\":" + referalService.registerNewPerson(newUser));
        } catch (NoContentException | ForbiddenException e) {
            response = String.format(response, "FAIL",e.getMessage());
        } catch (NullPointerException exception) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(200).entity(response).build();
    }
}
