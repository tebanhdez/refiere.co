package co.refiere.services;

import javax.mail.MessagingException;
import javax.ws.rs.core.Response;

import co.refiere.dao.PersonDao;
import co.refiere.dao.ReferencesCodesDao;
import co.refiere.models.Person;
import co.refiere.models.ReferencesCodes;
import co.refiere.models.ReferencesCodesId;
import co.refiere.resources.base.NewUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReferalCodeService {

	public Response registerNewPerson(NewUserRequest newUser) {
        String response = "{\"companyId\" : %d, \"userId\" : %d}";
        try {

            Person person = new Person();
            person.setIdentificationCardNumber(newUser.getIdentificationCardNumber());
            person.setName(newUser.getName());
            person.setLastName(newUser.getLastName());
            person.setEmail(newUser.getEmail());
            person.setPhoneNumber(newUser.getPhoneNumber());
            person.setCompanyDatabase(null);
            PersonDao personDao = new PersonDao();
            LOGGER.info("Aboutto save person");
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
            LOGGER.info("About to sabe referenceCode");
            referencesCodesDao.save(referencesCode);

        }catch (Exception exception) {
            LOGGER.info("SOMETHING REALLY BAD");
            LOGGER.error(exception.getMessage());
        }

		return Response.status(javax.ws.rs.core.Response.Status.OK).build();


	}

}
