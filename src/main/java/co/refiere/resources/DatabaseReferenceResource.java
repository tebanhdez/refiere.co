package co.refiere.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NoContentException;
import javax.ws.rs.core.Response;

import co.refiere.resources.base.DatabaseRefRequest;
import co.refiere.services.DatabaseService;

@Path("v1/databaseRef")
public class DatabaseReferenceResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDatabaseRef(DatabaseRefRequest databaseRefe) {
        String response = "{\"databaseReference\": \"%s\", %s}";
        DatabaseService databaseService = new DatabaseService();
        try {
            response = String.format(response, "OK", "\"result\":" + databaseService.createDatabaseRef(databaseRefe));
        } catch (NoContentException e) {
            response = String.format(response, "FAIL",e.getMessage());
        } catch (NullPointerException exception) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(200).entity(response).build();
    }
}
