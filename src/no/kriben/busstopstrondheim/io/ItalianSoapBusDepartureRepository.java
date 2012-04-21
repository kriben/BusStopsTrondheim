package no.kriben.busstopstrondheim.io;

import java.net.MalformedURLException;
import java.util.List;

import no.kriben.busstopstrondheim.model.BusDeparture;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class ItalianSoapBusDepartureRepository implements
        BusDepartureRepository {

    private String username_;
    private String password_;
    final private int NUM_HOURS = 6;
    final private int MAX_DEPARTURES = 50;
    
    public ItalianSoapBusDepartureRepository(String username, String password) {
        username_ = username;
        password_ = password;
    }

    @Override
    public List<BusDeparture> getAllForBusStop(int code) {
        String result = "";
        try {
            result = getData(code);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ItalianBusDepartureJsonParser.parseBusDepartures(result);
    }

    public String getData(int busStopId) throws MalformedURLException {

        String SOAP_ACTION = "http://miz.it/infotransit/getUserRealTimeForecastExByStop";
        String METHOD_NAME = "getUserRealTimeForecastExByStop";
        String NAMESPACE = "http://miz.it/infotransit";
        String URL = "http://st.atb.no/InfoTransit/userservices.asmx";
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            Auth auth = new Auth(username_, password_);
            PropertyInfo pi = new PropertyInfo();
            pi.setName("auth");
            pi.setValue(auth);
            pi.setType(auth.getClass());

            // Set the bus stop id property
            request.addProperty(pi);
            request.addProperty("busStopId", new Integer(busStopId).toString());
            request.addProperty("nForecast", MAX_DEPARTURES);
            request.addProperty("nHours", NUM_HOURS);
            
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(NAMESPACE, "WsAuthentication",
                    new Auth().getClass());
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            //androidHttpTransport.debug = true;
            androidHttpTransport.call(SOAP_ACTION, envelope);
            //System.out.println(androidHttpTransport.requestDump);
            //System.out.println(androidHttpTransport.responseDump);

            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
