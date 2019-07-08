package io.uacf.core.api;

import com.google.gson.annotations.Expose;

public class UacfApiEnvironment {
    @Expose
    private String baseUrl;
    @Expose
    private String clientId;
    @Expose
    private String clientSecret;
    @Expose
    private String name;

    public UacfApiEnvironment() {
    }

    public UacfApiEnvironment(String str, String str2, String str3, String str4) {
        this.name = str;
        this.baseUrl = str2;
        this.clientSecret = str4;
        this.clientId = str3;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }
}
