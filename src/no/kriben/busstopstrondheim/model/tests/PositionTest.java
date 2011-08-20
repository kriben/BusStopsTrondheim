package no.kriben.busstopstrondheim.model.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import no.kriben.busstopstrondheim.model.Position;

import org.junit.Test;

public class PositionTest {
    @Test
    public void testConstruction() {
        double longitude = 10.0;
        double latitude = 23.34;

        Position location = new Position(latitude, longitude);
        assertEquals(longitude, location.getLongitude(), 0.000001);
        assertEquals(latitude, location.getLatitude(), 0.000001);
    }

    @Test
    public void testPositionToSamePositionDistance() {
        ArrayList<Position> positions = new ArrayList<Position>();
        positions.add(new Position(0.0, 0.0));
        positions.add(new Position(100.0, 50.9));
        positions.add(new Position(140.0, -134.0));

        for (Position p : positions)
            assertEquals(0.0f, p.distanceTo(p), 0.00001);
    }

    @Test
    public void testPositionExample() {
        // Test data from http://williams.best.vwh.net/avform.htm
        Position lax = new Position(33.942522, -118.407161);
        Position jfk = new Position(40.639751, -73.778926);

        //
        float distanceInMeters = 2149.0f * 1.852f;
        float delta = 10.0f;
        assertEquals(distanceInMeters, lax.distanceTo(jfk), delta);
        assertEquals(distanceInMeters, jfk.distanceTo(lax), delta);
    }
}
