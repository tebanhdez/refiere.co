package co.refiere.resources;



import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import co.refiere.resources.base.NewUserRequest;
import co.refiere.services.ReferalCodeService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("v1/redeemCode")
public class RedeemCodeResource {

	@Path("/registerUser")
	@POST
	public Response registerUser(NewUserRequest newUser){
		ReferalCodeService referalService = new ReferalCodeService();
        String response = "{\"status\": \"%s\", %s}";
        try {
            response = String.format(response, "OK", "\"result\":" + referalService.    .createCompanyAccount(company));
        } catch (NoContentException e) {
            response = String.format(response, "FAIL",e.getMessage());
        } catch (NullPointerException exception) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
		return referalService.registerNewPerson(newUser);

	}
	
}
