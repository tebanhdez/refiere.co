  package co.refiere.resources;

  import static org.junit.Assert.assertEquals;

  import java.io.File;
  import java.io.IOException;

  import javax.ws.rs.client.Client;
  import javax.ws.rs.client.ClientBuilder;
  import javax.ws.rs.client.Entity;
  import javax.ws.rs.client.WebTarget;
  import javax.ws.rs.core.Application;
  import javax.ws.rs.core.Response;

  import org.glassfish.jersey.media.multipart.FormDataMultiPart;
  import org.glassfish.jersey.media.multipart.MultiPartFeature;
  import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
  import org.glassfish.jersey.server.ResourceConfig;
  import org.glassfish.jersey.test.JerseyTest;
  import org.junit.Ignore;
  import org.junit.Test;
  public class DataImportTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(DatabaseImporterResource.class).register(MultiPartFeature.class);
    }

    
    @Test
    public void excelImportTest() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("test.xlsx").getFile());
        Response response = doPostWithFiles(file);

        assertEquals(200, response.getStatus());
    }

    
    @Test
    public void csvImportTest() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("test.csv").getFile());
        Response response = doPostWithFiles(file);

        assertEquals(200, response.getStatus());
    }

    private Response doPostWithFiles(File file) {
        Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
        FileDataBodyPart filePart = new FileDataBodyPart("file", file);
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
        FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.field("foo", "bar").bodyPart(filePart);

        WebTarget target = client.target("http://localhost:8080/rest/database/2/import");
        Response response = target.request().post(Entity.entity(multipart, multipart.getMediaType()));
        return response;
    }

  }
