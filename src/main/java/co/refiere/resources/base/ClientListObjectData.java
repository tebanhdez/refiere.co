package co.refiere.resources.base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClientListObjectData implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @XmlElement(name="id")
    private String id;
    
    @XmlElement(name="identificationCardNumber")
    private String identificationCardNumber;
    
    @XmlElement(name="name")
    private String name;
    
    @XmlElement(name="lastName")
    private String lastName;
    
    @XmlElement(name="count")
    private String count;
    
    @XmlElement(name="code")
    private String code;
    
    @XmlElement(name="description")
    private String description;
    
    @XmlElement(name="prizeForRefiere")
    private String prizeForRefiere;
    
    public ClientListObjectData() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentificationCardNumber() {
        return identificationCardNumber;
    }

    public void setIdentificationCardNumber(String identificationCardNumber) {
        this.identificationCardNumber = identificationCardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrizeForRefiere() {
        return prizeForRefiere;
    }

    public void setPrizeForRefiere(String prizeForRefiere) {
        this.prizeForRefiere = prizeForRefiere;
    }
}
