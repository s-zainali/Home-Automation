package model;

import model.room.RoomManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HouseTest {

    private House house;

    @BeforeEach
    void runBefore() {
        house = new House();
    }

    @Test
    void testConstructorClassTitle() {
        assertEquals("", house.getTitle());
    }

    @Test
    void testConstructorDefaultDevices() {
        assertEquals("Main house door", house.deviceManager().getDevices().get(0).getTitle());
    }

    @Test
    void testConstructorRoomManager() {
        assertEquals("No rooms to display", house.roomManager().listRooms(house));
    }

    @Test
    void testToJsonOnlyNameAndDoor() {
        JSONObject houseJson = house.toJson();

        assertEquals(3, houseJson.length());
    }

    @Test
    void testSetName() {
        house.setName("House");

        assertEquals("House", house.getTitle());
    }
}