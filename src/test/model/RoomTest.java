package model;

import model.device.AirConditioner;
import model.room.Room;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    Room room;

    @BeforeEach
    void runBefore() {
        room = new Room("Room1");
    }

    @Test
    void testConstructor() {
        assertEquals("Room1", room.getTitle());
    }

    @Test
    void testToJson() {
        JSONObject houseJson = room.toJson();

        assertEquals(2, houseJson.length());

        room.deviceManager().addDevice(new AirConditioner("s"));
        room.deviceManager().addDevice(new AirConditioner("r"));
        assertEquals(2, houseJson.length());
    }
}
