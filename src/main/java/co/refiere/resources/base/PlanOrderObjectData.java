package co.refiere.resources.base;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlanOrderObjectData implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @XmlElement(name="planOrderId")
    private int planOrderId;
    @XmlElement(name="planOrderCompanyId")
    private int planOrderCompanyId;
    @XmlElement(name="statusId")
    private int statusId;
    @XmlElement(name="paymentId")
    private int paymentId;
    @XmlElement(name="planId")
    private int planId;
    @XmlElement(name="approvedBy")
    private String approvedBy;
    @XmlElement(name="startDate")
    private Date startDate;
    @XmlElement(name="endDate")
    private Date endDate;
    
    public PlanOrderObjectData() {}
    
    public PlanOrderObjectData(int id){
        setPlanOrderId(id);
    }
    
    public int getPlanOrderId() {
        return planOrderId;
    }
    public void setPlanOrderId(int planOrderId) {
        this.planOrderId = planOrderId;
    }
    public int getPlanOrderCompanyId() {
        return planOrderCompanyId;
    }
    public void setPlanOrderCompanyId(int planOrderCompanyId) {
        this.planOrderCompanyId = planOrderCompanyId;
    }
    public int getStatusId() {
        return statusId;
    }
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
    public int getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    public int getPlanId() {
        return planId;
    }
    public void setPlanId(int planId) {
        this.planId = planId;
    }
    public String getApprovedBy() {
        return approvedBy;
    }
    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
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
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
