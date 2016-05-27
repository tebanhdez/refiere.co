package co.refiere.resources.base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PrizeObjectData implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @XmlElement(name="prizeId")
    private int planId;
    @XmlElement(name="description")
    private String description;
    
    public PrizeObjectData() {}
    
    public PrizeObjectData(int id) {
        setPlanId(id);
    }

    public int getPlanId() {
        return planId;
    }
    public void setPlanId(int planId) {
        this.planId = planId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
