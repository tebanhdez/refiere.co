package co.refiere.resources;

import java.math.BigDecimal;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.refiere.dao.RefiereCompanyDao;
import co.refiere.dao.RefiereLapseDao;
import co.refiere.dao.RefierePlanDao;
import co.refiere.dao.RefiereUserCompanyRelationDao;
import co.refiere.dao.RefiereUserDao;
import co.refiere.models.Company;
import co.refiere.models.Lapse;
import co.refiere.models.Plan;
import co.refiere.models.SimpleUser;
import co.refiere.models.UserCompany;
import co.refiere.resources.base.CompanyRequest;
import co.refiere.resources.base.PlanRequest;

@Path("v1/company")
public class CompanyResource {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response createCompany(CompanyRequest company) {
        try {
            //Creating user
            RefiereUserDao userDao = new RefiereUserDao();
            SimpleUser user = new SimpleUser();
            user.setLogin(company.getUser().getLogin());
            user.setPassword(company.getUser().getPassword());
            userDao.save(user);

            //Creating company
            RefiereCompanyDao companyDao = new RefiereCompanyDao();
            Company newCompany = new Company();
            newCompany.setName(company.getName());
            newCompany.setAddress(company.getAddress());
            newCompany.setEmail(company.getEmail());
            newCompany.setPhone(company.getTelephone());
            companyDao.save(newCompany);

            //Linking main user to company
            RefiereUserCompanyRelationDao relationDao = new RefiereUserCompanyRelationDao();
            UserCompany relation = new UserCompany();
            relation.setCompany(newCompany);
            relation.setSimpleUser(user);
            relationDao.save(relation);

            // ** Setting plan
            PlanRequest planRequest = company.getPlan();

            //Setting Selected Plan
            RefierePlanDao planDao = new RefierePlanDao();
            Plan plan = new Plan();
            planDao.findByPlanById(planRequest.getId());
            
            }catch (NullPointerException e) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        return Response.status(200).build();
    }

}
