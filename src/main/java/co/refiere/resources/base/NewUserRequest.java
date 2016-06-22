package co.refiere.resources.base;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NewUserRequest {

	 	private int campaignId;
	 	private int referalPersonId;
	    private String identificationCardNumber;
	    private String name;
	    private String lastName;
	    private String email;
	    private String phoneNumber;
	    
	    public int getCampaignId() {
			return campaignId;
		}
		public void setCampaignId(int campaignId) {
			this.campaignId = campaignId;
		}
		public int getReferalPersonId() {
			return referalPersonId;
		}
		public void setReferalPersonId(int referalPersonId) {
			this.referalPersonId = referalPersonId;
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
