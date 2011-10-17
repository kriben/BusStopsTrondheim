package no.kriben.busstopstrondheim.io.tests;

import no.kriben.busstopstrondheim.io.ProgressHandler;


public class MockProgressHandler implements ProgressHandler {

    private String message_ = "";
    private double completeFraction_ = 0.0;
    
    
    public MockProgressHandler() {}

    @Override
    public void setProgress(double completeFraction, String message) {
        completeFraction_ = completeFraction;
        message_ = message;
    }

    @Override
    public String getMessage() {
        return message_;
    }

    @Override
    public double getCompleteFraction() {
        return completeFraction_;
    }    
}
