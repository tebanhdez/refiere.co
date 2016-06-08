package co.refiere.resources;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import co.refiere.dao.PersonDao;
import co.refiere.dao.ReferencesCodesDao;
import co.refiere.models.Person;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import co.refiere.dao.CampaignDao;
import co.refiere.dao.CompanyDatabaseDao;
import co.refiere.models.Campaign;
import co.refiere.models.CompanyDatabase;
import co.refiere.resources.base.NewUserRequest;


public class RedeemCodeResourceTest extends JerseyTest {

    private int databaseId;
    Campaign testCampaign;
    Person referalPerson = new Person();
    PersonDao personDao = new PersonDao();
    CampaignDao campaignDao = new CampaignDao();
    ReferencesCodesDao referencesCodesDao = new ReferencesCodesDao();

    @Override
    protected Application configure() {
        return new ResourceConfig(RedeemCodeResource.class);
    }

    @Before
    public void createTestData(){
        CompanyDatabase companyData = new CompanyDatabase();
        companyData.setName("testCompany");
        CompanyDatabaseDao companyDataBase = new CompanyDatabaseDao();
        companyDataBase.save(companyData);
        databaseId = companyData.getId();
        Assert.assertNotNull(databaseId);

        testCampaign = new Campaign();
        testCampaign.setName("test campaign name");
        testCampaign.setCompanyDatabase(companyData);
        campaignDao.save(testCampaign);

        referalPerson.setIdentificationCardNumber("00000");
        referalPerson.setName("ReferalName");
        referalPerson.setLastName("ReferalLastName");
        referalPerson.setPhoneNumber("0000000");
        referalPerson.setEmail("referalEmail@test.com");
        personDao.save(referalPerson);
    }

    @Test
    public void createNewUserTest() {
        NewUserRequest newUserRequest = new NewUserRequest();
        newUserRequest.setCampaignId(testCampaign.getId()); 
        newUserRequest.setReferalPersonId(referalPerson.getId());
        newUserRequest.setIdentificationCardNumber("1111111");
        newUserRequest.setName("newUserName");
        newUserRequest.setLastName("newUserLastName");
        newUserRequest.setEmail("newUserEmail@test.com");
        newUserRequest.setPhoneNumber("00000000");
        Response response = target().path("v1/redeemCode/registerUser").request().post(Entity.json(newUserRequest));
        assertEquals(200, response.getStatus());
        Assert.assertTrue(response.hasEntity());
        String jsonResponseString = response.readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(jsonResponseString));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        Assert.assertEquals("OK", object.getString("status"));
    }
}