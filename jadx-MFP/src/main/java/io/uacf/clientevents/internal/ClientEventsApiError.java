package io.uacf.clientevents.internal;

import com.google.gson.annotations.Expose;

public class ClientEventsApiError {
    @Expose
    private String error;
    @Expose
    private String errorDescription;

    public String getError() {
        return this.error;
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }
}
