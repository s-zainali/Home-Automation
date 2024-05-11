package model;

import model.device.AirConditioner;
import model.device.LightBulb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LightBulbTest {

    private LightBulb lightBulb;

    @BeforeEach
    public void runBefore() {
        lightBulb = new LightBulb("Light Bulb 1");
    }

    @Test
    void testConstructor() {
        assertEquals("Light Bulb 1", lightBulb.getTitle());
        assertEquals("LightBulb", lightBulb.getType());
    }
}
