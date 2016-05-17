package co.refiere.resources.base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlanObjectData implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @XmlElement(name="planId")
    private int planId;
    @XmlElement(name="name")
    private String name;
    @XmlElement(name="campaignPeriodicity")
    private String campaignPeriodicity;
    @XmlElement(name="reportsPeriodicity")
    private String reportsPeriodicity;
    @XmlElement(name="planPrice")
    private float planPrice;
    
    public PlanObjectData(){}
    
    public PlanObjectData(int id){
        setPlanId(id);
    }
    
    public int getPlanId() {
        return planId;
    }
    public void setPlanId(int planId) {
        this.planId = planId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCampaignPeriodicity() {
        return campaignPeriodicity;
    }
    public void setCampaignPeriodicity(String campaignPeriodicity) {
        this.campaignPeriodicity = campaignPeriodicity;
    }
    public String getReportsPeriodicity() {
        return reportsPeriodicity;
    }
    public void setReportsPeriodicity(String reportsPeriodicity) {
        this.reportsPeriodicity = reportsPeriodicity;
    }
    public float getPlanPrice() {
        return planPrice;
    }
    public void setPlanPrice(float planPrice) {
        this.planPrice = planPrice;
    }
}