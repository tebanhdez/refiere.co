package co.refiere.services;

import java.util.Date;

import javax.ws.rs.core.NoContentException;

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

public class CompanyService {

    public String createCompanyAccount(CompanyRequest company) throws NoContentException {
        String response = "{\"companyId\" : %d, \"userId\" : %d}";
        //Creating user
        RefiereUserDao userDao = new RefiereUserDao();
        SimpleUser user = new SimpleUser();
        user.setLogin(company.getUser().getLogin());
        user.setPassword(company.getUser().getPassword());
        RoleDao roleDao = new RoleDao();
        UserRoles roleID = roleDao.findByRoleId(11);
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
        if(planSelected == null)
            throw new NoContentException("\"Error \": \"Plan not found \"");

        OrderStatus orderPendingApproval = orderStatusDao.findOrderStatusById(12);

        PlanOrder planOrder = new PlanOrder();
        planOrder.setCompany(newCompany);
        planOrder.setPlan(planSelected);
        planOrder.setApprovedBy("");
        planOrder.setPersonalizedEmail( (planRequest.getPersonalizedEmail() == null) ? "" : planRequest.getPersonalizedEmail()); 
        planOrder.setStartDate(new Date());
        planOrder.setEndDate(new Date());

        if(orderPendingApproval == null)
            throw new NoContentException("\"Error \": \"Cannot process your order \"");

        planOrder.setOrderStatus(orderPendingApproval);
        //Saving all objects
        userDao.save(user);
        companyDao.save(newCompany);
        response = String.format(response, newCompany.getId(), user.getId());
        userCompanyRelationDao.save(relation);
        orderDao.save(planOrder);
        return response;//Response.status(200).entity(response).build();
    }
}