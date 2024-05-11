package model.room;

import java.util.ArrayList;

import model.House;

import model.logging.EventLog;
import org.json.JSONArray;

import model.logging.Event;

/**
 * Represents a Room Manager which manages rooms
 */
public class RoomManager {
    ArrayList<Room> rooms;
    Room selectedRoom;

    /**
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> Initialises a new Room Manager with a new list of rooms and a selected room.
     */
    public RoomManager() {
        rooms = new ArrayList<Room>();
        selectedRoom = null;
    }

    /**
     * <b>REQUIRES :</b> Room doesn't already exist in rooms - CHECKED IN METHOD
     * <p>
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> adds a room to the list of rooms only if room not in rooms
     * @return true if device added successfully, otherwise false
     */
    public boolean addRoom(Room room) {
        if (!checkRoomExists(room.getTitle())) {
            rooms.add(room);
            EventLog.getInstance().logEvent(new Event("Room: "
                    + room.getTitle()
                    + " added to list of rooms."));
        } else {
            return false;
        }
        return true;
    }

    /**
     * <b>REQUIRES :</b> Room exists in rooms - CHECKED IN METHOD
     * <p>
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> removes a room from the list of rooms only if room in rooms
     * @return true if device removed successfully, otherwise false
     */
    public boolean removeRoom(Room room) {
        if (checkRoomExists(room.getTitle())) {
            rooms.remove(room);
            EventLog.getInstance().logEvent(new Event("Room: "
                    + room.getTitle()
                    + " removed from list of rooms."));
        } else {
            return false;
        }
        return true;
    }

    /**
     * <b>EFFECTS  :</b>
     * <br>
     * @return true if given room exists and false if given room doesn't exist in rooms
     */
    public boolean checkRoomExists(String roomTitle) {
        for (Room existingRoom : rooms) {
            if (existingRoom.getTitle().equals(roomTitle)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <b>EFFECTS : </b>if rooms exist in the device manager
     * @return string of all the rooms listed out,
     * <br>the string "No rooms to display" if no rooms exist
     */
    public String listRooms(House house) {
        if (house.roomManager().getRooms().isEmpty()) {
            return "No rooms to display";
        } else {
            String outputString = "";
            outputString += "There are " + house.roomManager().getNumRooms() + " rooms available\n";
            outputString += "Rooms:\n";

            for (int i = 0; i < house.roomManager().getNumRooms(); i++) {
                outputString += "\t[" + (i + 1) + "] " + house.roomManager().getRooms().get(i).getTitle() + "\n";
            }
            return outputString;
        }
    }

    /**
     * <b>EFFECTS: </b> returns things in this Room Manager as a JSON array
     */
    public JSONArray toJson() {
        JSONArray jsonArray = new JSONArray();

        for (Room room : getRooms()) {
            jsonArray.put(room.toJson());
        }

        return jsonArray;
    }

    /**
     * <b>REQUIRES :</b> room exists in rooms
     * <p>
     * <b>MODIFIES :</b> this
     * <p>
     * <b>EFFECTS  :</b> removes the specified room from the list of rooms
     * <p>
     * TODO Test and implement this method after Phase 2
     */
    //public  void removeRoom(Room room) {
    //    rooms.remove(room);
    //}

    /**
     * <b>EFFECTS  :</b>
     * @return the number of rooms in rooms
     */
    public int getNumRooms() {
        return rooms.size();
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public Room getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(Room selectedRoom) {
        this.selectedRoom = selectedRoom;
    }
}
