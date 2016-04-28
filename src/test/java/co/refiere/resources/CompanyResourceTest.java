package co.refiere.resources;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.UUID;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import co.refiere.resources.base.CompanyRequest;
import co.refiere.resources.base.LapseRequest;
import co.refiere.resources.base.PlanRequest;
import co.refiere.resources.base.UserRequest;

public class CompanyResourceTest extends JerseyTest {

    @Override

    protected Application configure() {
        return new ResourceConfig(CompanyResource.class);      
    }
    //    protected Application configure() {
    //        return new ResourceConfig(MailerResource.class);
    //    }
    //
    /**
     * 
     */
    @Test
    public void testRegisterCompanyInfo() {
        //User details
        UserRequest companyUser = new UserRequest();
        companyUser.setLogin(UUID.randomUUID().toString().substring(0, 18));
        companyUser.setPassword("password");
        
        //Company details
        CompanyRequest newCompany = new CompanyRequest();
        newCompany.setName("CompanyResourceTest::testRegisterCompanyInfo");
        newCompany.setAddress("RegisterCompanyInfo Company");
        newCompany.setEmail("jehehe1@gmail.com");
        newCompany.setTelephone("+506 0000 0000");
        
        //Plan details
        PlanRequest plan = new PlanRequest();
        plan.setId(10); // 10 - Basic Plan
        plan.setPersonalizedEmail("company@custom.email");
        //Setting up properties
        newCompany.setUser(companyUser);
        newCompany.setPlan(plan);

        final Response confirmationResponse = target().path("v1/company/register").request().post(Entity.json(newCompany));

        assertEquals(200, confirmationResponse.getStatus());
    }

    private Application ResourceConfig() {
        // TODO Auto-generated method stub
        return null;
    }
}
