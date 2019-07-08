package com.myfitnesspal.shared.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.myfitnesspal.shared.api.ApiResponseBase;

public class AuthTokenResponse extends ApiResponseBase {
    @Expose
    private String accessToken;
    @Expose
    private Long expiresIn;
    @SerializedName("user_id")
    @Expose
    private String obfuscatedUserId;
    @Expose
    private String refreshToken;
    @Expose
    private String tokenType;

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public void setTokenType(String str) {
        this.tokenType = str;
    }

    public Long getExpiresIn() {
        return this.expiresIn;
    }

    public void setExpiresIn(Long l) {
        this.expiresIn = l;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String str) {
        this.refreshToken = str;
    }

    public String getObfuscatedUserId() {
        return this.obfuscatedUserId;
    }

    public void setObfuscatedUserId(String str) {
        this.obfuscatedUserId = str;
    }
}
