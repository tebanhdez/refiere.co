
package co.refiere.services;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.NoContentException;

import co.refiere.dao.PersonDao;
import co.refiere.dao.ReferencesCodesDao;
import co.refiere.models.Person;
import co.refiere.models.ReferencesCodes;
import co.refiere.models.ReferencesCodesId;
import co.refiere.resources.base.NewUserRequest;
import co.refiere.services.qrcode.QRCodeService;

import java.util.List;

public class ReferalCodeService {


    private final int MAX_REFERALS = 2;

    public String registerNewPerson(NewUserRequest newUser) throws NoContentException, ForbiddenException {
        String response = "{\"msg\" : \"%s\"}";

        ReferencesCodesDao referencesCodesDao = new ReferencesCodesDao();
        PersonDao personDao = new PersonDao();

        List<ReferencesCodes> referals = referencesCodesDao.findReferalCode(newUser.getReferalCode());
        ReferencesCodes code = null;
        if (referals != null && referals.size() == 1 )
            code = (referals != null ) ? (ReferencesCodes) referals.get(0) : null;
        else
            throw new NoContentException("\"Error \": \" Referal code not found \"");


        Person referalPerson = personDao.findPersonsById(code.getId().getPersonId());
        List<ReferencesCodes> referencesCodes = referencesCodesDao.findCampaignReferalCodesByPerson(code.getId().getCampaignId(), referalPerson.getId());
        if(referencesCodes != null && referencesCodes.size() >= MAX_REFERALS)
            throw new ForbiddenException("\"Error \": \" Referal code not valid \"");

        Person person = new Person();
        person.setIdentificationCardNumber(newUser.getIdentificationCardNumber());
        person.setName(newUser.getName());
        person.setLastName(newUser.getLastName());
        person.setEmail(newUser.getEmail());
        person.setPhoneNumber(newUser.getPhoneNumber());
        person.setCompanyDatabase(null);
        personDao.save(person);

        referencesCodesDao.save(new ReferencesCodes(new ReferencesCodesId(code.getId().getCampaignId(), person.getId()), referalPerson, QRCodeService.generateQRCode()));
        response = String.format(response, "User Registered");

        return response;

    }

}
