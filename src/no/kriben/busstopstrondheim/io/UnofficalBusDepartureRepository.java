package no.kriben.busstopstrondheim.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import no.kriben.busstopstrondheim.model.BusDeparture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class UnofficalBusDepartureRepository implements BusDepartureRepository {

    @Override
    public List<BusDeparture> getAllForBusStop(int code) {

        List<BusDeparture> departures = new ArrayList<BusDeparture>();
        // [ { "name":"1476 (Stud. samfundet                )",
        // "forecast":[{"rute":"9","ankomst":"22:53","type":"Prev"},
        // {"rute":"36","ankomst":"23:02","type":"Prev"},
        // {"rute":"8","ankomst":"23:05","type":"sched"},
        // {"rute":"54","ankomst":"23:06","type":"sched"} ] } ]

        // String json =
        // "[ { \"name\":\"1476 (Stud. samfundet)\", \"forecast\":[{\"rute\":\"9\",\"ankomst\":\"22:53\",\"type\":\"Prev\"}, {\"rute\":\"8\",\"ankomst\":\"23:05\",\"type\":\"sched\"} ] } ]";
        // URI uri = new
        // URI("http://www.atb.no/xmlhttprequest.php?service=realtime.getBusStopRealtimeForecast&busStopId=100575");

        try {
            String json = getJson("http://www.atb.no/xmlhttprequest.php?service=realtime.getBusStopRealtimeForecast&busStopId="
                    + new Integer(code).toString());

            JSONTokener tokener = new JSONTokener(json);

            JSONArray root = new JSONArray(tokener);
            JSONArray forecastArray = root.getJSONObject(0).getJSONArray(
                    "forecast");
            for (int i = 0; i < forecastArray.length(); i++) {
                JSONObject object = forecastArray.getJSONObject(i);
                String rute = object.getString("rute");
                String time = object.getString("ankomst");
                departures.add(new BusDeparture(rute, time));
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return departures;
    }

    private String readInputStreamAsString(InputStream in) throws IOException {

        BufferedInputStream bis = new BufferedInputStream(in);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result = bis.read();
        while (result != -1) {
            byte b = (byte) result;
            buf.write(b);
            result = bis.read();
        }
        return buf.toString();
    }

    private String getJson(String url) {
        System.setProperty("http.agent", "");
        try {
            URL feedUrl = new URL(url);
            URLConnection conn = feedUrl.openConnection();

            conn.setRequestProperty(
                    "User-agent",
                    "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Trident/4.0; SLCC1; .NET CLR 2.0.50727; .NET CLR 1.1.4322; .NET CLR 3.5.30729; InfoPath.1; .NET CLR 3.0.30618)");

            InputStream inputStream = conn.getInputStream();
            return readInputStreamAsString(inputStream);
        } catch (MalformedURLException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (RuntimeException e) {
            System.out.println(e.toString());
        }
        return "";
    }
    
    public void setStringCache(StringCache stringCache) {};
}
