package co.refiere.models;

/**
 * Created by Esteban H.
 */
public enum DefaultPrize {
    MONEY       (10, "DINERO"),
    DISCOUNT    (11, "DESCUENTO"),
    BONUS       (12, "BONO"),
    PRIZE       (13, "PREMIO"),
    UPGRADE     (14, "UPGRADE");

    private int id;
    private String prizeName;
    
    DefaultPrize(int id, String prizeName) {
        this.id = id;
        this.prizeName = prizeName;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public int getPrizeId(){
        return this.id;
    }
}
