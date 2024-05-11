package model.device;

import model.logging.Event;
import model.logging.EventLog;
import model.room.Room;
import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Represents a Device Manager which manages devices within a room
 */
public class DeviceManager {
    private ArrayList<Device> devices;
    private Device selectedDevice;
    private ArrayList<String> availableDeviceTypes;

    /**
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> Initialises a new Device Manager with a new list of available devices,
     * <br>new list of devices and a selected device.
     */
    public DeviceManager() {
        availableDeviceTypes = new ArrayList<String>();
        availableDeviceTypes.add("SmartDoor");
        availableDeviceTypes.add("LightBulb");
        availableDeviceTypes.add("AirFreshener");
        availableDeviceTypes.add("AutomaticVacuum");
        availableDeviceTypes.add("AirConditioner");
        devices = new ArrayList<Device>();
        selectedDevice = null;
    }

    /**
     * <b>REQUIRES :</b> Device doesn't already exist in devices - CHECKED IN METHOD
     * <p>
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> adds a device to the list of devices only if device not in devices
     * @return true if device added successfully, otherwise false
     */
    public boolean addDevice(Device device) {
        if (!checkDeviceExists(device.getTitle())) {
            devices.add(device);
            EventLog.getInstance().logEvent(new Event("Device: "
                    + device.getTitle()
                    + " added to list of devices."));
        } else {
            return false;
        }
        return true;
    }

    public boolean removeDevice(Device device) {
        if (checkDeviceExists(device.getTitle())) {
            devices.remove(device);
            EventLog.getInstance().logEvent(new Event("Device: "
                    + device.getTitle()
                    + " removed from list of devices."));
        } else {
            return false;
        }
        return true;
    }

    /**
     * <b>EFFECTS  :</b>
     * <br>
     * @return true if given device exists and false if given device doesn't exist in devices
     */
    public boolean checkDeviceExists(String title) {
        for (Device existingDevice : devices) {
            if (existingDevice.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <b>EFFECTS : </b>if devices exist in the device manager
     * @return string of all the devices in a given room listed out,
     * <br>the string "No devices to display" if no devices exist
     */
    public String listDevices(Room room) {
        if (room.deviceManager().getNumDevices() == 0) {
            return "No devices to display";
        } else {
            String outputString = "";
            outputString += "There are " + room.deviceManager().getNumDevices() + " devices available\n";
            outputString += "Devices:\n";

            for (int i = 0; i < room.deviceManager().getNumDevices(); i++) {
                outputString += "\t[" + (i + 1) + "] " + room.deviceManager().getDevices().get(i).getTitle() + "\n";
            }

            return outputString;
        }
    }

    /**
     * <b>EFFECTS: </b> returns things in this Device Manager as a JSON array
     */
    public JSONArray toJson() {
        JSONArray jsonArray = new JSONArray();

        for (Device device : getDevices()) {
            jsonArray.put(device.toJson());
        }

        return jsonArray;
    }

    /**
     * <b>REQUIRES :</b> device exists in devices
     * <p>
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> removes the specified device from the list of devices
     * <p>
     * TODO Test and implement this method after Phase 1
     */
    //public  void removeRoom(Device device) {
    //   devices.remove(device);
    //}

    /**
     * <b>EFFECTS  :</b>
     * @return the number of devices in devices
     */
    public int getNumDevices() {
        return devices.size();
    }

    /**
     * <b>EFFECTS  :</b>
     * @return the number of available device types
     */
    public int getNumAvailableDeviceTypes() {
        return availableDeviceTypes.size();
    }


    public ArrayList<String> getAvailableDeviceTypes() {
        return availableDeviceTypes;
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public Device getSelectedDevice() {
        return selectedDevice;
    }

    public void setSelectedDevice(Device selectedDevice) {
        this.selectedDevice = selectedDevice;
    }
}
