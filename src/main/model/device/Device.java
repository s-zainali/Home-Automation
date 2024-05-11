package model.device;

import org.json.JSONObject;

/**
 * Represents a generic device who has a title and type
 */
public abstract class Device {
    protected String type;
    protected String title; // Unique name for each device

    /**
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> Initialises a new device with title set to the parameter provided
     */
    public Device(String title) {
        this.title = title;
    }

    /**
     * <b>EFFECTS  :</b> Returns a string with creation details to be printed by the UI handler.
     */
    public String getCreationDetails() {
        return "You have a new "
                + getType()
                + " called "
                + getTitle()
                + " added.";
    }

    /**
     * <b>EFFECTS: </b> returns the Device's title as a JSON object
     */
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", getTitle());
        json.put("type", getType());
        return json;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }
}
