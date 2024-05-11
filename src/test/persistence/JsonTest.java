package persistence;

import org.junit.jupiter.api.Test;

import model.House;
import model.room.Room;
import model.device.Device;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkDevice(String title, String type, Room room, Device device) {
        assertEquals(title, device.getTitle());
        assertEquals(type, device.getType());
    }
}