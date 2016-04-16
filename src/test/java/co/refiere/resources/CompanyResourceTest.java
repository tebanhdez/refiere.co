package co.refiere.resources;

import static org.junit.Assert.assertEquals;

import java.util.Date;

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
		companyUser.setLogin("login");
		companyUser.setPassword("password");
		
		//Company details
		CompanyRequest newCompany = new CompanyRequest();
		newCompany.setName("Testing Company");
		newCompany.setAddress("Testing Company");
		newCompany.setEmail("jehehe1@gmail.com");
		newCompany.setTelephone("+506 0000 0000");
		
		//LapseDetails
		LapseRequest dummyLapse = new LapseRequest();
		dummyLapse.setId(1);
		dummyLapse.setDays(30);
		dummyLapse.setLapseName("TestLapse");
		
		//Plan details
		PlanRequest plan = new PlanRequest();
		plan.setName("Test Plan");
		plan.setSalesPercentaje((float) 9.5);
		plan.setCampaignAmount(200);
		
		plan.setReferrerAmount(500);
		plan.setPersonalizedEmail("fake@email.com");
		plan.setPanelType((byte) 0);
		
		plan.setCampaign_lapse(dummyLapse);
		plan.setTimely_report(dummyLapse);
		
		plan.setStartDate(new Date());
		plan.setEndDate(new Date());
		

		
		//Setting up properties
		newCompany.setUser(companyUser);
		newCompany.setPlan(plan);

		final Response confirmationResponse = target().path("v1/company").request().post(Entity.json(newCompany));

		assertEquals(200, confirmationResponse.getStatus());
	}

	private Application ResourceConfig() {
		// TODO Auto-generated method stub
		return null;
	}
}
