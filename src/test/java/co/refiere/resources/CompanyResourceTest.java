package co.refiere.resources;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import co.refiere.resources.base.CompanyRequest;
import co.refiere.resources.base.LapseRequest;
import co.refiere.resources.base.PlanRequest;

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
	 * Test to see that the message "Got it!" is sent in the response.
	 */
	@Test
	public void testRegisterCompanyInfo() {
		CompanyRequest newCompany = new CompanyRequest();
		PlanRequest plan = new PlanRequest();
		LapseRequest dummyLapse = new LapseRequest();
		dummyLapse.setId(1);
		dummyLapse.setLapseName("TestLapse");
		plan.setName("Test Plan");
		plan.setPanelType((byte) 0);
		plan.setPersonalizedEmail("fake@email.com");
		plan.setCampaign_lapse(dummyLapse);
		plan.setTimely_report(dummyLapse);
		plan.setCampaignAmount(200);
		plan.setReferrerAmount(500);
		plan.setSalesPercentaje(9);
		newCompany.setName("Testing Company");
		newCompany.setAddress("Testing Company");
		newCompany.setEmail("Testing Company");
		newCompany.setTelephone("Testing Company");
		newCompany.setPlan(plan);

		final Response confirmationResponse = target().path("v1/company").request().post(Entity.json(newCompany));

		assertEquals(200, confirmationResponse.getStatus());
	}

	private Application ResourceConfig() {
		// TODO Auto-generated method stub
		return null;
	}
}
