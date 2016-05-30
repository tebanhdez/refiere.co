package co.refiere.resources;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import co.refiere.services.DataImporterService;

@Path("/database")
public class DatabaseImporterResource {

 

  @POST
  @Path("/import")
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public Response insertDataInDB2(@FormDataParam("file") InputStream uploadedInputStream,
      @FormDataParam("file") FormDataContentDisposition fileDetail) {
    DataImporterService dataImporterService = new DataImporterService();
    dataImporterService.importDatabase(uploadedInputStream, fileDetail);
    return Response.ok().build(); 
    }

  

  
}