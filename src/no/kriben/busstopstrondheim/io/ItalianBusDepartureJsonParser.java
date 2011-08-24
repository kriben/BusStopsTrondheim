package no.kriben.busstopstrondheim.io;

import java.util.ArrayList;
import java.util.List;

import no.kriben.busstopstrondheim.model.BusDeparture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ItalianBusDepartureJsonParser {

    public static List<BusDeparture> parseBusDepartures(String jsonString) {
        List<BusDeparture> busStops = new ArrayList<BusDeparture>();

        try {
            JSONTokener tokener = new JSONTokener(jsonString);
            JSONObject root = new JSONObject(tokener);

            JSONArray busStopsArray = root.getJSONArray("Orari");
            for (int i = 0; i < busStopsArray.length(); i++) {
                JSONObject object = busStopsArray.getJSONObject(i);

                String time = object.getString("orario");
                // Keep only the string part of the departure time
                time = time.substring(time.length() - 5);  
                
                String line = object.getString("codAzLinea");

                BusDeparture busDeparture = new BusDeparture(line, time);
                busStops.add(busDeparture);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return busStops;
    }

}
