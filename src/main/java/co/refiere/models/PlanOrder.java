package co.refiere.models;
// Generated Apr 19, 2016 11:27:45 AM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PlanOrder generated by hbm2java
 */
public class PlanOrder implements java.io.Serializable {

    private int id;
    private Company company;
    private OrderStatus orderStatus;
    private Payment payment;
    private Plan plan;
    private short paneltype;
    private String personalizedEmail;
    private String approvedBy;
    private Date startDate;
    private Date endDate;
    private Set planOrderHistories = new HashSet(0);

    public PlanOrder() {
    }

    public PlanOrder(int id, String approvedBy, Date startDate, Date endDate, short paneltype) {
        this.id = id;
        this.approvedBy = approvedBy;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paneltype = paneltype;
    }

    public PlanOrder(int id, Company company, OrderStatus orderStatus, Payment payment, short paneltype, String personalizedEmail, Plan plan, String approvedBy,
            Date startDate, Date endDate, Set planOrderHistories) {
        this.id = id;
        this.company = company;
        this.orderStatus = orderStatus;
        this.payment = payment;
        this.paneltype = paneltype;
        this.plan = plan;
        this.paneltype = paneltype;
        this.personalizedEmail = personalizedEmail;
        this.approvedBy = approvedBy;
        this.startDate = startDate;
        this.endDate = endDate;
        this.planOrderHistories = planOrderHistories;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Payment getPayment() {
        return this.payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Plan getPlan() {
        return this.plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public String getApprovedBy() {
        return this.approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set getPlanOrderHistories() {
        return this.planOrderHistories;
    }

    public void setPlanOrderHistories(Set planOrderHistories) {
        this.planOrderHistories = planOrderHistories;
    }

    public short getPaneltype() {
        return this.paneltype;
    }

    public void setPaneltype(short paneltype) {
        this.paneltype = paneltype;
    }

    public String getPersonalizedEmail() {
        return this.personalizedEmail;
    }

    public void setPersonalizedEmail(String personalizedEmail) {
        this.personalizedEmail = personalizedEmail;
    }

}
