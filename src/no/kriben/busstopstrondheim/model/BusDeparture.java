package no.kriben.busstopstrondheim.model;

public class BusDeparture {

    private String line_;
    private String scheduledTime_;   
    private String estimatedTime_;
    private String destination_;

    public BusDeparture(String line, String scheduledTime, String estimatedTime, String destination) {
        assert !line.isEmpty();
        assert !estimatedTime.isEmpty();
        assert !destination.isEmpty();
        line_ = line;
        scheduledTime_ = scheduledTime;
        estimatedTime_ = estimatedTime;
        destination_ = destination;
    }

    public String getScheduledTime() {
        return scheduledTime_;
    }

    public String getEstimatedTime() {
        return estimatedTime_;
    }
    
    public String getLine() {
        return line_;
    }

    public String getDestination() {
        return destination_;
    }

}
