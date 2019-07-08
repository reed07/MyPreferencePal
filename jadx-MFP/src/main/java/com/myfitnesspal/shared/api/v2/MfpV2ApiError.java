package com.myfitnesspal.shared.api.v2;

import com.google.gson.annotations.Expose;

public class MfpV2ApiError {
    @Expose
    private String error;
    @Expose
    private String errorDescription;
    @Expose
    private Object errorDetails;
    @Expose
    private Object errorUri;

    public String getError() {
        return this.error;
    }

    public void setError(String str) {
        this.error = str;
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

    public void setErrorDescription(String str) {
        this.errorDescription = str;
    }

    public Object getErrorUri() {
        return this.errorUri;
    }

    public void setErrorUri(Object obj) {
        this.errorUri = obj;
    }

    public Object getErrorDetails() {
        return this.errorDetails;
    }

    public void setErrorDetails(Object obj) {
        this.errorDetails = obj;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getError());
        sb.append("\n");
        sb.append(getErrorDescription());
        sb.append("\nDetails");
        sb.append(getErrorDetails());
        sb.append("\nURI:");
        sb.append(getErrorUri());
        return sb.toString();
    }
}
