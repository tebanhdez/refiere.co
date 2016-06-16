package co.refiere.resources.base;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DatabaseObjectData implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @XmlElement(name="databaseId")
    private int databaseId;
    @XmlElement(name="databaseName")
    private String databaseName;
    
    public DatabaseObjectData() {}
    
    public DatabaseObjectData(int id) {
        setDatabaseId(id);
    }
    
    public int getDatabaseId() {
        return databaseId;
    }
    public void setDatabaseId(int databaseId) {
        this.databaseId = databaseId;
    }
    public String getDatabaseName() {
        return databaseName;
    }
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}
