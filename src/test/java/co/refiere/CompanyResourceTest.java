package co.refiere;

import static org.junit.Assert.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import co.refiere.resources.CompanyResource;

public class CompanyResourceTest extends JerseyTest {
  
  @Override
  protected Application configure() {
    return new ResourceConfig(CompanyResource.class);
  }
  
  @Test
  public void testPostCompany() {
    final Response responseMsg = target().path("v1/company").request().post(Entity.json("test"));
    
    assertEquals(200, responseMsg.getStatus());
  }

}
