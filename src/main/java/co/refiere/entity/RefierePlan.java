package co.refiere.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="info.Plan")

public class RefierePlan {

    @Column(name="plan_name")
    private String name;
    
    @Column(name="sales_percentaje")
    private int salesPercentaje;
    
    @Column(name="campaign_amount")
    private int campaignAmount;
    
    @ManyToOne
    private RefiereLapse campaign_lapse;

    @Column(name="referrer_amount")
    private int referrerAmount;
    
    @Column(name="personalized_email")
    private String personalizedEmail;
    
    @Column(name="panel_type")
    private byte panelType;
    
    @ManyToOne
    private RefiereLapse timely_report;

    public RefierePlan(String name, int salesPercentaje, int campaignAmount, RefiereLapse campaign_lapse,
        int referrerAmount, String personalizedEmail, byte panelType, RefiereLapse timely_report) {
      super();
      this.name = name;
      this.salesPercentaje = salesPercentaje;
      this.campaignAmount = campaignAmount;
      this.campaign_lapse = campaign_lapse;
      this.referrerAmount = referrerAmount;
      this.personalizedEmail = personalizedEmail;
      this.panelType = panelType;
      this.timely_report = timely_report;
    }

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

    public RefiereLapse getCampaign_lapse() {
      return campaign_lapse;
    }

    public void setCampaign_lapse(RefiereLapse campaign_lapse) {
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

    public byte getPanelType() {
      return panelType;
    }

    public void setPanelType(byte panelType) {
      this.panelType = panelType;
    }

    public RefiereLapse getTimely_report() {
      return timely_report;
    }

    public void setTimely_report(RefiereLapse timely_report) {
      this.timely_report = timely_report;
    }

}
