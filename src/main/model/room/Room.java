package model.room;

import model.device.DeviceManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Represents a room which can have devices within
 */
public class Room {

    protected String title;
    protected DeviceManager deviceManager;

    /**
     * <b>EFFECTS  :</b> Creates a new room instance with a title and a device manager
     */
    public Room(String title) {
        this.title = title;
        deviceManager = new DeviceManager();
    }

    /**
     * <b>EFFECTS: </b> returns the Room's title and devices as a JSON object
     */
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("devices", deviceManager.toJson());
        json.put("title", getTitle());
        return json;
    }

    /**
     * <b>EFFECTS  :</b> returns the device manager
     */
    public DeviceManager deviceManager() {
        return deviceManager;
    }

    public String getTitle() {
        return title;
    }
}
