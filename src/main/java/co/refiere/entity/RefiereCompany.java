package co.refiere.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "info.User")
@DiscriminatorValue("C")
public class RefiereCompany extends RefiereUser{

  @Column(name="company_name")
	private String name;
  
  @Column(name="company_address")
	private String address;
  
  @Column(name="company_email")
	private String email;
  
  @Column(name="company_telephone")
	private String telephone;
  
  @Column(name="company_plan")
  private String plan;

  public RefiereCompany(String login, String password, String name, String address, String email,
      String telephone, String plan) {
    super(login, password);
    this.name = name;
    this.address = address;
    this.email = email;
    this.telephone = telephone;
    this.plan = plan;
  }

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
  public String getPlan() {
    return plan;
  }
  public void setPlan(String plan) {
    this.plan = plan;
  }
}
