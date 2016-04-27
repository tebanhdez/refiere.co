package co.refiere.resources.base;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CompanyRequest {

    @XmlElement(name="UserRequest", type=UserRequest.class)
    private UserRequest user;
    private String name;
    private String address;
    private String email;
    private String telephone;
    @XmlElement(name="PlanRequest", type=PlanRequest.class)
    private PlanRequest plan;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public PlanRequest getPlan() {
        return plan;
    }
    public void setPlan(PlanRequest plan) {
        this.plan = plan;
    }
    public UserRequest getUser() {
        return user;
    }
    public void setUser(UserRequest user) {
        this.user = user;
    }
}