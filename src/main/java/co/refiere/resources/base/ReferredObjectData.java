package co.refiere.resources.base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import co.refiere.models.Person;
import co.refiere.models.ReferencesCodesId;

public class ReferredObjectData  implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @XmlElement(name="referredId")
    private ReferencesCodesId referredId;
    @XmlElement(name="person")
    private Person person;
    
    public ReferredObjectData(){}
    
    public ReferredObjectData(ReferencesCodesId id){
        setReferredId(id);
    }

    public ReferencesCodesId getReferredId() {
        return referredId;
    }

    public void setReferredId(ReferencesCodesId referredId) {
        this.referredId = referredId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
}
