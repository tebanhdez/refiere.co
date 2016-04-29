package co.refiere.services;

import java.util.Date;

import javax.ws.rs.core.NoContentException;
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

public class PaymentService {

    public String acceptPayment(PaymentRequest payment) throws NoContentException, NullPointerException{
        String response = "{\"paymentId\": %d}";
        
        PaymentDao paymentDao = new PaymentDao();
        PaymentTypeDao paymentTypeDao = new PaymentTypeDao();
        CurrencyDao currencyDao = new CurrencyDao();
        
        PlanOrderDao planOrderDao = new PlanOrderDao();
        OrderStatusDao statusDao = new OrderStatusDao();
        
        PaymentType type = paymentTypeDao.findPaymentTypeById(payment.getPaymentTypeId());
        Currency currency = currencyDao.findCurrencyTypeById(payment.getCurrencyId());
        PlanOrder planOrder = planOrderDao.findPlanOrderById(payment.getOrderId());
        OrderStatus confirmed = statusDao.findOrderStatusById(13); //Confirmed
        if(type == null)
            throw new NoContentException("\"Error \": \"Payment type not found \"");
        if(currency == null)
            throw new NoContentException("\"Error \": \"Currency not found \"");
        if(planOrder == null)
            throw new NoContentException("\"Error \": \"Order not found for the given payment\"");
        if(confirmed == null)
            throw new NoContentException("\"Error \": \"Internal error processing your payment\"");
        
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

        paymentDao.save(paymentObject);
        response = String.format(response, paymentObject.getId());
        planOrderDao.save(planOrder);
        return response;
    }
}
