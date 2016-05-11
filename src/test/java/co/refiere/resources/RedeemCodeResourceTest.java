package co.refiere.resources;

import static org.junit.Assert.assertEquals;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import co.refiere.dao.PersonDao;
import co.refiere.dao.ReferencesCodesDao;
import co.refiere.models.Person;
import co.refiere.models.ReferencesCodes;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import co.refiere.dao.CampaignDao;
import co.refiere.dao.CompanyDatabaseDao;
import co.refiere.models.Campaign;
import co.refiere.models.CompanyDatabase;
import co.refiere.resources.base.NewUserRequest;

import java.io.StringReader;

public class RedeemCodeResourceTest extends JerseyTest {
     
    private int databaseId;
    Person referalPerson = new Person();
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

         Campaign campaign = new Campaign();
         campaign.setName("test campaig name");
 		 campaign.setCompanyDatabase(companyData);
 		 CampaignDao campaignDao = new CampaignDao();
 		 campaignDao.save(campaign);

         referalPerson.setIdentificationCardNumber("99999");
         referalPerson.setName("Bat");
         referalPerson.setLastName("bat");
         referalPerson.setPhoneNumber("8977");
         referalPerson.setEmail("fd@gmail.com");
         PersonDao personDao = new PersonDao();
         personDao.save(referalPerson);
     }
     
      @Test
      public void createNewUserTest() {
        NewUserRequest newUserRequest = new NewUserRequest();
        newUserRequest.setCampaignId(databaseId); 
        newUserRequest.setReferalPersonId(referalPerson.getId());
        newUserRequest.setIdentificationCardNumber("304620827");
        newUserRequest.setName("Pablo");
        newUserRequest.setLastName("Fernandez");
        newUserRequest.setEmail("jpblo.3105@gmail.com");
        newUserRequest.setPhoneNumber("88888888");
        Response response = target().path("v1/redeemCode/registerUser").request().post(Entity.json(newUserRequest));
        assertEquals(200, response.getStatus());
          Assert.assertTrue(response.hasEntity());
          String jsonResponseString = response.readEntity(String.class);
          JsonReader jsonReader = Json.createReader(new StringReader(jsonResponseString));
          JsonObject object = jsonReader.readObject();
          jsonReader.close();
          Assert.assertEquals("OK", object.getString("status"));
          Assert.assertTrue((Object)object.getInt("personId") instanceof Integer);

      }

}
