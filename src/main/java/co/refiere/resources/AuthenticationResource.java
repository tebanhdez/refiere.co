package co.refiere.resources;

import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NoContentException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import co.refiere.models.*;
import co.refiere.resources.base.LoginRequest;
import co.refiere.services.AuthenticationService;
import co.refiere.services.mailer.ResourceManager;

@Path("v1/auth")
public class AuthenticationResource {

    private static final Log LOGGER = LogFactory.getLog(AuthenticationResource.class);

    private static Properties properties = null;

    static{
        properties = new Properties();
        try {
                properties.load(ResourceManager.getResourceAsInputStream("refiere.properties"));
            } catch (IOException exception) {
                LOGGER.error("Error loading properties file", exception);
            }
    }

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
            return Response.status(Status.NO_CONTENT).entity(javax.ws.rs.client.Entity.json("{\"Error \": \"UserRole not found \"}")).build();

        try{
            switch (userRole.getRoleIdentifier()) {
                case "SYS":
                    Company refiereCorp = new Company();
                    refiereCorp.setName(properties.getProperty("refiere.company.name"));
                    refiereCorp.setAddress(properties.getProperty("refiere.company.address"));
                    refiereCorp.setEmail(properties.getProperty("refiere.company.email"));
                    refiereCorp.setPhone(properties.getProperty("refiere.company.phone"));
                    response = String.format(response, user.getUserRoles().getRoleIdentifier(), "OK", refiereCorp.toJson());
                    break;
                case "USER":
                    response = String.format(response, user.getUserRoles().getRoleIdentifier(), "OK", getCompany(user).toJson());
                    break;
                case "ADMIN":
                    response = String.format(response, user.getUserRoles().getRoleIdentifier(), "OK", getCompany(user).toJson());
                    break;
                case "ACCAD":
                    response = String.format(response, user.getUserRoles().getRoleIdentifier(), "OK", getCompany(user).toJson());
                    break;
                default:
                    response = String.format(response, "", "OK", "");
                    break;
                }
            response = String.format(response, user.getUserRoles().getRoleIdentifier(), "OK", "");
        }catch (NoContentException exception){
            return Response.status(Status.NO_CONTENT).entity(exception.getMessage()).build();
        }
        return Response.status(200).entity(response).build();
    }

    private Company getCompany(SimpleUser user) throws NoContentException{
        Company company = null;
        try {
            UserCompany userCompanyRelation = (UserCompany) user.getUserCompanies().toArray()[0];
            company = userCompanyRelation.getCompany();
        }catch (Exception exception){
            LOGGER.error(exception);
        }
        if (company == null)
            throw new NoContentException("{\"Error\": \"User not associated with any company\"}");
        return company;    }
}
