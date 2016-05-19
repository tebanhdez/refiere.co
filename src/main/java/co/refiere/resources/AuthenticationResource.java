package co.refiere.resources;
import javax.json.JsonObject;
import javax.json.JsonString;

import java.io.StringReader;

import javax.json.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import co.refiere.models.Company;
import co.refiere.models.SimpleUser;
import co.refiere.models.UserCompany;
import co.refiere.models.UserRoles;
import co.refiere.resources.base.LoginRequest;
import co.refiere.services.AuthenticationService;

@Path("v1/auth")
public class AuthenticationResource {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response authenticate(LoginRequest credentials) {

        String response = "{\"RoleIdentifier\": \"%s\", \"status\":\"%s\", \"company\": %s}";
        
        AuthenticationService authService = new AuthenticationService();
        SimpleUser user = authService.findUserByLogin(credentials.getLogin());
        
        if (user == null)
            return Response.status(Status.UNAUTHORIZED).entity(javax.ws.rs.client.Entity.json("{\"Error \": \"User not found \"}")).build();
        
        UserRoles userRole = user.getUserRoles();
        if(userRole == null)
            return Response.status(Status.NOT_FOUND).entity(javax.ws.rs.client.Entity.json("{\"Error \": \"UserRole not found \"}")).build();

        UserCompany userCompanyRelation = (UserCompany)user.getUserCompanies().toArray()[0];

        Company company = userCompanyRelation.getCompany();
        if(company == null)
            return Response.status(Status.NOT_FOUND).entity(javax.ws.rs.client.Entity.json("{\"Error \": \"User not associated with any company \"}")).build();
  
        switch (userRole.getRoleIdentifier()) {
        case "USER":
          response = String.format(response, user.getUserRoles().getRoleIdentifier(), "OK", company.toJson());
          break;
        case "ADMIN":          
          response = String.format(response, user.getUserRoles().getRoleIdentifier(), "OK", company.toJson());
          break;

        default:
          break;
        }
        response = String.format(response, user.getUserRoles().getRoleIdentifier(), "OK", "");
        
        return Response.status(200).entity(response).build();
    }
    
}
