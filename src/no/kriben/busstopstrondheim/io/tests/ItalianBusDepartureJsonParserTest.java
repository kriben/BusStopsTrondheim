package no.kriben.busstopstrondheim.io.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import no.kriben.busstopstrondheim.io.ItalianBusDepartureJsonParser;
import no.kriben.busstopstrondheim.model.BusDeparture;

import org.junit.Test;

public class ItalianBusDepartureJsonParserTest {

    @Test
    public void testConstruction() {

        String testData = "{\"InfoNodo\": ["
                + "   {"
                + "      \"bitMaskProprieta\": \"\","
                + "       \"codAzNodo\": \"16011404\","
                + "       \"codeMobile\": \"1404 (Solsiden                       )\","
                + "       \"coordLat\": \"63.434006\","
                + "       \"coordLon\": \"10.412957\","
                + "       \"descrNodo\": \"1404 (Solsiden                       )\","
                + "       \"nomeNodo\": \"Solsi\","
                + "       \"nome_Az\": \"AtB\"" + "   }" + "],"
                + "\"Orari\": [" + "   {"
                + "       \"capDest\": \"Dronningens gt.                \","
                + "       \"codAzLinea\": \"11\","
                + "       \"descrizioneLinea\": \"11\","
                + "       \"orario\": \"21/08/2011 22:01\","
                + "       \"orarioSched\": \"21/08/2011 22:01\","
                + "       \"statoPrevisione\": \"sched\"" + "   }," + "   {"
                + "       \"capDest\": \"Munkegata - M2                \","
                + "       \"codAzLinea\": \"6\", "
                + "       \"descrizioneLinea\": \"6\","
                + "       \"orario\": \"21/08/2011 22:27\","
                + "       \"orarioSched\": \"21/08/2011 22:22\","
                + "       \"statoPrevisione\": \"Prev\"" + "   }," + "  ]"
                + " }";

        List<BusDeparture> busStops = ItalianBusDepartureJsonParser
                .parseBusDepartures(testData);

        assertEquals(2, busStops.size());
        BusDeparture departure1 = busStops.get(0);
        assertEquals("11", departure1.getLine());
        assertEquals("22:01", departure1.getTime());

        BusDeparture departure2 = busStops.get(1);
        assertEquals("6", departure2.getLine());
        assertEquals("22:27", departure2.getTime());
    }
}
