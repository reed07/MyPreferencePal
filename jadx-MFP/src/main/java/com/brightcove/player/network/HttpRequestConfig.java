package com.brightcove.player.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestConfig {
    private final String brightcoveAuthorizationToken;
    private final Map<String, String> queryParameters;
    private final Map<String, String> requestHeaders;

    public static class Builder {
        /* access modifiers changed from: private */
        public String brightcoveAuthorizationToken = "";
        /* access modifiers changed from: private */
        public Map<String, String> queryParameters = new HashMap();
        /* access modifiers changed from: private */
        public Map<String, String> requestHeaders = new HashMap();

        public Builder setBrightcoveAuthorizationToken(@NonNull String str) {
            this.brightcoveAuthorizationToken = str;
            return this;
        }

        public Builder addRequestHeaders(@Nullable Map<String, String> map) {
            if (map != null) {
                this.requestHeaders.putAll(map);
            }
            return this;
        }

        public Builder addRequestHeader(@NonNull String str, @NonNull String str2) {
            this.requestHeaders.put(str, str2);
            return this;
        }

        public Builder addQueryParameters(@Nullable Map<String, String> map) {
            if (map != null) {
                this.queryParameters.putAll(map);
            }
            return this;
        }

        public Builder addQueryParameter(@NonNull String str, @NonNull String str2) {
            this.queryParameters.put(str, str2);
            return this;
        }

        public HttpRequestConfig build() {
            return new HttpRequestConfig(this);
        }
    }

    private HttpRequestConfig(Builder builder) {
        this.brightcoveAuthorizationToken = builder.brightcoveAuthorizationToken;
        this.requestHeaders = builder.requestHeaders;
        this.queryParameters = builder.queryParameters;
    }

    @NonNull
    public String getBrightcoveAuthorizationToken() {
        return this.brightcoveAuthorizationToken;
    }

    @NonNull
    public Map<String, String> getRequestHeaders() {
        return Collections.unmodifiableMap(this.requestHeaders);
    }

    @NonNull
    public Map<String, String> getQueryParameters() {
        return Collections.unmodifiableMap(this.queryParameters);
    }
}
