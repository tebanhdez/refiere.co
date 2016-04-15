package co.refiere.resources.base;

public class PlanRequest {
  private String name;
  private int salesPercentaje;
  private int campaignAmount;
  private LapseRequest campaign_lapse;
  private int referrerAmount;
  private String personalizedEmail;
  private int panelType;
  private LapseRequest timely_report;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSalesPercentaje() {
    return salesPercentaje;
  }

  public void setSalesPercentaje(int salesPercentaje) {
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
}
