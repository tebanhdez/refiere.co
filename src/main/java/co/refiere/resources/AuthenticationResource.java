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
        
        SimpleUser user = authService.findUserByLogin(credentials.getLogin());
        
        if (user == null)
            return Response.status(Status.UNAUTHORIZED).entity(javax.ws.rs.client.Entity.json("{\"Error \": \"User not found \"}")).build();
        
        //UserRoles roles = user.getUserRoles();

        switch (user.getUserRoles().getRoleIdentifier()) {
        case "USER":
          Company company = (Company) user.getUserCompanies().toArray()[0];
          response = String.format(response, user.getUserRoles().getRoleIdentifier(), "OK", company.toJson());
          break;

        default:
          break;
        }
        response = String.format(response, user.getUserRoles().getRoleIdentifier(), "OK", "");
        
        //JsonObject obj = new JsonParser().parse(JsonString).getAsJsonObject();
        JsonObject JsonData = Json.createReader(new StringReader(response)).readObject();

        //return Response.status(200)/*.entity(javax.ws.rs.client.Entity.json(response))*/.build();
        //return Response.status(200).jsonData.build();
        return Response.status(200).entity(JsonData).build();
    }
    
}
