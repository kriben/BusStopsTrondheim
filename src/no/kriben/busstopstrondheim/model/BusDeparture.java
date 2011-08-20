package no.kriben.busstopstrondheim.model;

public class BusDeparture {

    private String line_;
    private String time_;

    public BusDeparture(String line, String time) {
        assert !line.isEmpty();
        assert !time.isEmpty();
        line_ = line;
        time_ = time;
    }

    public String getTime() {
        return time_;
    }

    public String getLine() {
        return line_;
    }
}
