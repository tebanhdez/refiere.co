package co.refiere.resources.base;

public class LapseRequest {
    private int id;
    private int days;
    private String lapseName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLapseName() {
        return lapseName;
    }

    public void setLapseName(String lapseName) {
        this.lapseName = lapseName;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
