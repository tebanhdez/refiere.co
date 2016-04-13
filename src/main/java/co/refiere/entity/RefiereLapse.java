package co.refiere.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "info.Time_Lapse")

public class RefiereLapse {
  
  @Id
  @GeneratedValue
  @Column(name = "id")
  private byte id;
  
  @OneToMany
  @Column(name = "name")
  private String lapseName;

  public RefiereLapse(String lapseName) {
    super();
    this.lapseName = lapseName;
  }

  public byte getId() {
    return id;
  }

  public void setId(byte id) {
    this.id = id;
  }

  public String getLapseName() {
    return lapseName;
  }

  public void setLapseName(String lapseName) {
    this.lapseName = lapseName;
  }
}
