package co.refiere.resources;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
import co.refiere.services.PaymentService;

@Path("v1/order")
public class PlanOrderResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/payment")
    public Response createCompany(PaymentRequest payment) {
        String response = "{\"status\": \"%s\", %s}";
        PaymentService paymentService = new PaymentService();
        try {
            response = String.format(response, "OK", "\"result\":" + paymentService.acceptPayment(payment));
        } catch (NoContentException e) {
            response = String.format(response, "FAIL",e.getMessage());
        } catch (NullPointerException exception) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(200).entity(response).build();
    }
}
