package persistence;

import model.House;
import model.device.*;
import model.room.Room;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            House house = new House();
            house.setName("EmptyHouse");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            House house = new House();
            house.setName("EmptyHouse");
            JsonWriter writer = new JsonWriter("./data/testWriteEmptyHouse.json");
            writer.open();
            writer.write(house);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteEmptyHouse.json");
            house = reader.read();
            assertEquals("EmptyHouse", house.getTitle());
            assertEquals(1, house.deviceManager().getNumDevices());
            assertEquals(0, house.roomManager().getNumRooms());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralHouse() {
        try {
            House house = new House();
            house.setName("GeneralHouse");

            Room room1 = new Room("Bedroom");
            room1.deviceManager().addDevice(new AirConditioner("AC 1"));
            room1.deviceManager().addDevice(new SmartDoor("Room Door"));

            Room room2 = new Room("Kitchen");
            room2.deviceManager().addDevice(new LightBulb("Lamp"));
            room2.deviceManager().addDevice(new AirFreshener("Air Freshener 1"));
            room2.deviceManager().addDevice(new SmartDoor("Kitchen Door"));

            house.roomManager().addRoom(room1);
            house.roomManager().addRoom(room2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralHouse.json");
            writer.open();
            writer.write(house);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralHouse.json");
            house = reader.read();
            assertEquals("GeneralHouse", house.getTitle());
            assertEquals(1, house.deviceManager().getNumDevices());
            assertEquals(2, house.roomManager().getNumRooms());

            room1 = house.roomManager().getRooms().get(0);
            room2 = house.roomManager().getRooms().get(1);

            assertEquals("Bedroom", room1.getTitle());
            assertEquals(2, room1.deviceManager().getNumDevices());
            assertEquals("Kitchen", room2.getTitle());
            assertEquals(3, room2.deviceManager().getNumDevices());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}