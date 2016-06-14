package co.refiere.models;

/**
 * Created by Esteban H.
 */
public enum DefaultUserRole {
    SYS     (10, "SYS"),
    ADMIN   (11, "ADMIN"),
    USER    (12, "USER"),
    ACCAD   (13, "ACCAD");

    private final int id;
    private final String roleIdentifier;

    DefaultUserRole(int id, String identifier){
        this.id = id;
        this.roleIdentifier = identifier;
    }

    public int getId(){
        return this.id;
    }

    public String getIdentifier(){
        return this.roleIdentifier;
    }
}
