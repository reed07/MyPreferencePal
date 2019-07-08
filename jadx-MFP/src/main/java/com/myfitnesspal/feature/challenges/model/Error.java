package com.myfitnesspal.feature.challenges.model;

public class Error {
    private String error;
    private String error_description;
    private String error_details;
    private String error_uri;

    public String getError() {
        return this.error;
    }

    public void setError(String str) {
        this.error = str;
    }

    public String getError_description() {
        return this.error_description;
    }

    public void setError_description(String str) {
        this.error_description = str;
    }

    public String getError_details() {
        return this.error_details;
    }

    public void setError_details(String str) {
        this.error_details = str;
    }

    public String getError_uri() {
        return this.error_uri;
    }

    public void setError_uri(String str) {
        this.error_uri = str;
    }
}
