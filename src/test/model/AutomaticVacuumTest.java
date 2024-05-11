package model;

import model.device.AutomaticVacuum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AutomaticVacuumTest {

    private AutomaticVacuum automaticVacuum;

    @BeforeEach
    public void runBefore() {
        automaticVacuum = new AutomaticVacuum("Vacuum Cleaner 1");
    }

    @Test
    void testConstructor() {
        assertEquals("Vacuum Cleaner 1", automaticVacuum.getTitle());
        assertEquals("AutomaticVacuum", automaticVacuum.getType());
    }
}
