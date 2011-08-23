package no.kriben.busstopstrondheim.io.tests;

import static org.junit.Assert.assertEquals;
import no.kriben.busstopstrondheim.io.MercatorProjectionUtil;

import org.junit.Test;

public class MercatorProjectUtilTest {
    @Test
    public void testMercatorLatitude() {
        double mercatorLatitude = 9201811.0;
        double latitude = 63.4112661956096;
        assertEquals(latitude,
                MercatorProjectionUtil
                        .convertLatitudeFromMercator(mercatorLatitude), 0.0001);
    }

    @Test
    public void testMercatorLongitude() {
        double mercatorLongitude = 1153379.0;
        double longitude = 10.3610008566504;
        assertEquals(longitude,
                MercatorProjectionUtil
                        .convertLongitudeFromMercator(mercatorLongitude),
                0.0001);
    }
}
