package co.refiere.models;
// Generated Apr 19, 2016 11:27:45 AM by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Prize generated by hbm2java
 */
public class Prize implements java.io.Serializable {

    private int id;
    private String description;
    private Set campaignsForPrizeForRefiereId = new HashSet(0);
    private Set campaignsForPrizeForRefereeId = new HashSet(0);

    public Prize() {
    }

    public Prize(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Prize(int id, String description, Set campaignsForPrizeForRefiereId, Set campaignsForPrizeForRefereeId) {
        this.id = id;
        this.description = description;
        this.campaignsForPrizeForRefiereId = campaignsForPrizeForRefiereId;
        this.campaignsForPrizeForRefereeId = campaignsForPrizeForRefereeId;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set getCampaignsForPrizeForRefiereId() {
        return this.campaignsForPrizeForRefiereId;
    }

    public void setCampaignsForPrizeForRefiereId(Set campaignsForPrizeForRefiereId) {
        this.campaignsForPrizeForRefiereId = campaignsForPrizeForRefiereId;
    }

    public Set getCampaignsForPrizeForRefereeId() {
        return this.campaignsForPrizeForRefereeId;
    }

    public void setCampaignsForPrizeForRefereeId(Set campaignsForPrizeForRefereeId) {
        this.campaignsForPrizeForRefereeId = campaignsForPrizeForRefereeId;
    }

}