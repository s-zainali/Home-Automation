package model;

import model.device.SmartDoor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeviceTest {

    @BeforeEach
    public void runBefore() {

    }

    @Test
    void testGetCreationDetails() {
        SmartDoor door = new SmartDoor("door");
        assertEquals("You have a new SmartDoor called door added.",
                door.getCreationDetails());
    }
}
