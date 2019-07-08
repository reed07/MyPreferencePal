package io.uacf.core.api;

import com.google.gson.annotations.Expose;

public class ApiResponseBase {
    @Expose
    String error;

    public String getError() {
        return this.error;
    }
}
