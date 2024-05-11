package model;

import model.device.SmartDoor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorTest {

    private SmartDoor smartDoor;

    @BeforeEach
    public void runBefore() {
        smartDoor = new SmartDoor("Door 1");
    }

    @Test
    void testConstructor() {
        assertEquals("Door 1", smartDoor.getTitle());
        assertEquals("SmartDoor", smartDoor.getType());
    }
}
