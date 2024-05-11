package model.device;

/**
 * Represents an automatic Light Bulb which is a subclass of Device.
 */
public class LightBulb extends Device {

    /**
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> Initialises a new LightBulb with the name as the parameter provided.
     */
    public LightBulb(String title) {
        super(title);
        this.type = "LightBulb";
    }
}
