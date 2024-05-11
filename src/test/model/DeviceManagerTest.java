package model;
import model.device.Device;
import model.device.DeviceManager;
import model.device.SmartDoor;
import model.room.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DeviceManagerTest {

    private DeviceManager deviceManager;

    @BeforeEach
    public void runBefore() {
        deviceManager = new DeviceManager();
    }

    @Test
    public void testConstructor() {
        assertEquals(5, deviceManager.getNumAvailableDeviceTypes());
        assertNull(deviceManager.getSelectedDevice());
    }

    @Test
    public void testAddDeviceNoDuplicate() {
        assertEquals(0, deviceManager.getNumDevices());
        assertTrue(deviceManager.addDevice(new SmartDoor("1")));
        assertEquals(1, deviceManager.getNumDevices());
        assertTrue(deviceManager.addDevice(new SmartDoor("2")));
        assertEquals(2, deviceManager.getNumDevices());
    }

    @Test
    public void testAddDeviceDuplicateWithOneItem() {
        assertEquals(0, deviceManager.getNumDevices());
        assertTrue(deviceManager.addDevice(new SmartDoor("1")));
        assertEquals(1, deviceManager.getNumDevices());
        assertFalse(deviceManager.addDevice(new SmartDoor("1")));
        assertEquals(1, deviceManager.getNumDevices());
    }

    @Test
    public void testAddDeviceDuplicateWithManyItems() {
        assertEquals(0, deviceManager.getNumDevices());
        assertTrue(deviceManager.addDevice(new SmartDoor("1")));
        assertEquals(1, deviceManager.getNumDevices());
        assertTrue(deviceManager.addDevice(new SmartDoor("2")));
        assertEquals(2, deviceManager.getNumDevices());
        assertTrue(deviceManager.addDevice(new SmartDoor("3")));
        assertEquals(3, deviceManager.getNumDevices());

        assertFalse(deviceManager.addDevice(new SmartDoor("1")));
        assertEquals(3, deviceManager.getNumDevices());

        assertTrue(deviceManager.addDevice(new SmartDoor("4")));
        assertEquals(4, deviceManager.getNumDevices());

        assertFalse(deviceManager.addDevice(new SmartDoor("4")));
        assertEquals(4, deviceManager.getNumDevices());
    }

    @Test
    public void testRemoveDeviceNoDuplicate() {
        Device device1 = new SmartDoor("1");
        Device device2 = new SmartDoor("2");
        assertEquals(0, deviceManager.getNumDevices());
        assertTrue(deviceManager.addDevice(device1));
        assertEquals(1, deviceManager.getNumDevices());
        assertTrue(deviceManager.addDevice(device2));
        assertEquals(2, deviceManager.getNumDevices());

        assertTrue(deviceManager.removeDevice(device1));
        assertFalse(deviceManager.removeDevice(device1));
        assertEquals(1, deviceManager.getNumDevices());
        assertTrue(deviceManager.removeDevice(device2));
        assertEquals(0, deviceManager.getNumDevices());
    }

    @Test
    public void testCheckDeviceExistsDeviceDoesNotExist() {
        assertFalse(deviceManager.checkDeviceExists("1"));
    }

    @Test
    public void testCheckDeviceExists1DeviceExists() {
        deviceManager.addDevice(new SmartDoor("1"));
        assertTrue(deviceManager.checkDeviceExists("1"));
    }

    @Test
    public void testCheckDeviceExistsMultipleDevicesExist() {
        deviceManager.addDevice(new SmartDoor("1"));
        deviceManager.addDevice(new SmartDoor("2"));
        deviceManager.addDevice(new SmartDoor("3"));
        deviceManager.addDevice(new SmartDoor("4"));
        assertTrue(deviceManager.checkDeviceExists("1"));
        assertTrue(deviceManager.checkDeviceExists("3"));
        assertTrue(deviceManager.checkDeviceExists("2"));
        assertFalse(deviceManager.checkDeviceExists("5"));
        assertFalse(deviceManager.checkDeviceExists("7"));
    }

    @Test
    public void testListDevicesWith0Items() {
        assertEquals("No devices to display", deviceManager.listDevices(new Room("1")));
    }

    @Test
    public void testListDevicesWith1Item() {
        Room room = new Room("1");
        room.deviceManager().addDevice(new SmartDoor("1"));
        assertEquals("There are 1 devices available\nDevices:\n\t[1] 1\n",
                deviceManager.listDevices(room));
    }

    @Test
    public void testListDevicesWithManyItems() {
        Room room = new Room("1");
        room.deviceManager().addDevice(new SmartDoor("1"));
        room.deviceManager().addDevice(new SmartDoor("2"));
        room.deviceManager().addDevice(new SmartDoor("3"));
        room.deviceManager().addDevice(new SmartDoor("4"));
        room.deviceManager().addDevice(new SmartDoor("5"));
        assertEquals("There are 5 devices available\nDevices:\n"
                        +  "\t[1] 1\n"
                        +  "\t[2] 2\n"
                        +  "\t[3] 3\n"
                        +  "\t[4] 4\n"
                        +  "\t[5] 5\n",
                deviceManager.listDevices(room));
    }

    @Test
    public void testSelectedDevice() {
        assertNull(deviceManager.getSelectedDevice());
        Device newDevice = new SmartDoor("1");
        deviceManager.addDevice(newDevice);
        deviceManager.setSelectedDevice(newDevice);
        assertEquals(newDevice, deviceManager.getSelectedDevice());
    }

    @Test
    public void testGetAvailableDeviceTypes() {
        ArrayList<String> exampleDeviceTypes = new ArrayList<String>();
        exampleDeviceTypes.add("SmartDoor");
        exampleDeviceTypes.add("LightBulb");
        exampleDeviceTypes.add("AirFreshener");
        exampleDeviceTypes.add("AutomaticVacuum");
        exampleDeviceTypes.add("AirConditioner");
        ArrayList<String> originalDeviceTypes = deviceManager.getAvailableDeviceTypes();
        originalDeviceTypes = exampleDeviceTypes;
        assertEquals(exampleDeviceTypes, deviceManager.getAvailableDeviceTypes());
    }
}
