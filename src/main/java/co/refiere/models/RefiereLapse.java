package co.refiere.models;

/**
 * Created by teban on 6/3/16.
 */
public enum RefiereLapse {
    DAILY       (10, "Diario"),
    WEEKLY      (11, "Semanal"),
    FORTNIGHTLY (12, "Quincenal"),
    MONTHLY     (13, "Mensual"),
    BIMONTHLY   (14, "Bimestral"),
    QUARTERLY   (15, "Trimestral"),
    FOURMONTH   (16, "Cuatrimestral"),
    BIANNUAL    (17, "Semestral"),
    ANNUAL      (18, "Anual");

    private final int id;
    private final String name;

    RefiereLapse(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getLapseId(){
        return this.id;
    }
    public String getLapseName(){
        return this.name;
    }
}
