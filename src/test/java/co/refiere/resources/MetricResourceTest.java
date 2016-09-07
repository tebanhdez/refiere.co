package co.refiere.resources;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

public class MetricResourceTest extends JerseyTest {
    
    @Override
    protected Application configure() {
       return new ResourceConfig(MetricsResource.class);
    }

    @Test
    public void getCompanyClientList(){
        final Response companyResponse = target().path("v1/metrics/company/3/redeemCodeReport").request().get();
        Assert.assertEquals(200, companyResponse.getStatus());
    }
}
