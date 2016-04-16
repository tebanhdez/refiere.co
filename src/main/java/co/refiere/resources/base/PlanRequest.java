package co.refiere.resources.base;

import java.util.Date;

public class PlanRequest {

	private String name;
	private float salesPercentaje;
	private int campaignAmount;

	private int referrerAmount;
	private String personalizedEmail;
	private int panelType;
	private LapseRequest timely_report;
	private LapseRequest campaign_lapse;

	//private Integer status; //Set Default to 0:Disable
	private Date startDate;
	private Date endDate;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getSalesPercentaje() {
		return salesPercentaje;
	}

	public void setSalesPercentaje(float salesPercentaje) {
		this.salesPercentaje = salesPercentaje;
	}

	public int getCampaignAmount() {
		return campaignAmount;
	}

	public void setCampaignAmount(int campaignAmount) {
		this.campaignAmount = campaignAmount;
	}

	public LapseRequest getCampaign_lapse() {
		return campaign_lapse;
	}

	public void setCampaign_lapse(LapseRequest campaign_lapse) {
		this.campaign_lapse = campaign_lapse;
	}

	public int getReferrerAmount() {
		return referrerAmount;
	}

	public void setReferrerAmount(int referrerAmount) {
		this.referrerAmount = referrerAmount;
	}

	public String getPersonalizedEmail() {
		return personalizedEmail;
	}

	public void setPersonalizedEmail(String personalizedEmail) {
		this.personalizedEmail = personalizedEmail;
	}

	public int getPanelType() {
		return panelType;
	}

	public void setPanelType(int panelType) {
		this.panelType = panelType;
	}

	public LapseRequest getTimely_report() {
		return timely_report;
	}

	public void setTimely_report(LapseRequest timely_report) {
		this.timely_report = timely_report;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
