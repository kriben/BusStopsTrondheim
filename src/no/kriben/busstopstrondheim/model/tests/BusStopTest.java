package no.kriben.busstopstrondheim.model.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import no.kriben.busstopstrondheim.model.BusStop;
import no.kriben.busstopstrondheim.model.Position;

import org.junit.Test;

public class BusStopTest {
    @Test
    public void testBusStop() {
        String name = "Spongdal";
        String id = "123123123";
        int code = 4444;
        BusStop busStop = new BusStop(name, id, code);
        assertEquals(busStop.getName(), name);
        assertEquals(busStop.getId(), id);
        assertEquals(busStop.getCode(), code);
        assertNull(busStop.getPosition());
    }

    @Test
    public void testPosition() {
        BusStop busStop = new BusStop("Some stop", "23233", 993);
        Position position = new Position(63.4, 10.2);
        busStop.setPosition(position);
        assertEquals(position, busStop.getPosition());
    }
}
