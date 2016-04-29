package co.refiere.resources;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NoContentException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import co.refiere.dao.OrderStatusDao;
import co.refiere.dao.PlanOrderDao;
import co.refiere.dao.RefiereCompanyDao;
import co.refiere.dao.RefierePlanDao;
import co.refiere.dao.RefiereUserCompanyRelationDao;
import co.refiere.dao.RefiereUserDao;
import co.refiere.models.Company;
import co.refiere.models.OrderStatus;
import co.refiere.models.Plan;
import co.refiere.models.PlanOrder;
import co.refiere.models.SimpleUser;
import co.refiere.models.UserCompany;
import co.refiere.resources.base.CompanyRequest;
import co.refiere.resources.base.PlanRequest;
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
