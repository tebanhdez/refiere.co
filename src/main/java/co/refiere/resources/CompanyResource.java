package co.refiere.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.refiere.resources.base.CompanyRequest;
import co.refiere.services.CompanyService;

@Path("v1/company")
public class CompanyResource {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response createCompany(CompanyRequest company) {
        CompanyService companyService = new CompanyService();
        return companyService.createCompanyAccount(company);
    }
}
