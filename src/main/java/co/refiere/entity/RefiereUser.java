package co.refiere.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
 
@Entity
@Table(name = "info.User")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="discriminator",
    discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue(value="U")
public class RefiereUser implements Serializable {

  @Id
  @GeneratedValue
  @Column(name = "user_id")
	private Long id;
	
	@Column (name = "user_login")
	private String login;
	
	@Column (name = "user_password")
	private String password;
	
  public RefiereUser(String login, String password) {
    super();
    this.login = login;
    this.password = password;
  }
 
	public String getLogin() {
		return login;
	}
 
  public void setLogin(String login) {
		this.login = login;
 	}
	
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}