package model.device;

/**
 * Represents an automatic Vacuum cleaner (roomba-like) which is a subclass of Device.
 */
public class AutomaticVacuum extends Device {

    /**
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> Initialises a new AutomaticVacuum with the name as the parameter provided.
     */
    public AutomaticVacuum(String title) {
        super(title);
        this.type = "AutomaticVacuum";
    }
}
