package co.refiere.resources.util;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NoContentException;
import javax.ws.rs.core.Response;

import co.refiere.resources.base.DatabaseObjectData;
import co.refiere.services.DatabaseService;

@Path("v1/database")
public class DatabaseResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllDatabase() {
        String response = "{\"status\": \"%s\", %s}";
        try {
            DatabaseService databaseService = new DatabaseService();
            List<co.refiere.resources.base.DatabaseObjectData> databases = databaseService.getAllDatabases();
            GenericEntity<List<DatabaseObjectData>> list = new GenericEntity<List<DatabaseObjectData>>(databases) {};
            return Response.ok(list).build();
        } catch (NoContentException e) {
            response = String.format(response, "FAIL", e.getMessage());
        } catch (NullPointerException exception) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(200).entity(response).build();
    }
}
