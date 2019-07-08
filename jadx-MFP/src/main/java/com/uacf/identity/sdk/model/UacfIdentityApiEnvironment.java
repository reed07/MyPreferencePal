package com.uacf.identity.sdk.model;

import com.google.gson.annotations.Expose;
import io.uacf.core.api.UacfApiEnvironment;

public class UacfIdentityApiEnvironment extends UacfApiEnvironment {
    @Expose
    private String redirectUri;

    public UacfIdentityApiEnvironment() {
    }

    public UacfIdentityApiEnvironment(String str, String str2, String str3, String str4, String str5) {
        super(str, str2, str3, str4);
        this.redirectUri = str5;
    }

    public String getRedirectUri() {
        return this.redirectUri;
    }
}
