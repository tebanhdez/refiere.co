package co.refiere.resources;

import java.io.StringReader;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import co.refiere.dao.CampaignDao;
import co.refiere.dao.CompanyDatabaseDao;
import co.refiere.dao.PersonDao;
import co.refiere.dao.RefiereCompanyDao;
import co.refiere.models.Campaign;
import co.refiere.models.Company;
import co.refiere.models.CompanyDatabase;
import co.refiere.models.Person;
import co.refiere.resources.base.CampaignRequest;

public class CampaignResourceTest extends JerseyTest {

    RefiereCompanyDao companyDao = new RefiereCompanyDao();
    CompanyDatabaseDao companyDBDao = new CompanyDatabaseDao();
    CampaignDao campaignDao = new CampaignDao();
    PersonDao personDao = new PersonDao();
    
    Company testCompany;
    int testCompanyDataBaseId;
    Campaign testCampaign;
    
    @Override
    protected Application configure() {
       return new ResourceConfig(CampaignResource.class);
    }
    
    @Before
    public void settingTestData(){
        
        List<Company> testCompanies = companyDao.findAll();
        Assert.assertTrue("No Companies found!", testCompanies.size() > 0);
        testCompany = testCompanies.get(0);
        
        CompanyDatabase dataBase = new CompanyDatabase();
        dataBase.setName("CampaignResourceTest::settingTestData");
        companyDBDao.save(dataBase);
        testCompanyDataBaseId = dataBase.getId();
        
        for(int personIndex = 0; personIndex < 10; personIndex++){
            Person personTest = new Person();
            personTest.setIdentificationCardNumber("998899889988");
            personTest.setName("CampaignResourceTest::settingTestData::Name");
            personTest.setLastName("CampaignResourceTest::settingTestData::LastName");
            personTest.setPhoneNumber("+000999999999");
            personTest.setEmail("jehehe1@gmail.com");
            personTest.setCompanyDatabase(dataBase);
            personDao.save(personTest);
        }
    }
    
    @Test
    public void createCampaignTest(){
        CampaignRequest campaign = new CampaignRequest();
        campaign.setCampaignName("CampaignResourceTest::createCampaignTest");
        campaign.setPrizeForRefiere("Free month");
        campaign.setPrizeForRefieree("Free first month");
        campaign.setCompanyId(testCompany.getId());
        campaign.setCompanyDataBase(testCompanyDataBaseId);
        campaign.setPrizeForRefiereeId(12); // Bonus
        campaign.setPrizeForRefiereId(11);  // Discount
        final Response companyResponse = target().path("v1/campaign").request().post(Entity.json(campaign));
        Assert.assertEquals(200, companyResponse.getStatus());
        Assert.assertTrue(companyResponse.hasEntity());
        String jsonResponseString = companyResponse.readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(jsonResponseString));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        Assert.assertEquals("OK", object.getString("status"));
        Assert.assertTrue((Object)object.getInt("campaignId") instanceof Integer);
        testCampaign = campaignDao.getCampaigById(object.getInt("campaignId"));
    }
    
    @After
    public void deleteTestData(){
        campaignDao.deleteCampaign(testCampaign);
        CompanyDatabase testDB = companyDBDao.findDatabaseById(testCompanyDataBaseId); 
        companyDBDao.deleteCompanyDatabase(testDB);
        companyDao.deleteCompany(testCompany);
    }
}
