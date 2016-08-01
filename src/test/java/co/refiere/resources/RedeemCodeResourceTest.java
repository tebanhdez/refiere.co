package co.refiere.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import co.refiere.dao.PersonDao;
import co.refiere.dao.ReferencesCodesDao;
import co.refiere.models.*;
import org.apache.commons.lang.StringUtils;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.*;

import co.refiere.dao.CampaignDao;
import co.refiere.dao.CompanyDatabaseDao;
import co.refiere.resources.base.NewUserRequest;


public class RedeemCodeResourceTest extends JerseyTest {

    private int databaseId;
    Campaign testCampaign;
    Person referalPerson = new Person();
    CompanyDatabase companyDB = new CompanyDatabase();

    PersonDao personDao = new PersonDao();
    CampaignDao campaignDao = new CampaignDao();
    CompanyDatabaseDao companyDBDao = new CompanyDatabaseDao();
    ReferencesCodesDao referencesCodesDao = new ReferencesCodesDao();
    private final String REFERAL_CODE = "TEST1";

    @Override
    protected Application configure() {
        return new ResourceConfig(RedeemCodeResource.class);
    }

    @Before
    public void createTestData(){

        //Setting the test company
        companyDB.setName("testCompany");
        companyDBDao.save(companyDB);
        databaseId = companyDB.getId();
        Assert.assertNotNull(databaseId);

        //Setting the test campaign
        testCampaign = new Campaign();
        testCampaign.setName("test campaign name");
        testCampaign.setCompanyDatabase(companyDB);
        campaignDao.save(testCampaign);

        //Setting referal person
        referalPerson.setIdentificationCardNumber("00000");
        referalPerson.setName("ReferalName");
        referalPerson.setLastName("ReferalLastName");
        referalPerson.setPhoneNumber("0000000");
        referalPerson.setEmail("ehernandez@pernix-solutions.com");
        referalPerson.setCompanyDatabase(companyDB);
        personDao.save(referalPerson);

        //Creating referal code
        ReferencesCodes referalCode = new ReferencesCodes(new ReferencesCodesId(testCampaign.getId(), referalPerson.getId()), null, REFERAL_CODE);
        referencesCodesDao.save(referalCode);
    }

    @Test
    public void referalCodeCreated(){
        List<ReferencesCodes> referalCode = referencesCodesDao.findReferalCode(REFERAL_CODE);
        Assert.assertNotNull("No referal code found.",referalCode);
        Assert.assertTrue("Invalid referal code.", referalCode.size() == 1 && referalCode.get(0).getCode().equals(REFERAL_CODE));

        //Calling second test
        redeemOneCodeTest();

        //Calling third test
        redeemSecondPersonCode();

        //Calling fourth test
        //Expected exception since it just should allow 2 referals
        redeemThirdPersonCode();
    }

    public void sendRedeemCodeRequest(String statusExpected) {
        NewUserRequest newUserRequest = new NewUserRequest();
        newUserRequest.setReferalCode(REFERAL_CODE);
        newUserRequest.setIdentificationCardNumber("1111111");
        newUserRequest.setName("newUserName");
        newUserRequest.setLastName("newUserLastName");
        newUserRequest.setEmail("ehernandez@pernix.cr");
        newUserRequest.setPhoneNumber("00000000");
        Response response = target().path("v1/redeemCode/registerUser").request().post(Entity.json(newUserRequest));
        assertEquals(200, response.getStatus());
        Assert.assertTrue(response.hasEntity());
        String jsonResponseString = response.readEntity(String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(jsonResponseString));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        Assert.assertEquals(statusExpected, object.getString("status"));
    }

    public void redeemOneCodeTest(){
        sendRedeemCodeRequest("OK");
        List<ReferencesCodes> referencesCodes = referencesCodesDao.findCampaignReferalCodesByPerson(testCampaign.getId(), referalPerson.getId());
        assertTrue("Code not redeemed.", (referencesCodes != null && referencesCodes.size() == 1) );
        ReferencesCodes code = referencesCodes.get(0);
        assertTrue("Reference invalid:Campaing do not match", code.getId().getCampaignId() == testCampaign.getId());
        Person referee = personDao.findPersonsById(code.getId().getPersonId());
        assertTrue("Reference invalid:Person do not match", referee.getEmail().equals("ehernandez@pernix.cr") &&
                                                            referee.getIdentificationCardNumber().equals("1111111")&&
                                                            referee.getName().equals("newUserName") &&
                                                            referee.getLastName().equals("newUserLastName") &&
                                                            referee.getPhoneNumber().equals("00000000"));
        assertTrue("Reference invalid:Incorrect referal", code.getPerson().getId() == referalPerson.getId());
    }

    public void redeemSecondPersonCode(){
        sendRedeemCodeRequest("OK");
        List<ReferencesCodes> referencesCodes = referencesCodesDao.findCampaignReferalCodesByPerson(testCampaign.getId(), referalPerson.getId());
        assertTrue("Code not redeemed.", (referencesCodes != null && referencesCodes.size() == 2) );
    }

    public void redeemThirdPersonCode(){
        sendRedeemCodeRequest("FAIL");
    }
    @After
    public void deleteTestData(){
        List<ReferencesCodes> testCodes = referencesCodesDao.findAll();
        for(ReferencesCodes code : testCodes){
            Person toDelete = personDao.findPersonsById(code.getId().getPersonId());
            personDao.deletePerson(toDelete);
            referencesCodesDao.deleteReferencesCodes(code);
        }
        campaignDao.deleteCampaign(testCampaign);
        companyDBDao.deleteCompanyDatabase(companyDB);
    }
}