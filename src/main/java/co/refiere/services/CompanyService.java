package co.refiere.services;

import java.util.Date;

import javax.ws.rs.core.NoContentException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import co.refiere.dao.OrderStatusDao;
import co.refiere.dao.PlanOrderDao;
import co.refiere.dao.RefiereCompanyDao;
import co.refiere.dao.RefierePlanDao;
import co.refiere.dao.RefiereUserCompanyRelationDao;
import co.refiere.dao.RefiereUserDao;
import co.refiere.dao.RoleDao;
import co.refiere.models.Company;
import co.refiere.models.OrderStatus;
import co.refiere.models.Plan;
import co.refiere.models.PlanOrder;
import co.refiere.models.SimpleUser;
import co.refiere.models.UserCompany;
import co.refiere.models.UserRoles;
import co.refiere.resources.base.CompanyRequest;
import co.refiere.resources.base.PlanRequest;
import co.refiere.resources.util.RefiereInterceptor;

public class CompanyService {

    private static final Log LOGGER = LogFactory.getLog(CompanyService.class);
    
    public String createCompanyAccount(CompanyRequest company) throws NoContentException {
        String response = "{\"companyId\" : %d, \"userId\" : %d}";
        //Creating user
        RefiereUserDao userDao = new RefiereUserDao();
        SimpleUser user = new SimpleUser();
        user.setLogin(company.getUser().getLogin());
        user.setPassword(company.getUser().getPassword());
        RoleDao roleDao = new RoleDao();
        UserRoles roleID = roleDao.findByRoleId(11);
        if(roleID == null){
            LOGGER.error("Default user role not found.");
            throw new NoContentException("\"Error \": \"Default user role not found \"");
        }
        user.setUserRoles(roleID);

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
        if(planSelected == null){
            LOGGER.error("Plan selected not found.");
            throw new NoContentException("\"Error \": \"Plan selected not found \"");
        }
        OrderStatus orderPendingApproval = orderStatusDao.findOrderStatusById(12);
        if(orderPendingApproval == null){
            LOGGER.error("Order pending status not found.");
            throw new NoContentException("\"Error \": \"Cannot process your order \"");
        }
        PlanOrder planOrder = new PlanOrder();
        planOrder.setCompany(newCompany);
        planOrder.setPlan(planSelected);
        planOrder.setApprovedBy("");
        planOrder.setPersonalizedEmail( (planRequest.getPersonalizedEmail() == null) ? "" : planRequest.getPersonalizedEmail()); 
        planOrder.setStartDate(new Date());
        planOrder.setEndDate(new Date());

        planOrder.setOrderStatus(orderPendingApproval);
        //Saving all objects
        userDao.save(user);
        companyDao.save(newCompany);
        response = String.format(response, newCompany.getId(), user.getId());
        LOGGER.info("CompanyCreated: " + newCompany.getName() + ", Admin user: " + user.getLogin());
        userCompanyRelationDao.save(relation);
        orderDao.save(planOrder);
        return response;//Response.status(200).entity(response).build();
    }
}