package no.kriben.busstopstrondheim.model.tests;

import static org.junit.Assert.assertEquals;
import no.kriben.busstopstrondheim.model.BusDeparture;

import org.junit.Test;

public class BusDepartureTest {
    @Test
    public void testConstruction() {
        String line = "5";
        String time = "23:34";
        String destination = "Buenget";
        BusDeparture busDeparture = new BusDeparture(line, time, destination);
        assertEquals(busDeparture.getLine(), line);
        assertEquals(busDeparture.getTime(), time);
        assertEquals(busDeparture.getDestination(), destination);
    }
}
