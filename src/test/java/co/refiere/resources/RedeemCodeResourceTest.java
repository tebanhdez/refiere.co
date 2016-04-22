package co.refiere.resources;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import co.refiere.dao.CampaingDao;
import co.refiere.dao.CompanyDatabaseDao;
import co.refiere.models.Campaign;
import co.refiere.models.CompanyDatabase;
import co.refiere.resources.base.NewUserRequest;

public class RedeemCodeResourceTest extends JerseyTest {
     
	private int databaseId;
	
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
 		 CampaingDao campaingDao = new CampaingDao();
 		 campaingDao.save(campaign);
	 }
	
	 
	  @Test
	  public void testGetIt() {
	    NewUserRequest newUserRequest = new NewUserRequest();
	    newUserRequest.setCampaignId(databaseId); 
	    newUserRequest.setReferalPersonId(2);
	    newUserRequest.setIdentificationCardNumber("1274563495");
	    newUserRequest.setName("User Test Name");
	    newUserRequest.setLastName("Last-name");
	    newUserRequest.setEmail("email@test.com");
	    newUserRequest.setPhoneNumber("89898989");
		Response response = target().path("v1/redeemCode/registerUser").request().post(Entity.json(newUserRequest));
	    assertEquals(200, response.getStatus());
	  }
}
