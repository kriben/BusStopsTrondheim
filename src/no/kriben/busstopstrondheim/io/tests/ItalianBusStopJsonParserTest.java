package no.kriben.busstopstrondheim.io.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import no.kriben.busstopstrondheim.io.ItalianBusStopJsonParser;
import no.kriben.busstopstrondheim.model.BusStop;

import org.junit.Test;

public class ItalianBusStopJsonParserTest {
    @Test
    public void testConstruction() {
        String testData = "{"
                + "   \"Fermate\": ["
                + "       {"
                + "           \"cinFermata\": 100000,"
                + "           \"codAzNodo\": \"16011725\","
                + "           \"codeMobile\": \"1725 (Spongdal                       )\","
                + "           \"descrizione\": \"Spongdal                       (16011725)\","
                + "           \"lat\": 9188077,"
                + "           \"lon\": \"1131764\","
                + "           \"nomeMobile\": \"Spongdal                        (1725)\""
                + "         }" + "     ]" + "}";

        List<BusStop> busStops = ItalianBusStopJsonParser
                .parseBusStops(testData);
        assertEquals(1, busStops.size());
        BusStop busStop = busStops.get(0);
        assertEquals("Spongdal", busStop.getName());
        assertEquals("1725", busStop.getId());
        assertEquals(100000, busStop.getCode());
    }
}
