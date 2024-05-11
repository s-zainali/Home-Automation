package model.device;

/**
 * Represents a Smart Door which is a subclass of Device.
 */
public class SmartDoor extends Device {

    /**
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> Initialises a new SmartDoor with the name as the parameter provided.
     */
    public SmartDoor(String title) {
        super(title);
        this.type = "SmartDoor";
    }
}
