
package co.refiere.services;

import javax.ws.rs.core.NoContentException;

import co.refiere.dao.PersonDao;
import co.refiere.dao.ReferencesCodesDao;
import co.refiere.models.Person;
import co.refiere.models.ReferencesCodes;
import co.refiere.models.ReferencesCodesId;
import co.refiere.resources.base.NewUserRequest;

public class ReferalCodeService {

	public String registerNewPerson(NewUserRequest newUser) throws NoContentException {
		String response = "{\"msg\" : \"%s\"}";

		Person person = new Person();
		person.setIdentificationCardNumber(newUser.getIdentificationCardNumber());
		person.setName(newUser.getName());
		person.setLastName(newUser.getLastName());
		person.setEmail(newUser.getEmail());
		person.setPhoneNumber(newUser.getPhoneNumber());
		person.setCompanyDatabase(null);
		PersonDao personDao = new PersonDao();
		personDao.save(person);

		Person referalPerson = personDao.findPersonsById(newUser.getReferalPersonId());
		if(referalPerson == null)
			throw new NoContentException("\"Error \": \" Referal person not found \"");
		ReferencesCodesId referencesCodesId = new ReferencesCodesId();
		referencesCodesId.setCampaignId(newUser.getCampaignId());
		referencesCodesId.setPersonId(person.getId());

		ReferencesCodesDao referencesCodesDao = new ReferencesCodesDao();
		ReferencesCodes referencesCode = new ReferencesCodes();
		referencesCode.setId(referencesCodesId);
		referencesCode.setPerson(referalPerson);

		referencesCodesDao.save(referencesCode);
		response = String.format(response,"User Registered");

		return response;


	}

}
