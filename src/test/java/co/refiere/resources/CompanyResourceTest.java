package co.refiere.resources;

import java.util.UUID;

import javax.json.JsonObject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;

import co.refiere.models.DefaultPlan;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import co.refiere.dao.RefiereCompanyDao;
import co.refiere.dao.RefiereUserDao;
import co.refiere.models.Company;
import co.refiere.models.SimpleUser;
import co.refiere.resources.base.CompanyRequest;
import co.refiere.resources.base.PlanRequest;
import co.refiere.resources.base.UserRequest;
import co.refiere.resources.util.JsonUtils;

public class CompanyResourceTest extends JerseyTest {

    CompanyRequest newCompany;
    private int companyId;
    private int userId;

    @Override
    protected Application configure() {
        return new ResourceConfig(CompanyResource.class);      
    }
    
    
    @Before
    public void setTestData(){
    	//User details
        UserRequest companyUser = new UserRequest();
        companyUser.setLogin(UUID.randomUUID().toString().substring(0, 18));
        companyUser.setPassword("password");
        
        //Company details
        newCompany = new CompanyRequest();
        newCompany.setName("CompanyResourceTest::testRegisterCompanyInfo");
        newCompany.setAddress("RegisterCompanyInfo Company");
        newCompany.setEmail("jehehe1@gmail.com");
        newCompany.setTelephone("+506 0000 0000");
        
        //Plan details
        PlanRequest plan = new PlanRequest();
        plan.setId(DefaultPlan.BASIC.getPlanId());
        plan.setPersonalizedEmail("company@custom.email");
        //Setting up properties
        newCompany.setUser(companyUser);
        newCompany.setPlan(plan);
    }
    /**
     * 
     */
    @Test
    public void testRegisterCompanyInfo() {

        final String confirmationResponse = target().path("v1/company/register").request()
                .post(Entity.json(newCompany), String.class);
        JsonObject jsonResponse = JsonUtils.parseStringToJson(confirmationResponse);
        JsonObject resultObject = jsonResponse.getJsonObject("result");
        userId = resultObject.getInt("userId");
        companyId = resultObject.getInt("companyId");
    }

    private Application ResourceConfig() {
        // TODO Auto-generated method stub
        return null;
    }

    @After
    public void deleteTestData(){
        RefiereUserDao userDao = new RefiereUserDao();
        SimpleUser user = userDao.findUserById(userId);

        RefiereCompanyDao companyDao = new RefiereCompanyDao();
        Company companyToDelete = companyDao.findCompanyById(companyId);

        companyDao.deleteCompany(companyToDelete);
        userDao.deleteUser(user);
    }
}
