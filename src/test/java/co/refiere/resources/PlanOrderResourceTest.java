package co.refiere.resources;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.UUID;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import co.refiere.resources.base.CompanyRequest;
import co.refiere.resources.base.PaymentRequest;
import co.refiere.resources.base.PlanRequest;
import co.refiere.resources.base.UserRequest;
import co.refiere.services.CompanyService;

public class PlanOrderResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(PlanOrderResource.class);
    }
    
    @Before
    public void setTestCompany(){
      
        //User details
        UserRequest companyUser = new UserRequest();
        companyUser.setLogin(UUID.randomUUID().toString().substring(0, 18));
        companyUser.setPassword("password");
        
        //Company details
        CompanyRequest newCompany = new CompanyRequest();
        newCompany.setName("Testing Company");
        newCompany.setAddress("Testing Company");
        newCompany.setEmail("jehehe1@gmail.com");
        newCompany.setTelephone("+506 0000 0000");
        
        //Plan details
        PlanRequest plan = new PlanRequest();
        plan.setId(10); // 10 - Basic Plan
        plan.setPersonalizedEmail("company@custom.email");
        //Setting up properties
        newCompany.setUser(companyUser);
        newCompany.setPlan(plan);

        CompanyService companyService = new CompanyService();
        companyService.createCompanyAccount(newCompany);
    }
    
    @Test
    public void testAcceptPlanOrderPayment(){
        PaymentRequest paymentRequest = new PaymentRequest();
        //paymentRequest.setId(orderId);
        paymentRequest.setOrderId(1);
        paymentRequest.setCurrencyId(10); // 0 - Colones
        paymentRequest.setDiscount(BigDecimal.valueOf(0.10));
        paymentRequest.setPrice(BigDecimal.valueOf(99.99));
        paymentRequest.setPaymentDescription("Payment of Testing Company");
        paymentRequest.setPaymentTypeId(10); //Bank deposit
        paymentRequest.setTotalPrice(BigDecimal.valueOf(98.99));
        paymentRequest.setAccountingTrackRef("BANCO NACIONAL: 0832028384");
        paymentRequest.setUserName("ehernandez");
        final Response paymentResponse = target().path("v1/order/payment").request().header("Authorization", "Basic cm9vdDpAZG0xbjE1dHJhQHQwcg==").post(Entity.json(paymentRequest));
        Assert.assertTrue(paymentResponse.getStatus() == 200);
    }
}
