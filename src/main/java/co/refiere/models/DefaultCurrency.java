package co.refiere.models;

/**
 * Created by Esteban H.
 */
public enum DefaultCurrency {

    CRC(10),
    USD(11),
    EUR(12);

    private int id;

    DefaultCurrency(int id){
        this.id = id;
    }

    public int getCurrencyId(){
        return this.id;
    }
}
