package co.refiere.resources;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang.time.DateUtils;

import co.refiere.dao.CurrencyDao;
import co.refiere.dao.OrderStatusDao;
import co.refiere.dao.PaymentDao;
import co.refiere.dao.PaymentTypeDao;
import co.refiere.dao.PlanOrderDao;
import co.refiere.models.Currency;
import co.refiere.models.OrderStatus;
import co.refiere.models.Payment;
import co.refiere.models.PaymentType;
import co.refiere.models.PlanOrder;
import co.refiere.resources.base.PaymentRequest;

@Path("v1/order")
public class PlanOrderResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/payment")
    public Response createCompany(PaymentRequest payment) {
        
        PaymentDao paymentDao = new PaymentDao();
        PaymentTypeDao paymentTypeDao = new PaymentTypeDao();
        CurrencyDao currencyDao = new CurrencyDao();
        
        PlanOrderDao planOrderDao = new PlanOrderDao();
        OrderStatusDao statusDao = new OrderStatusDao();
        
        PaymentType type = paymentTypeDao.findPaymentTypeById(payment.getPaymentTypeId());
        Currency currency = currencyDao.findCurrencyTypeById(payment.getCurrencyId());
        PlanOrder planOrder = planOrderDao.findPlanOrderById(payment.getOrderId());
        OrderStatus confirmed = statusDao.findOrderStatusById(3); //Confirmed
        if(type == null)
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(javax.ws.rs.client.Entity.json("{\"Error \": \"Payment type not found \"}")).build(); 
        if(currency == null)
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(javax.ws.rs.client.Entity.json("{\"Error \": \"Currency not found \"}")).build(); 
        if(planOrder == null)
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(javax.ws.rs.client.Entity.json("{\"Error \": \"Order not found for the given payment\"}")).build(); 
        if(confirmed == null)
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(javax.ws.rs.client.Entity.json("{\"Error \": \"Internal error processing your payment\"}")).build(); 
        
        Payment paymentObject = new Payment();
        paymentObject.setPaymentDescription(payment.getPaymentDescription());
        paymentObject.setAccountingTrackRef(payment.getAccountingTrackRef());
        paymentObject.setDiscount(payment.getDiscount());
        paymentObject.setPrice(payment.getPrice());
        paymentObject.setTotalPrice(payment.getTotalPrice());
        paymentObject.setPaymentType(type);
        paymentObject.setCurrency(currency);
        
        planOrder.setPayment(paymentObject);
        planOrder.setStartDate(new Date());
        planOrder.setEndDate(DateUtils.addMonths(new Date(), 1));
        //TODO: Get username from request
        planOrder.setApprovedBy(payment.getUserName());
        planOrder.setOrderStatus(confirmed);

        statusDao.save(confirmed);
        currencyDao.save(currency);
        paymentTypeDao.save(type);
        paymentDao.save(paymentObject);
        planOrderDao.save(planOrder);
        return Response.status(200).build();
    }
}
