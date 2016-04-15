package co.refiere.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.refiere.dao.RefiereCompanyDao;
import co.refiere.models.RefiereCompany;
import co.refiere.resources.base.CompanyRequest;

@Path("v1/company")
public class CompanyResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCompany(CompanyRequest company) {

		try {
			RefiereCompanyDao companyDao = new RefiereCompanyDao();
			RefiereCompany newCompany = new RefiereCompany();
			
			newCompany.setName(company.getName());
			newCompany.setAddress(company.getAddress());
			newCompany.setEmail(company.getEmail());
			newCompany.setPhone(company.getTelephone());
			//newCompany.setRefiereUserCompaniesForCompanyId(null);
			//newCompany.setRefiereUserCompaniesForUserId(null);
			
			companyDao.save(newCompany);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).build();
	}

}
