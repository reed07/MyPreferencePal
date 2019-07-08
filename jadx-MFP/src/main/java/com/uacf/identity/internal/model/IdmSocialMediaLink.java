package com.uacf.identity.internal.model;

import com.google.gson.annotations.Expose;
import io.uacf.core.app.UacfSocialNetworkProvider;

public class IdmSocialMediaLink {
    @Expose
    private String appId;
    @Expose
    private String authToken;
    @Expose
    private String expiry;
    @Expose
    private UacfSocialNetworkProvider provider;
    @Expose
    private String refreshToken;
    @Expose
    private Long userId;
    @Expose
    private String username;

    public Long getUserId() {
        return this.userId;
    }

    public UacfSocialNetworkProvider getProvider() {
        return this.provider;
    }

    public String getUsername() {
        return this.username;
    }

    public String getAppId() {
        return this.appId;
    }

    public String getAuthToken() {
        return this.authToken;
    }

    public String getExpiry() {
        return this.expiry;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }
}
