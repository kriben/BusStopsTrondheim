package no.kriben.busstopstrondheim.io;

import java.util.List;

import no.kriben.busstopstrondheim.model.BusDeparture;

public interface BusDepartureRepository {
    public List<BusDeparture> getAllForBusStop(int code);
}
