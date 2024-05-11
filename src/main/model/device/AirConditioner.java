package model.device;

/**
 * Represents an automatic Air conditioner which is a subclass of Device.
 */
public class AirConditioner extends Device {

    /**
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> Initialises a new AutomaticVacuum with the name as the parameter provided.
     */
    public AirConditioner(String title) {
        super(title);
        this.type = "AirConditioner";
    }
}
