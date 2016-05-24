package co.refiere.resources;

import java.util.List;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import co.refiere.resources.base.PlanObjectData;
import co.refiere.resources.util.PlanResource;

public class PlanResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
      return new ResourceConfig(PlanResource.class);
    }

    @Test
    public void testGetAllPlans() {
        final Response response = target().path("v1/plan/all").request().get();
        Assert.assertEquals(200, response.getStatus());
        List<PlanObjectData> plans = response.readEntity(new GenericType<List<PlanObjectData>>(){});
        Assert.assertTrue("Plans not found", plans.size() > 0);
    }
}
