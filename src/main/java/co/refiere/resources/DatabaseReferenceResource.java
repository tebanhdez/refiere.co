package co.refiere.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.refiere.resources.base.DatabaseRefRequest;
import co.refiere.services.DatabaseService;



@Path("v1/databaseRef")
public class DatabaseReferenceResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDatabaseRef(DatabaseRefRequest databaseRefe) {
        DatabaseService databaseService = new DatabaseService();
        return databaseService.createDatabaseRef(databaseRefe);
    }
}
