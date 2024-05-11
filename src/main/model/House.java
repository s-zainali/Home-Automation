package model;


import model.device.SmartDoor;
import model.room.Room;
import model.room.RoomManager;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents a House as a "big" room with rooms and devices inside
 */
public class House extends Room {

    private RoomManager roomManager;

    /**
     * <b>MODIFIES :</b> this
     * <br>
     * <b>EFFECTS  :</b> Creates a new House and adds a device and room manager.
     * <br>
     * Creates a main door to the house and adds it to the list of devices via the device manager.
     */
    public House() {
        super("");
        roomManager = new RoomManager();

        SmartDoor mainDoor = new SmartDoor("Main house door");
        deviceManager.addDevice(mainDoor);
    }

    /**
     * <b>EFFECTS  :</b>
     * @return the Room Manager
     */
    public RoomManager roomManager() {
        return roomManager;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("devices", deviceManager.toJson());
        json.put("rooms", roomManager.toJson());
        json.put("title", getTitle());
        return json;
    }

    public void setName(String name) {
        this.title = name;
    }

}
