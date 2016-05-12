package co.refiere.resources;
import javax.json.JsonObject;
import javax.json.JsonString;

import java.io.StringReader;

import javax.json.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import co.refiere.models.Company;
import co.refiere.models.SimpleUser;
import co.refiere.models.UserRoles;
import co.refiere.resources.base.LoginRequest;
import co.refiere.services.AuthenticationService;

@Path("v1/auth")
public class AuthenticationResource {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response authenticate(LoginRequest credentials) {

        String response = "{\"RoleIdentifier\": \"%s\", \"status\":\"%s\", \"company\": \"%s\"}";
        
        AuthenticationService authService = new AuthenticationService();
        System.out.println("-----------Finding user by login: "+credentials.getLogin());
        SimpleUser user = authService.findUserByLogin(credentials.getLogin());
        System.out.println("User Found!");
        System.out.println(user);
        
        if (user == null)
            return Response.status(Status.UNAUTHORIZED).entity(javax.ws.rs.client.Entity.json("{\"Error \": \"User not found \"}")).build();
        
        //UserRoles roles = user.getUserRoles();
        UserRoles userRole = user.getUserRoles();
        if(userRole == null)
            return Response.status(Status.NOT_FOUND).entity(javax.ws.rs.client.Entity.json("{\"Error \": \"UserRole not found \"}")).build();
        
        Company company = (Company) user.getUserCompanies().toArray()[0];
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
        System.out.println("AQUIIIIIII22222222222");
        System.out.println(response);
        
        JsonObject JsonData = Json.createReader(new StringReader(response)).readObject();

        return Response.status(200).entity(JsonData).build();
    }
    
}
