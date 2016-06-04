package co.refiere.models;

/**
 * Author: Esteban H.
 */
public enum DefaultPlan {
    BASIC(10),
    ENTERPRICE(11),
    CORPORATE(12)
    ;

    private final int planId;

    DefaultPlan(int id){
        this.planId = id;
    }

    public int getPlanId(){
        return this.planId;
    }
}