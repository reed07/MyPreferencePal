package com.myfitnesspal.shared.api;

import com.google.gson.annotations.Expose;
import com.uacf.core.util.Strings;
import java.util.List;

public class ApiResponseBase {
    @Expose
    private String error;
    @Expose
    private String errorDescription;
    @Expose
    private String errorUri;
    @Expose
    private List<String> headers;

    public void addHeader(String str, String str2) {
    }

    public String getError() {
        return this.error;
    }

    public ApiResponseBase setError(String str) {
        this.error = str;
        return this;
    }

    public boolean hasError() {
        return Strings.notEmpty(this.error);
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

    public ApiResponseBase setErrorDescription(String str) {
        this.errorDescription = str;
        return this;
    }

    public String getErrorUri() {
        return this.errorUri;
    }

    public ApiResponseBase setErrorUri(String str) {
        this.errorUri = str;
        return this;
    }

    public String toString() {
        return String.format("ERROR: code = %s, description = %s, uri = %s", new Object[]{Strings.toString(this.error), Strings.toString(this.errorDescription), Strings.toString(this.errorUri)});
    }
}
