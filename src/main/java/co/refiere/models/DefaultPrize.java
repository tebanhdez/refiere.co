package co.refiere.models;

/**
 * Created by Esteban H.
 */
public enum DefaultPrize {
    MONEY       (10, "MONEY"),
    DISCOUNT    (11, "DISCOUNT"),
    BONUS       (12, "BONUS"),
    PRIZE       (13, "PRIZE"),
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
