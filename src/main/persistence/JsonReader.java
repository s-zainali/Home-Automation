package persistence;

import model.House;
import model.device.DeviceManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.room.Room;
import model.device.Device;
import ui.HouseApp;

/*
  Class written taking major inspiration from:
     Title: JsonReader
     Authors: Paul Carter, Felix Grund
     Available at : https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/

/**
 * Represents a reader that reads the smart house from JSON data stored in file
 * */
public class JsonReader {
    private String source;

    /**
     * <b>EFFECTS : </b> constructs reader to read from source file
     */
    public JsonReader(String source) {
        this.source = source;
    }

    /**
     * <b>EFFECTS : </b> reads the House from file and returns it
     * <br>
     * @throws IOException if an error occurs reading data from file
     */
    public House read() throws IOException {
        JSONObject jsonObject;
        try {
            String jsonData = readFile(source);
            jsonObject = new JSONObject(jsonData);
        } catch (Exception e) {
            throw new IOException();
        }
        return parseHouse(jsonObject);
    }

    /**
     * <b>EFFECTS:</b> reads source file as string and returns it
     */
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    /** <b>EFFECTS : </b> parses house from JSON object and
     *  <br>
     * @return WorkRoom
     */
    private House parseHouse(JSONObject jsonObject) {
        House house = new House();
        String houseName = jsonObject.getString("title");
        house.setName(houseName);

        JSONArray rooms = jsonObject.getJSONArray("rooms");
        JSONArray devices = jsonObject.getJSONArray("devices");

        addRooms(house, rooms);
        addDevices(house, devices);
        return house;
    }

     /**
      * <b>MODIFIES: </b> house
      * <br>
      * <b>EFFECTS: </b> parses thingies from JSON object and adds them to workroom
      */
    private void addRooms(House house, JSONArray jsonArray) {
        for (Object json : jsonArray) {
            JSONObject nextRoom = (JSONObject) json;
            addRoom(house, nextRoom);
        }
    }

     /**
      * <b>MODIFIES : </b> house
      * <br>
      * <b>EFFECTS : </b>
      * parses room from JSON object<br>
      * adds the devices to the room<br>
      * adds the room with the devices to house
      */
    private void addRoom(House house, JSONObject jsonObject) {
        String name = jsonObject.getString("title");
        Room room = new Room(name);
        JSONArray devices = jsonObject.getJSONArray("devices");
        addDevices(room, devices);

        house.roomManager().addRoom(room);
    }

    /** <b>MODIFIES :</b>: house
     *  <br>
     *  <b>EFFECTS :</b> parses devices from JSON object and adds them to room
     */
    private void addDevices(Room room, JSONArray devices) {
        for (Object json : devices) {
            JSONObject nextDevice = (JSONObject) json;
            addDevice(room, nextDevice);
        }
    }

    /** <b>MODIFIES</b>: house
     * <b>EFFECTS :</b>: parses thingy from JSON object and adds it to room
     */
    private void addDevice(Room room, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String type = jsonObject.getString("type");

        Device device = HouseApp.createAppropriateDeviceInstance(type, title);

        room.deviceManager().addDevice(device);
    }
}
