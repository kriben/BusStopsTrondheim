package no.kriben.busstopstrondheim.io;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import no.kriben.busstopstrondheim.model.BusStop;
import no.kriben.busstopstrondheim.model.Position;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ItalianBusStopJsonParser {

    public static List<BusStop> parseBusStops(String jsonString) {
        List<BusStop> busStops = new ArrayList<BusStop>();

        try {
            JSONTokener tokener = new JSONTokener(jsonString);
            JSONObject root = new JSONObject(tokener);

            // Quick italian course:
            // Fermate: stops (ie. bus stops in Trondheim)
            // CodAzNodo: ?? (maybe node number?)
            // descrizione: description
            // (lon, lat): longitude, latitude
            JSONArray busStopsArray = root.getJSONArray("Fermate");
            for (int i = 0; i < busStopsArray.length(); i++) {
                JSONObject object = busStopsArray.getJSONObject(i);

                String description = object.getString("descrizione");
                String codeAzNodo = object.getString("codAzNodo");
                String name = extractNameFromDescription(description,
                        codeAzNodo);
                int code = object.getInt("codAzNodo");

                String nomeMobile = object.getString("nomeMobile");
                String id = extractIdFromNomeMobile(nomeMobile);
                BusStop busStop = new BusStop(name, id, code);
                busStop.setPosition(new Position(MercatorProjectionUtil
                        .convertLatitudeFromMercator(object.getDouble("lat")),
                        MercatorProjectionUtil
                                .convertLongitudeFromMercator(object
                                        .getDouble("lon"))));
                busStops.add(busStop);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return busStops;
    }

    private static String extractIdFromNomeMobile(String nomeMobile) {
        Pattern p = Pattern.compile("\\((\\d+)\\)");
        Matcher m = p.matcher(nomeMobile);

        if (m.find()) {
            return m.group(1);
        }

        return "";
    }

    private static String extractNameFromDescription(String description,
            String id) {
        // Expect format (number of white space will vary):
        // "Name         (id)"

        String name = description.replace("(" + id + ")", "");
        return name.trim();
    }
}