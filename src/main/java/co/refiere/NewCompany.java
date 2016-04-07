package co.refiere;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("v1/user/create")
public class NewCompany {
  
  @POST
  public Response postMsg(@PathParam("param") String msg) {
    String output = "POST:Jersey say : " + msg;
    return Response.status(200).entity(output).build();
  }
}