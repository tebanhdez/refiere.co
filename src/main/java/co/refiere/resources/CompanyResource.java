package co.refiere.resources;

import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.HibernateIterator;

import co.refiere.entity.RefiereCompany;
import co.refiere.entity.RefierePlan;
import co.refiere.resources.base.CompanyRequest;
import co.refiere.resources.base.PlanRequest;
import co.refiere.resources.util.HibernateUtil;
import co.refiere.services.mailer.RefiereServiceFactory;

@Path("v1/company")
public class CompanyResource {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getIt() {
      return "Hello world!";
  }
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createCompany(CompanyRequest company) {
    
    Session session = HibernateUtil.getOpenSession();
    session.beginTransaction();
    
    try {
      String companyName = company.getName();
      String companyAddress = company.getAddress();
      String companyEmail = company.getEmail();
      String companyTelephone = company.getTelephone();
      PlanRequest companyPlan = company.getPlan();
      
      session.save(company);
      session.getTransaction().commit();
      HibernateUtil.closeSession(session);
      
  } catch (Exception e) {
    e.printStackTrace();
  }
    return Response.status(200).build();
  }
  
}
