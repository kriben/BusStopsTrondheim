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
    private List<BusStop> cached_ = null;
    private StringCache stringCache_ = new NullStringCache();
    
    public ItalianSoapBusStopRepository(String username, String password) {
        assert !username.isEmpty();
        assert !password.isEmpty();
        username_ = username;
        password_ = password;
    }

    
    public void setStringCache(StringCache stringCache) {
        stringCache_ = stringCache;
    }
    
    @Override
    public List<BusStop> getAll(ProgressHandler progressHandler) {
        // Check if we have the bus stops parse already 
        if (cached_ == null) {
            
            // Need to get the raw data from some where: is it cached?
            String data = stringCache_.get();
            
            // If it is not in cache: get it from the slow server
            if (data.length() == 0) { // Cant use isEmpty since android does not support it < 2.3
                progressHandler.setProgress(0.25, "Downloading bus stop information. Please wait...");
                data = getData();    
                stringCache_.set(data);
            }
            
            // Now try to parse the data we have: and cache that result if successful
            progressHandler.setProgress(0.75, "Download complete. Please wait...");
            List<BusStop> busStops = ItalianBusStopJsonParser.parseBusStops(data);
            if (!busStops.isEmpty()) {
                cached_ = busStops;
            }    
        }
        
        progressHandler.setProgress(1.0, "Bus stop information coming up..");
        return cached_;
    }

    @Override
    public BusStop getByCode(int code, ProgressHandler handler) {
        // TODO: implement or remove???
        return null;
    }

    @Override
    public List<BusStop> getByCode(List<Integer> codes, ProgressHandler progressHandler) {
        // TODO: duplicated with mock!!!
        List<BusStop> allBusStops = getAll(progressHandler);
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
