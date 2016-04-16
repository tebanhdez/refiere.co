package co.refiere.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.refiere.dao.RefiereCompanyDao;
import co.refiere.dao.RefiereUserCompanyRelationDao;
import co.refiere.dao.RefiereUserDao;
import co.refiere.models.RefiereCompany;
import co.refiere.models.RefiereUser;
import co.refiere.models.RefiereUserCompany;
import co.refiere.resources.base.CompanyRequest;

@Path("v1/company")
public class CompanyResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
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
			
			//Linkind main user to company
			RefiereUserCompanyRelationDao relationDao = new RefiereUserCompanyRelationDao();
			RefiereUserCompany relation = new RefiereUserCompany();
			relation.setRefiereCompany(newCompany);
			relation.setRefiereUser(user);
			relationDao.save(relation);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).build();
	}

}
