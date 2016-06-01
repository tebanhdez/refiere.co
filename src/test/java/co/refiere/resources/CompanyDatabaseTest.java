package co.refiere.resources;

import java.util.List;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import co.refiere.resources.base.DatabaseObjectData;
import co.refiere.resources.util.DatabaseResource;

public class CompanyDatabaseTest extends JerseyTest{
    
    @Override
    protected Application configure() {
        return new ResourceConfig(DatabaseResource.class);
    }

    @Test
    public void testGetAllDatabases(){
        final Response response = target().path("v1/database/all").request().get();
        Assert.assertEquals(200, response.getStatus());
        List<DatabaseObjectData> databases = response.readEntity(new GenericType<List<DatabaseObjectData>>(){});
        Assert.assertTrue("CompanyDatabase not found", databases.size() > 0);
    }
}
