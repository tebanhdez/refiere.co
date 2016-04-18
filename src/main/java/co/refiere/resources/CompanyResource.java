package co.refiere.resources;

import java.math.BigDecimal;

import javax.ws.rs.BadRequestException;
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
import co.refiere.models.RefiereCompany;
import co.refiere.models.RefiereLapse;
import co.refiere.models.RefierePlan;
import co.refiere.models.RefiereUser;
import co.refiere.models.RefiereUserCompany;
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
            RefiereUser user = new RefiereUser();
            user.setLogin(company.getUser().getLogin());
            user.setPassword(company.getUser().getPassword());
            userDao.save(user);

            //Creating company
            RefiereCompanyDao companyDao = new RefiereCompanyDao();
            RefiereCompany newCompany = new RefiereCompany();
            newCompany.setName(company.getName());
            newCompany.setAddress(company.getAddress());
            newCompany.setEmail(company.getEmail());
            newCompany.setPhone(company.getTelephone());
            companyDao.save(newCompany);

            //Linking main user to company
            RefiereUserCompanyRelationDao relationDao = new RefiereUserCompanyRelationDao();
            RefiereUserCompany relation = new RefiereUserCompany();
            relation.setRefiereCompany(newCompany);
            relation.setRefiereUser(user);
            relationDao.save(relation);

            // ** Setting plan
            PlanRequest planRequest = company.getPlan();

            //Setting Lapses
            RefiereLapseDao lapseDao = new RefiereLapseDao();
            RefiereLapse lapseCampaigns = new RefiereLapse();
            lapseCampaigns.setDays(planRequest.getCampaign_lapse().getDays());
            lapseCampaigns.setName(planRequest.getCampaign_lapse().getLapseName());

            RefiereLapse lapseReports = new RefiereLapse();
            lapseReports.setDays(planRequest.getTimely_report().getDays());
            lapseReports.setName(planRequest.getTimely_report().getLapseName());

            lapseDao.save(lapseReports);
            lapseDao.save(lapseCampaigns);

            //Setting Selected Plan
            RefierePlanDao planDao = new RefierePlanDao();
            RefierePlan plan = new RefierePlan();
            plan.setName(planRequest.getName());
            plan.setSalesPercentaje(BigDecimal.valueOf(planRequest.getSalesPercentaje()));
            plan.setCampaignAmount(plan.getCampaignAmount());
            plan.setReferrerAmount(plan.getReferrerAmount());
            plan.setPersonalizedEmail(plan.getPersonalizedEmail());
            plan.setPaneltype(plan.getPaneltype());
            plan.setStartDate(plan.getStartDate());
            plan.setEndDate(plan.getEndDate());
            //Inactive by Default
            plan.setStatus(0);
            plan.setRefiereLapseByCampaignLapseRef(lapseCampaigns);
            plan.setRefiereLapseByReportLapseId(lapseReports);
            planDao.save(plan);
            }catch (NullPointerException e) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        return Response.status(200).build();
    }

}
