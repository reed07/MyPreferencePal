package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.player.ContentProgressProvider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: IMASDK */
public final class aco implements AdsRequest {
    private String a;
    private AdDisplayContainer b;
    private Map<String, String> c;
    private String d;
    private ContentProgressProvider e;
    private acp f = acp.UNKNOWN;
    private acq g = acq.UNKNOWN;
    private Float h;
    private List<String> i;
    private String j;
    private Float k;
    private Float l;
    private transient Object m;

    public final void setAdTagUrl(String str) {
        this.a = str;
    }

    public final String getAdTagUrl() {
        return this.a;
    }

    public final void setExtraParameter(String str, String str2) {
        if (this.c == null) {
            this.c = new HashMap();
        }
        this.c.put(str, str2);
    }

    public final String getExtraParameter(String str) {
        Map<String, String> map = this.c;
        if (map == null) {
            return null;
        }
        return (String) map.get(str);
    }

    public final Map<String, String> getExtraParameters() {
        return this.c;
    }

    public final void setUserRequestContext(Object obj) {
        this.m = obj;
    }

    public final Object getUserRequestContext() {
        return this.m;
    }

    public final AdDisplayContainer getAdDisplayContainer() {
        return this.b;
    }

    public final void setAdDisplayContainer(AdDisplayContainer adDisplayContainer) {
        this.b = adDisplayContainer;
    }

    public final ContentProgressProvider getContentProgressProvider() {
        return this.e;
    }

    public final void setContentProgressProvider(ContentProgressProvider contentProgressProvider) {
        this.e = contentProgressProvider;
    }

    public final String getAdsResponse() {
        return this.d;
    }

    public final void setAdsResponse(String str) {
        this.d = str;
    }

    public final acp a() {
        return this.f;
    }

    public final acq b() {
        return this.g;
    }

    public final void setAdWillAutoPlay(boolean z) {
        if (z) {
            this.f = acp.AUTO;
        } else {
            this.f = acp.CLICK;
        }
    }

    public final void setAdWillPlayMuted(boolean z) {
        if (z) {
            this.g = acq.MUTED;
        } else {
            this.g = acq.UNMUTED;
        }
    }

    public final Float c() {
        return this.h;
    }

    public final void setContentDuration(float f2) {
        this.h = Float.valueOf(f2);
    }

    public final List<String> d() {
        return this.i;
    }

    public final void setContentKeywords(List<String> list) {
        this.i = list;
    }

    public final String e() {
        return this.j;
    }

    public final void setContentTitle(String str) {
        this.j = str;
    }

    public final void setVastLoadTimeout(float f2) {
        this.k = Float.valueOf(f2);
    }

    public final Float f() {
        return this.k;
    }

    public final void setLiveStreamPrefetchSeconds(float f2) {
        this.l = Float.valueOf(f2);
    }

    public final Float g() {
        return this.l;
    }
}
