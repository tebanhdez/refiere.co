package co.refiere.resources;



import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import co.refiere.resources.base.NewUserRequest;
import co.refiere.services.ReferalCodeService;

@Path("v1/redeemCode")
public class RedeemCodeResource {
	
	// POST - User register and generates code
	@Path("/registerUser")
	@POST
	public Response registerUser(NewUserRequest newUser){
		ReferalCodeService referalService = new ReferalCodeService();
		return referalService.registerNewPerson(newUser);
	}
	
	// POST - As a company user I read/redeem a code
	
}
