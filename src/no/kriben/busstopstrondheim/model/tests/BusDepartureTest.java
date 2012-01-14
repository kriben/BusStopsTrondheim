package no.kriben.busstopstrondheim.model.tests;

import static org.junit.Assert.assertEquals;
import no.kriben.busstopstrondheim.model.BusDeparture;

import org.junit.Test;

public class BusDepartureTest {
    @Test
    public void testConstruction() {
        String line = "5";
        String estimatedTime = "23:34";
        String scheduledTime = "23:31";
        String destination = "Buenget";
        BusDeparture busDeparture = new BusDeparture(line, scheduledTime, estimatedTime, destination);
        assertEquals(busDeparture.getLine(), line);
        assertEquals(busDeparture.getEstimatedTime(), estimatedTime);
        assertEquals(busDeparture.getScheduledTime(), scheduledTime);
        assertEquals(busDeparture.getDestination(), destination);
    }
}
