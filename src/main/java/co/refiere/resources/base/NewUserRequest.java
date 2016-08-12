package co.refiere.resources.base;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NewUserRequest {

	 	private String referalCode;
	    private String identificationCardNumber;
	    private String name;
	    private String lastName;
	    private String email;
	    private String phoneNumber;

	    public String getReferalCode() {
			return referalCode;
		}
		public void setReferalCode(String referalCode) {
			this.referalCode = referalCode;
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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

}
