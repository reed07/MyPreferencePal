package com.uacf.identity.internal.model;

import com.google.gson.annotations.Expose;

public class IdmPasswordResetRequest {
    @Expose
    private String email;
    @Expose
    private String redirectUri;

    public IdmPasswordResetRequest(String str, String str2) {
        this.email = str;
        this.redirectUri = str2;
    }
}
