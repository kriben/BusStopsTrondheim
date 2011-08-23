package no.kriben.busstopstrondheim.io;

/**
 * http://en.wikipedia.org/wiki/Transverse_Mercator_projection
 * http://wiki.openstreetmap.org/wiki/Mercator
 */
public class MercatorProjectionUtil {

    private static double R_MAJOR = 6378137.0;
    private static double R_MINOR = R_MAJOR; // Should be 6356752.3142 for
                                             // non-spherical projections
    private static double RATIO = R_MINOR / R_MAJOR;
    private static double ECCENT = Math.sqrt(1.0 - (RATIO * RATIO));
    private static double COM = 0.5 * ECCENT;
    private static double PI_2 = Math.PI * 0.5;

    public static double convertLatitudeFromMercator(double mercatorLatitude) {
        final double ts = Math.exp(-mercatorLatitude / R_MAJOR);
        double phi = PI_2 - 2 * Math.atan(ts);
        double dphi = 1.0;
        int i = 0;
        while ((Math.abs(dphi) > 0.000000001) && (i < 15)) {
            double con = ECCENT * Math.sin(phi);
            dphi = PI_2 - 2
                    * Math.atan(ts * Math.pow((1.0 - con) / (1.0 + con), COM))
                    - phi;
            phi += dphi;
            i++;
        }
        return Math.toDegrees(phi);
    }

    public static double convertLongitudeFromMercator(double mercatorLongitude) {
        return Math.toDegrees(mercatorLongitude) / R_MAJOR;
    }
}
