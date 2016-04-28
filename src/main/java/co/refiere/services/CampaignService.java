package co.refiere.services;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import co.refiere.dao.CampaignDao;
import co.refiere.dao.CompanyDatabaseDao;
import co.refiere.dao.PrizeDao;
import co.refiere.dao.RefiereCompanyDao;
import co.refiere.models.Campaign;
import co.refiere.models.Company;
import co.refiere.models.CompanyDatabase;
import co.refiere.models.Prize;
import co.refiere.resources.base.CampaignRequest;

public class CampaignService {

    public Response createCampaign(CampaignRequest campaign) {
        String jsonResponse = "{\"status\":\"OK\", \"campaignId\": %d}";
        CampaignDao campaignDao= new CampaignDao();
        CompanyDatabaseDao dataBaseDao = new CompanyDatabaseDao();
        PrizeDao prizeDao = new PrizeDao();
        
        Campaign campaignModel = new Campaign();
        campaignModel.setName(campaign.getCampaignName());
        RefiereCompanyDao companyDao = new RefiereCompanyDao();
        Company company = companyDao.findCompanyById(campaign.getCompanyId());
        if(company == null)
            return Response.status(Status.NOT_FOUND).entity(javax.ws.rs.client.Entity.json("{\"Error \": \"Company not found \"}")).build();
        campaignModel.setCompany(company);

        CompanyDatabase dataBase = dataBaseDao.findDatabaseById(campaign.getCompanyDataBase());
        if(dataBase == null)
            return Response.status(Status.NOT_FOUND).entity(javax.ws.rs.client.Entity.json("{\"Error \": \"Database not found \"}")).build();
        campaignModel.setCompanyDatabase(dataBase);
        
        Prize refereePrize = prizeDao.findPrizeById(campaign.getPrizeForRefiereeId());
        Prize refierePrize = prizeDao.findPrizeById(campaign.getPrizeForRefiereId());
        if(refereePrize == null || refierePrize == null)
            return Response.status(Status.NOT_FOUND).entity(javax.ws.rs.client.Entity.json("{\"Error \": \"Prize selected not found \"}")).build();
        
        campaignModel.setPrizeByPrizeForRefiereId(refierePrize);
        campaignModel.setPrizeByPrizeForRefereeId(refereePrize);

        campaignModel.setPrizeForReferee(campaign.getPrizeForRefieree());
        campaignModel.setPrizeForRefiere(campaign.getPrizeForRefiere());
        campaignDao.save(campaignModel);
        return Response.ok(String.format(String.format(jsonResponse, campaignModel.getId())), MediaType.APPLICATION_JSON).build();
	}

}
