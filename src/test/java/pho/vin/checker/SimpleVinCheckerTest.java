package pho.vin.checker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleVinCheckerTest {

    @Test
    void shouldSimpleCheckVin() {
        String vin = "JH4CU2F82EC001620";
        assertTrue(SimpleVinChecker.simpleCheck(vin));
    }

    @Test
    void shouldNotSimpleCheckVin() {
        String vin = "JH4CU2F82EQ001620";
        assertFalse(SimpleVinChecker.simpleCheck(vin));
    }

}