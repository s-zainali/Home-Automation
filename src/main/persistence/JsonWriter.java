package persistence;

import org.json.JSONObject;

import model.House;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/*
  Class written taking major inspiration from:
    Title: JsonWriter
    Authors: Paul Carter, Felix Grund
    Available at: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

/**
 * Represents a writer that writes JSON representation of house to file
 * */
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    /**
     * <b>EFFECTS : </b> constructs writer to write to destination file
     */
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    /**
     * <b>MODIFIES : </b>this
     * <br>
     * <b>EFFECTS: </b> opens writer;
     * @throws FileNotFoundException if destination file cannot
     *      be opened for writing
     */
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    /**
     * <b>MODIFIES: </b> this
     * <br>
     * <b>EFFECTS: </b> writes JSON representation of house to file
     */
    public void write(House house) {
        JSONObject json = house.toJson();
        saveToFile(json.toString(TAB));
    }

     /**
      * <b>MODIFIES: </b> this
      * <br>
      * <b>EFFECTS: </b> closes writer
      */
    public void close() {
        writer.close();
    }

     /**
      * <b>MODIFIES: </b> this
      * <br>
      * <b>EFFECTS: </b> writes string to file
      */
    private void saveToFile(String json) {
        writer.print(json);
    }
}
