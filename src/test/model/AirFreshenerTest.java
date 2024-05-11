package model;

import model.device.AirFreshener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AirFreshenerTest {

    private AirFreshener airFreshener;

    @BeforeEach
    public void runBefore() {
        airFreshener = new AirFreshener("Flower Smell Air Freshener");
    }

    @Test
    void testConstructor() {
        assertEquals("Flower Smell Air Freshener", airFreshener.getTitle());
        assertEquals("AirFreshener", airFreshener.getType());
    }
}
