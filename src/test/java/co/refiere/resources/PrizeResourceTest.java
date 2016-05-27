package co.refiere.resources;

import java.util.List;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import co.refiere.resources.base.PrizeObjectData;
import co.refiere.resources.util.PrizeResource;

public class PrizeResourceTest extends JerseyTest{

    @Override
    protected Application configure() {
        return new ResourceConfig(PrizeResource.class);
    }
    
    @Test
    public void testGetAllPrizes() {
        final Response response = target().path("v1/prize/all").request().get();
        Assert.assertEquals(200, response.getStatus());
        List<PrizeObjectData> prizes = response.readEntity(new GenericType<List<PrizeObjectData>>(){});
        Assert.assertTrue("Prizes not found", prizes.size() > 0);
    }
}
