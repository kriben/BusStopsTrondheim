package no.kriben.busstopstrondheim.model;

public class BusDeparture {

    private String line_;
    private String time_;
    private String destination_;

    public BusDeparture(String line, String time, String destination) {
        assert !line.isEmpty();
        assert !time.isEmpty();
        assert !destination.isEmpty();
        line_ = line;
        time_ = time;
        destination_ = destination;
    }

    public String getTime() {
        return time_;
    }

    public String getLine() {
        return line_;
    }

    public String getDestination() {
        return destination_;
    }
}
