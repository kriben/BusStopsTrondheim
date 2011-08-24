package no.kriben.busstopstrondheim.io;

import java.util.List;

import no.kriben.busstopstrondheim.model.BusStop;

public interface BusStopRepository {

    public List<BusStop> getAll();

    public BusStop getByCode(int code);

    public List<BusStop> getByCode(List<Integer> codes);
    
    public void setStringCache(StringCache stringCache);
}
