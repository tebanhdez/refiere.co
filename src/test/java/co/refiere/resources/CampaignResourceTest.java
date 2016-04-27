package co.refiere.resources;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import co.refiere.resources.base.CampaignRequest;

public class CampaignResourceTest extends JerseyTest {

     @Override
     protected Application configure() {
        return new ResourceConfig(CampaignResource.class);
     }
     @Ignore
    @Test
    public void createCampaignTest(){
        CampaignRequest campaign = new CampaignRequest();
        campaign.setCampaignName("Test Campaign");
        campaign.setPrizeForRefiere("Free month");
        campaign.setPrizeForRefieree("Free first month");
        
        final Response companyResponse = target().path("v1/campaign").request().post(Entity.json(campaign));
        Assert.assertEquals(200, companyResponse.getStatus());
        Assert.assertTrue(companyResponse.hasEntity());
        String jsonResponseString = companyResponse.readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(jsonResponseString));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        Assert.assertEquals("OK", object.getString("status"));
        Assert.assertTrue((Object)object.getInt("campaignId") instanceof Integer);
    }
}
