package co.refiere.models;

/**
 * Created by Esteban H.
 */
public enum DefaultPayment {
    BANK_DEPOSIT(10),
    ELECTRONIC_TRANSACTION(11),
    CREDIT_CARD(12),
    CASH(13),
    CHECK(14);

    private int id;

    DefaultPayment(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
}
