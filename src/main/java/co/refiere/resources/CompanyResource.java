package co.refiere.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NoContentException;
import javax.ws.rs.core.Response;

import co.refiere.resources.base.CompanyRequest;
import co.refiere.services.CompanyService;

@Path("v1/company")
public class CompanyResource {
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response createCompany(CompanyRequest company) {
        CompanyService companyService = new CompanyService();
        String response = "{\"status\": \"%s\", %s}";
        try {
            response = String.format(response, "OK", "\"result\":" + companyService.createCompanyAccount(company));
        } catch (NoContentException e) {
            response = String.format(response, "FAIL",e.getMessage());
        } catch (NullPointerException exception) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(200).entity(response).build();
    }
}
