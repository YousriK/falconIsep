package com.falcon.avisep.configuration;

public class ErrorMessage {

    private String error;

    public ErrorMessage() {

    }

    public ErrorMessage(String message) {
        this.error = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
