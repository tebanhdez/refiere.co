package co.refiere.models;
// Generated Apr 15, 2016 11:32:27 AM by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import java.util.Date;

/**
 * RefierePlan generated by hbm2java
 */
public class RefierePlan implements java.io.Serializable {

	private int id;
	private String name;
	private BigDecimal salesPercentaje;
	private int campaignAmount;
	
	private int referrerAmount;
	private String personalizedEmail;
	private short paneltype;
	private RefiereLapse refiereLapseByReportLapseId;
	private RefiereLapse refiereLapseByCampaignLapseRef;
	
	private Integer status;
	private Date startDate;
	private Date endDate;

	public RefierePlan() {
	}

	public RefierePlan(int id, int campaignAmount, short paneltype, int referrerAmount, BigDecimal salesPercentaje) {
		this.id = id;
		this.campaignAmount = campaignAmount;
		this.paneltype = paneltype;
		this.referrerAmount = referrerAmount;
		this.salesPercentaje = salesPercentaje;
	}

	public RefierePlan(int id, RefiereLapse refiereLapseByReportLapseId, RefiereLapse refiereLapseByCampaignLapseRef,
			int campaignAmount, String name, short paneltype, String personalizedEmail, int referrerAmount,
			BigDecimal salesPercentaje, Integer status, Date startDate, Date endDate) {
		this.id = id;
		this.refiereLapseByReportLapseId = refiereLapseByReportLapseId;
		this.refiereLapseByCampaignLapseRef = refiereLapseByCampaignLapseRef;
		this.campaignAmount = campaignAmount;
		this.name = name;
		this.paneltype = paneltype;
		this.personalizedEmail = personalizedEmail;
		this.referrerAmount = referrerAmount;
		this.salesPercentaje = salesPercentaje;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RefiereLapse getRefiereLapseByReportLapseId() {
		return this.refiereLapseByReportLapseId;
	}

	public void setRefiereLapseByReportLapseId(RefiereLapse refiereLapseByReportLapseId) {
		this.refiereLapseByReportLapseId = refiereLapseByReportLapseId;
	}

	public RefiereLapse getRefiereLapseByCampaignLapseRef() {
		return this.refiereLapseByCampaignLapseRef;
	}

	public void setRefiereLapseByCampaignLapseRef(RefiereLapse refiereLapseByCampaignLapseRef) {
		this.refiereLapseByCampaignLapseRef = refiereLapseByCampaignLapseRef;
	}

	public int getCampaignAmount() {
		return this.campaignAmount;
	}

	public void setCampaignAmount(int campaignAmount) {
		this.campaignAmount = campaignAmount;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getReferrerAmount() {
		return this.referrerAmount;
	}

	public void setReferrerAmount(int referrerAmount) {
		this.referrerAmount = referrerAmount;
	}

	public BigDecimal getSalesPercentaje() {
		return this.salesPercentaje;
	}

	public void setSalesPercentaje(BigDecimal salesPercentaje) {
		this.salesPercentaje = salesPercentaje;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

}
