package no.kriben.busstopstrondheim.io;

import java.util.List;

import no.kriben.busstopstrondheim.model.BusStop;

public interface BusStopRepository {

    public List<BusStop> getAll(ProgressHandler progressHandler);

    public BusStop getByCode(int code, ProgressHandler progressHandler);

    public List<BusStop> getByCode(List<Integer> codes, ProgressHandler progressHandler);
    
    public void setStringCache(StringCache stringCache);
}
