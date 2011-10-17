package no.kriben.busstopstrondheim.io;

public interface ProgressHandler {

    public void setProgress(double fraction, String message);

    public String getMessage();

    public double getCompleteFraction();
}
