package co.refiere.models;

/**
 * Created by Esteban H.
 */
public enum DefaultPrize {
    MONEY       (10),
    DISCOUNT    (11),
    BONUS       (12),
    PRIZE       (13),
    UPGRADE     (14);

    private int id;

    DefaultPrize(int id){
        this.id = id;
    }

    public int getPrizeId(){
        return this.id;
    }
}
