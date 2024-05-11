package persistence;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import model.House;
import model.room.Room;
import model.device.Device;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            House house = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyHouse() {
        JsonReader reader = new JsonReader("./data/testReaderHouse.json");
        try {
            House house = reader.read();
            assertEquals("EmptyHouse", house.getTitle());
            assertEquals(1, house.deviceManager().getNumDevices());
            assertEquals(0, house.roomManager().getNumRooms());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralHouse.json");
        try {
            House house = reader.read();
            assertEquals("GeneralHouse", house.getTitle());
            assertEquals(1, house.deviceManager().getNumDevices());
            assertEquals(2, house.roomManager().getNumRooms());

            Room room1 = house.roomManager().getRooms().get(0);
            Room room2 = house.roomManager().getRooms().get(1);

            assertEquals("Bedroom", room1.getTitle());
            assertEquals(2, room1.deviceManager().getNumDevices());
            assertEquals("Kitchen", room2.getTitle());
            assertEquals(3, room2.deviceManager().getNumDevices());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}