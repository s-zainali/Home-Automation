package model.device;

/**
 * Represents an automatic Air freshener which is a subclass of Device.
 */
public class AirFreshener extends Device {

    /**
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> Initialises a new AirFreshener with the name as the parameter provided.
     */
    public AirFreshener(String title) {
        super(title);
        this.type = "AirFreshener";
    }
}
