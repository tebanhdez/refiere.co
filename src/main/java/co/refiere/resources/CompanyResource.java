package co.refiere.resources;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import co.refiere.dao.OrderStatusDao;
import co.refiere.dao.PlanOrderDao;
import co.refiere.dao.RefiereCompanyDao;
import co.refiere.dao.RefierePlanDao;
import co.refiere.dao.RefiereUserCompanyRelationDao;
import co.refiere.dao.RefiereUserDao;
import co.refiere.models.Company;
import co.refiere.models.OrderStatus;
import co.refiere.models.Plan;
import co.refiere.models.PlanOrder;
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

            //Creating company
            RefiereCompanyDao companyDao = new RefiereCompanyDao();
            Company newCompany = new Company();
            newCompany.setName(company.getName());
            newCompany.setAddress(company.getAddress());
            newCompany.setEmail(company.getEmail());
            newCompany.setPhone(company.getTelephone());

            //Linking main user to company
            RefiereUserCompanyRelationDao userCompanyRelationDao = new RefiereUserCompanyRelationDao();
            UserCompany relation = new UserCompany();
            relation.setCompany(newCompany);
            relation.setSimpleUser(user);

            // Setting plan
            PlanRequest planRequest = company.getPlan();

            // Injecting DAOs
            RefierePlanDao planDao = new RefierePlanDao();
            PlanOrderDao orderDao = new PlanOrderDao();
            OrderStatusDao orderStatusDao = new OrderStatusDao();
            
            Plan planSelected = planDao.findByPlanById(planRequest.getId());
            if(planSelected == null)
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(javax.ws.rs.client.Entity.json("{\"Error \": \"Plan not found \"}")).build();
            
            OrderStatus orderPendingApproval = orderStatusDao.findOrderStatusById(2);
            
            PlanOrder planOrder = new PlanOrder();
            planOrder.setCompany(newCompany);
            planOrder.setPlan(planSelected);
            planOrder.setApprovedBy("");
            planOrder.setPersonalizedEmail( (planRequest.getPersonalizedEmail() == null) ? "" : planRequest.getPersonalizedEmail()); 
            planOrder.setStartDate(new Date());
            planOrder.setEndDate(new Date());

            if(orderPendingApproval == null)
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(javax.ws.rs.client.Entity.json("{\"Error \": \"Cannot process your order \"}")).build();

            planOrder.setOrderStatus(orderPendingApproval);
            //Saving all objects
            planDao.save(planSelected);
            userDao.save(user);
            companyDao.save(newCompany);
            userCompanyRelationDao.save(relation);
            orderDao.save(planOrder);
            }catch (NullPointerException e) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        return Response.status(200).build();
    }

}
