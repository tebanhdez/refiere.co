package co.refiere.resources;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.json.JsonObject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.NoContentException;

import co.refiere.dao.PlanOrderDao;
import co.refiere.models.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import co.refiere.dao.PaymentDao;
import co.refiere.dao.RefiereCompanyDao;
import co.refiere.dao.RefiereUserDao;
import co.refiere.resources.base.CompanyRequest;
import co.refiere.resources.base.PaymentRequest;
import co.refiere.resources.base.PlanRequest;
import co.refiere.resources.base.UserRequest;
import co.refiere.resources.util.JsonUtils;
import co.refiere.services.CompanyService;

public class PlanOrderResourceTest extends JerseyTest {


    CompanyRequest newCompany;
    private int companyId;
    private int orderId;
    private int userId;
    private int paymentId;

    @Override
    protected Application configure() {
        return new ResourceConfig(PlanOrderResource.class);
    }
    
    @Before
    public void setTestCompany() throws NoContentException{
      
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
        plan.setId(DefaultPlan.BASIC.getPlanId());
        plan.setPersonalizedEmail("company@custom.email");
        //Setting up properties
        newCompany.setUser(companyUser);
        newCompany.setPlan(plan);

        CompanyService companyService = new CompanyService();
        String response = companyService.createCompanyAccount(newCompany);
        JsonObject jsonResponse = JsonUtils.parseStringToJson(response);
        Assert.assertTrue("Key not found", jsonResponse.containsKey("userId"));
        Assert.assertTrue("Key not found", jsonResponse.containsKey("companyId"));
        userId = jsonResponse.getInt("userId");
        companyId = jsonResponse.getInt("companyId");
        PlanOrderDao orderDao = new PlanOrderDao();
        List <PlanOrder> planOrders = orderDao.findPlanOrdersByCompanyId(companyId);
        Assert.assertTrue("No plan order found", planOrders != null && planOrders.size() > 0);
        orderId = planOrders.get(0).getId();
    }

    @Test
    public void testAcceptPlanOrderPayment(){
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setOrderId(orderId);
        paymentRequest.setCurrencyId(DefaultCurrency.CRC.getCurrencyId());
        paymentRequest.setDiscount(BigDecimal.valueOf(0.10));
        paymentRequest.setPrice(BigDecimal.valueOf(99.99));
        paymentRequest.setPaymentDescription("Payment of Testing Company");
        paymentRequest.setPaymentTypeId(DefaultPayment.BANK_DEPOSIT.getId());
        paymentRequest.setTotalPrice(BigDecimal.valueOf(98.99));
        paymentRequest.setAccountingTrackRef("BANCO NACIONAL: 0832028384");
        paymentRequest.setUserName("ehernandez");
        final String paymentResponse = target().path("v1/order/payment").request()
                .post(Entity.json(paymentRequest), String.class);
        JsonObject jsonResponse = JsonUtils.parseStringToJson(paymentResponse);
        JsonObject resultObject = jsonResponse.getJsonObject("result");
        paymentId = resultObject.getInt("paymentId");
        Assert.assertNotNull("No payment Id found", paymentId);
    }

    @After
    public void deleteTestData(){
        Assert.assertNotNull("No userId found", userId);
        Assert.assertNotNull("No companyId found", companyId);
        RefiereUserDao userDao = new RefiereUserDao();
        SimpleUser user = userDao.findUserById(userId);
        PaymentDao paymentDao = new PaymentDao();
        
        RefiereCompanyDao companyDao = new RefiereCompanyDao();
        Company companyToDelete = companyDao.findCompanyById(companyId);

        companyDao.deleteCompany(companyToDelete);
        userDao.deleteUser(user);
        Payment payment = paymentDao.findPaymentById(paymentId);
        paymentDao.deletePayment(payment);
    }
}
