package co.refiere.resources.base;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CampaignRequest {

    private int companyId;
	private int companyDataBase;
    private int prizeForRefiereId;
    private int prizeForRefiereeId;
    private String prizeForRefiere;
    private String prizeForRefieree;
    private String campaignName;

    public int getCompanyId() {
        return companyId;
    }
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
    public int getCompanyDataBase() {
        return companyDataBase;
    }
    public void setCompanyDataBase(int companyDataBase) {
        this.companyDataBase = companyDataBase;
    }
    public int getPrizeForRefiereId() {
        return prizeForRefiereId;
    }
    public void setPrizeForRefiereId(int prizeForRefiereId) {
        this.prizeForRefiereId = prizeForRefiereId;
    }
    public int getPrizeForRefiereeId() {
        return prizeForRefiereeId;
    }
    public void setPrizeForRefiereeId(int prizeForRefiereeId) {
        this.prizeForRefiereeId = prizeForRefiereeId;
    }
    public String getPrizeForRefiere() {
        return prizeForRefiere;
    }
    public void setPrizeForRefiere(String prizeForRefiere) {
        this.prizeForRefiere = prizeForRefiere;
    }
    public String getPrizeForRefieree() {
        return prizeForRefieree;
    }
    public void setPrizeForRefieree(String prizeForRefieree) {
        this.prizeForRefieree = prizeForRefieree;
    }
    public String getCampaignName() {
        return campaignName;
    }
    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }
}
