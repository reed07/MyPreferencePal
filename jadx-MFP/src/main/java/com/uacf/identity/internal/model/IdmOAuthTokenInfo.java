package com.uacf.identity.internal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uacf.core.util.Strings;
import java.util.Calendar;

public class IdmOAuthTokenInfo {
    /* access modifiers changed from: private */
    @SerializedName("accessToken")
    @Expose
    public String accessToken;
    @SerializedName("access_token")
    @Expose
    private String access_token;
    /* access modifiers changed from: private */
    @SerializedName("expiresIn")
    @Expose
    public int expiresIn;
    @SerializedName("expires_in")
    @Expose
    private int expires_in;
    /* access modifiers changed from: private */
    @SerializedName("idToken")
    @Expose
    public String idToken;
    @SerializedName("id_token")
    @Expose
    private String id_token;
    /* access modifiers changed from: private */
    @SerializedName("refreshToken")
    @Expose
    public String refreshToken;
    @SerializedName("refresh_token")
    @Expose
    private String refresh_token;
    /* access modifiers changed from: private */
    @SerializedName("startTime")
    @Expose
    public long startTime;
    @SerializedName("start_time")
    @Expose
    private long start_time;
    /* access modifiers changed from: private */
    @SerializedName("tokenType")
    @Expose
    public String tokenType;
    @SerializedName("token_type")
    @Expose
    private String token_type;

    public static final class Builder {
        private String accessToken;
        private int expiresIn;
        private String idToken;
        private String refreshToken;
        private long startTime;
        private String tokenType;

        public Builder() {
        }

        public Builder(IdmOAuthTokenInfo idmOAuthTokenInfo) {
            if (idmOAuthTokenInfo != null) {
                this.accessToken = idmOAuthTokenInfo.getAccessToken();
                this.tokenType = idmOAuthTokenInfo.getTokenType();
                this.expiresIn = idmOAuthTokenInfo.getExpiresIn();
                this.refreshToken = idmOAuthTokenInfo.getRefreshToken();
                this.idToken = idmOAuthTokenInfo.getIdToken();
                this.startTime = idmOAuthTokenInfo.getStartTime();
            }
        }

        public Builder withExpiresIn(int i) {
            this.expiresIn = i;
            return this;
        }

        public Builder withStartTime(long j) {
            this.startTime = j;
            return this;
        }

        public IdmOAuthTokenInfo build() {
            IdmOAuthTokenInfo idmOAuthTokenInfo = new IdmOAuthTokenInfo();
            idmOAuthTokenInfo.accessToken = this.accessToken;
            idmOAuthTokenInfo.tokenType = this.tokenType;
            idmOAuthTokenInfo.expiresIn = this.expiresIn;
            idmOAuthTokenInfo.refreshToken = this.refreshToken;
            idmOAuthTokenInfo.idToken = this.idToken;
            idmOAuthTokenInfo.startTime = this.startTime;
            return idmOAuthTokenInfo;
        }
    }

    private IdmOAuthTokenInfo() {
        this.startTime = Calendar.getInstance().getTimeInMillis();
    }

    public String getAccessToken() {
        String str = this.accessToken;
        return str != null ? str : this.access_token;
    }

    public String getTokenType() {
        String str = this.tokenType;
        return str != null ? str : this.token_type;
    }

    public int getExpiresIn() {
        int i = this.expiresIn;
        return i != 0 ? i : this.expires_in;
    }

    public long getStartTime() {
        long j = this.startTime;
        return j != 0 ? j : this.start_time;
    }

    public String getRefreshToken() {
        String str = this.refreshToken;
        return str != null ? str : this.refresh_token;
    }

    public String getIdToken() {
        String str = this.idToken;
        return str != null ? str : this.id_token;
    }

    public IdmOAuthTokenInfo setIdToken(String str) {
        this.idToken = str;
        return this;
    }

    public boolean hasAccessToken() {
        return Strings.notEmpty(getAccessToken());
    }

    public boolean accessTokenWillExpireWithin(int i) {
        return Calendar.getInstance().getTimeInMillis() > getExpirationTimeInMillis() - ((long) i);
    }

    public boolean isExpired() {
        return accessTokenWillExpireWithin(0);
    }

    public long getExpirationTimeInMillis() {
        return getStartTime() + (((long) getExpiresIn()) * 1000);
    }

    public long getTimeUntilExpirationInSeconds() {
        return (getExpirationTimeInMillis() - Calendar.getInstance().getTimeInMillis()) / 1000;
    }
}
