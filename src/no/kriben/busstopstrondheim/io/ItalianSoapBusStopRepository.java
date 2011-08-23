package no.kriben.busstopstrondheim.io;

import java.util.ArrayList;
import java.util.List;

import no.kriben.busstopstrondheim.model.BusStop;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class ItalianSoapBusStopRepository implements BusStopRepository {

    private String username_;
    private String password_;

    public ItalianSoapBusStopRepository(String username, String password) {
        assert !username.isEmpty();
        assert !password.isEmpty();
        username_ = username;
        password_ = password;
    }

    @Override
    public List<BusStop> getAll() {
        return ItalianBusStopJsonParser.parseBusStops(getData());
    }

    @Override
    public BusStop getByCode(int code) {
        // TODO: implement or remove???
        return null;
    }

    @Override
    public List<BusStop> getByCode(List<Integer> codes) {
        // TODO: duplicated with mock!!!
        List<BusStop> allBusStops = getAll();
        List<BusStop> filteredBusStops = new ArrayList<BusStop>();
        for (BusStop busStop : allBusStops) {
            if (codes.contains(busStop.getCode()))
                filteredBusStops.add(busStop);
        }

        return filteredBusStops;
    }

    public String getData() {
        String SOAP_ACTION = "http://miz.it/infotransit/GetBusStopsList";
        String METHOD_NAME = "GetBusStopsList";
        String NAMESPACE = "http://miz.it/infotransit";
        String URL = "http://st.atb.no/InfoTransit/userservices.asmx";
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            Auth auth = new Auth(username_, password_);
            PropertyInfo pi = new PropertyInfo();
            pi.setName("auth");
            pi.setValue(auth);
            pi.setType(auth.getClass());
            request.addProperty(pi);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(NAMESPACE, "WsAuthentication",
                    new Auth().getClass());
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.debug = true;

            androidHttpTransport.call(SOAP_ACTION, envelope);
            // System.out.println(androidHttpTransport.requestDump);
            // System.out.println(androidHttpTransport.responseDump);

            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
