package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.StreamDisplayContainer;
import com.google.ads.interactivemedia.v3.api.StreamRequest;
import com.google.ads.interactivemedia.v3.api.StreamRequest.StreamFormat;
import java.util.Map;

/* compiled from: IMASDK */
public final class aem implements StreamRequest {
    private StreamDisplayContainer a;
    private String b;
    private String c;
    private String d;
    private String e;
    private Map<String, String> f;
    private String g;
    private String h;
    private String i;
    private StreamFormat j;
    private Boolean k;
    private transient Object l;

    public final void a(String str) {
        this.b = str;
    }

    public final String getAssetKey() {
        return this.b;
    }

    public final void setAuthToken(String str) {
        this.h = str;
    }

    public final String getAuthToken() {
        return this.h;
    }

    public final void b(String str) {
        this.d = str;
    }

    public final String getContentSourceId() {
        return this.d;
    }

    public final void c(String str) {
        this.e = str;
    }

    public final String getVideoId() {
        return this.e;
    }

    public final void setFormat(StreamFormat streamFormat) {
        this.j = streamFormat;
    }

    public final StreamFormat getFormat() {
        return this.j;
    }

    public final void d(String str) {
        this.c = str;
    }

    public final String getApiKey() {
        return this.c;
    }

    public final void setStreamActivityMonitorId(String str) {
        this.i = str;
    }

    public final String getStreamActivityMonitorId() {
        return this.i;
    }

    public final void setUserRequestContext(Object obj) {
        this.l = obj;
    }

    public final Object getUserRequestContext() {
        return this.l;
    }

    public final StreamDisplayContainer getStreamDisplayContainer() {
        return this.a;
    }

    @Deprecated
    public final void a(StreamDisplayContainer streamDisplayContainer) {
        this.a = streamDisplayContainer;
    }

    public final void setAdTagParameters(Map<String, String> map) {
        this.f = map;
    }

    public final Map<String, String> getAdTagParameters() {
        return this.f;
    }

    public final void setManifestSuffix(String str) {
        this.g = str;
    }

    public final String getManifestSuffix() {
        return this.g;
    }

    public final Boolean getUseQAStreamBaseUrl() {
        return this.k;
    }

    public final void setUseQAStreamBaseUrl(Boolean bool) {
        this.k = bool;
    }
}
