package model;

import model.device.AirConditioner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AirConditionerTest {

    private AirConditioner airConditioner;

    @BeforeEach
    public void runBefore() {
        airConditioner = new AirConditioner("AC1");
    }

    @Test
    void testConstructor() {
        assertEquals("AC1", airConditioner.getTitle());
        assertEquals("AirConditioner", airConditioner.getType());
    }
}
