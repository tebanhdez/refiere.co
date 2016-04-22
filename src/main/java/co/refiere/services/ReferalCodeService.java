package co.refiere.services;

import javax.ws.rs.core.Response;

import co.refiere.dao.CompanyDatabaseDao;
import co.refiere.dao.PersonDao;
import co.refiere.models.Person;
import co.refiere.resources.base.NewUserRequest;

public class ReferalCodeService {

	public Response registerNewPerson(NewUserRequest newUser) {
		
		
		Person person = new Person();
		person.setIdentificationCardNumber(newUser.getIdentificationCardNumber());
		person.setName(newUser.getName());
		person.setLastName(newUser.getLastName());
		person.setEmail(newUser.getEmail());
		person.setPhoneNumber(newUser.getPhoneNumber());
		person.setCompanyDatabase(null);
		
		CompanyDatabaseDao companyDataBase = new CompanyDatabaseDao();
		companyDataBase.findCompanyDatabaseById(newUser.getCampaignId());
		
		PersonDao personDao = new PersonDao();
		personDao.save(person);
		return Response.status(javax.ws.rs.core.Response.Status.OK).build();
		
		
	}

}
