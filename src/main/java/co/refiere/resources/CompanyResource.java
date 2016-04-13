package co.refiere.resources;

import javax.ws.rs.Consumes;
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

@Path("v1/company")
public class CompanyResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_PLAIN)
  public Response createCompany(CompanyRequest company) {
    
    Session session = HibernateUtil.getOpenSession();
    session.beginTransaction();

    RefiereCompany companyModel = new RefiereCompany(company.getName(), "Balmer", "Microsoft", "LA", "microsoft@outlook.com", "555555555", "Basic");
    PlanRequest plan = company.getPlan();
    RefierePlan planModel = new RefierePlan(plan.getName(), plan.getSalesPercentaje(), plan.getCampaignAmount(), plan.getTimely_report(), plan.getReferrerAmount(), plan.getPersonalizedEmail(), plan.getPanelType(), plan.getTimely_report());
    companyModel.setPlan(plan);
    
    session.save(companyModel);
    session.getTransaction().commit();
    HibernateUtil.closeSession(session);
    return Response.status(200).build();
  }
}
