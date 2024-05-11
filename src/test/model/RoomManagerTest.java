package model;
//import model.Room.SmartDoor;

import model.room.Room;
import model.room.RoomManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RoomManagerTest {

    private RoomManager roomManager;

    @BeforeEach
    public void runBefore() {
        roomManager = new RoomManager();
    }

    @Test
    public void testConstructor() {
        assertNull(roomManager.getSelectedRoom());
    }

    @Test
    public void testAddRoomNoDuplicate() {
        assertEquals(0, roomManager.getNumRooms());
        assertTrue(roomManager.addRoom(new Room("1")));
        assertEquals(1, roomManager.getNumRooms());
        assertTrue(roomManager.addRoom(new Room("2")));
        assertEquals(2, roomManager.getNumRooms());
    }

    @Test
    public void testAddRoomDuplicateWithOneItem() {
        assertEquals(0, roomManager.getNumRooms());
        assertTrue(roomManager.addRoom(new Room("1")));
        assertEquals(1, roomManager.getNumRooms());
        assertFalse(roomManager.addRoom(new Room("1")));
        assertEquals(1, roomManager.getNumRooms());
    }

    @Test
    public void testAddRoomDuplicateWithManyItems() {
        assertEquals(0, roomManager.getNumRooms());
        assertTrue(roomManager.addRoom(new Room("1")));
        assertEquals(1, roomManager.getNumRooms());
        assertTrue(roomManager.addRoom(new Room("2")));
        assertEquals(2, roomManager.getNumRooms());
        assertTrue(roomManager.addRoom(new Room("3")));
        assertEquals(3, roomManager.getNumRooms());

        assertFalse(roomManager.addRoom(new Room("1")));
        assertEquals(3, roomManager.getNumRooms());

        assertTrue(roomManager.addRoom(new Room("4")));
        assertEquals(4, roomManager.getNumRooms());

        assertFalse(roomManager.addRoom(new Room("4")));
        assertEquals(4, roomManager.getNumRooms());
    }

    @Test
    public void testRemoveRoomNoDuplicate() {
        Room room1 = new Room("1");
        Room room2 = new Room("2");
        assertEquals(0, roomManager.getNumRooms());
        assertTrue(roomManager.addRoom(room1));
        assertEquals(1, roomManager.getNumRooms());
        assertTrue(roomManager.addRoom(room2));
        assertEquals(2, roomManager.getNumRooms());

        assertTrue(roomManager.removeRoom(room1));
        assertFalse(roomManager.removeRoom(room1));
        assertEquals(1, roomManager.getNumRooms());
        assertTrue(roomManager.removeRoom(room2));
        assertEquals(0, roomManager.getNumRooms());
    }

    @Test
    public void testCheckRoomExistsRoomDoesNotExist() {
        assertFalse(roomManager.checkRoomExists("1"));
    }

    @Test
    public void testCheckRoomExists1RoomExists() {
        roomManager.addRoom(new Room("1"));
        assertTrue(roomManager.checkRoomExists("1"));
    }

    @Test
    public void testCheckRoomExistsMultipleRoomsExist() {
        roomManager.addRoom(new Room("1"));
        roomManager.addRoom(new Room("2"));
        roomManager.addRoom(new Room("3"));
        roomManager.addRoom(new Room("4"));
        assertTrue(roomManager.checkRoomExists("1"));
        assertTrue(roomManager.checkRoomExists("3"));
        assertTrue(roomManager.checkRoomExists("2"));
        assertFalse(roomManager.checkRoomExists("5"));
        assertFalse(roomManager.checkRoomExists("7"));
    }

    @Test
    public void testListRoomsWith0Items() {
        assertEquals("No rooms to display", roomManager.listRooms(new House()));
    }

    @Test
    public void testListRoomsWith1Item() {
        House house = new House();
        house.roomManager().addRoom(new Room("1"));
        assertEquals("There are 1 rooms available\nRooms:\n\t[1] 1\n",
                roomManager.listRooms(house));
    }

    @Test
    public void testListRoomsWithManyItems() {
        House house = new House();
        house.roomManager().addRoom(new Room("1"));
        house.roomManager().addRoom(new Room("2"));
        house.roomManager().addRoom(new Room("3"));
        house.roomManager().addRoom(new Room("4"));
        house.roomManager().addRoom(new Room("5"));
        assertEquals("There are 5 rooms available\nRooms:\n"
                        +  "\t[1] 1\n"
                        +  "\t[2] 2\n"
                        +  "\t[3] 3\n"
                        +  "\t[4] 4\n"
                        +  "\t[5] 5\n",
                roomManager.listRooms(house));
    }

    @Test
    public void testSelectedRoom() {
        assertNull(roomManager.getSelectedRoom());
        Room newRoom = new Room("1");
        roomManager.addRoom(newRoom);
        roomManager.setSelectedRoom(newRoom);
        assertEquals(newRoom, roomManager.getSelectedRoom());
    }

    @Test
    public void testToJsonAddFewRooms() {
        roomManager.addRoom(new Room("Room 1"));
        roomManager.addRoom(new Room("Room 2"));
        roomManager.addRoom(new Room("Room 3"));
        roomManager.addRoom(new Room("Room 4"));
        assertEquals(4, roomManager.toJson().length());
    }

    @Test
    public void testToJsonEmpty() {
        assertEquals(0, roomManager.toJson().length());
    }
}
