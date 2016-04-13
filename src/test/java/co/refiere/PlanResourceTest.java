package co.refiere;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import co.refiere.resources.CompanyResource;

public class PlanResourceTest extends JerseyTest {
  @Override
  protected Application configure() {
    return new ResourceConfig(CompanyResource.class);
  }
  

}