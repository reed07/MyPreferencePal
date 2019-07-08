package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdsManager;
import com.google.ads.interactivemedia.v3.api.AdsManagerLoadedEvent;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.StreamManager;
import com.google.ads.interactivemedia.v3.api.StreamRequest;
import java.util.List;
import java.util.SortedSet;

/* compiled from: IMASDK */
public class adu {
    final /* synthetic */ ach a;

    public void a(String str, aec aec, List<Float> list, SortedSet<Float> sortedSet, boolean z) {
        AdsRequest adsRequest = (AdsRequest) this.a.f.get(str);
        try {
            ach ach = this.a;
            acl acl = new acl(str, this.a.b, aec, this.a.a(adsRequest), adsRequest.getContentProgressProvider(), list, sortedSet, this.a.h, this.a.c, z);
            ach.a((AdsManagerLoadedEvent) new acm((AdsManager) acl, adsRequest.getUserRequestContext()));
        } catch (AdError e) {
            this.a.d.a((AdErrorEvent) new acc(e, adsRequest.getUserRequestContext()));
        }
    }

    public void a(String str, aec aec, String str2, boolean z) {
        StreamRequest streamRequest = (StreamRequest) this.a.g.get(str);
        this.a.h.a(true);
        try {
            ach ach = this.a;
            ael ael = new ael(str, this.a.b, aec, streamRequest.getManifestSuffix(), this.a.h, this.a.c, str2, z, this.a.a(streamRequest));
            ach.a((AdsManagerLoadedEvent) new acm((StreamManager) ael, streamRequest.getUserRequestContext()));
        } catch (AdError e) {
            this.a.d.a((AdErrorEvent) new acc(e, streamRequest.getUserRequestContext()));
        }
    }

    public void a(String str, AdErrorType adErrorType, int i, String str2) {
        Object obj;
        if (this.a.f.get(str) != null) {
            obj = ((AdsRequest) this.a.f.get(str)).getUserRequestContext();
        } else {
            obj = ((StreamRequest) this.a.g.get(str)).getUserRequestContext();
        }
        this.a.d.a((AdErrorEvent) new acc(new AdError(adErrorType, i, str2), obj));
    }

    public void a(String str, AdErrorType adErrorType, AdErrorCode adErrorCode, String str2) {
        Object obj;
        if (this.a.f.get(str) != null) {
            obj = ((AdsRequest) this.a.f.get(str)).getUserRequestContext();
        } else {
            obj = ((StreamRequest) this.a.g.get(str)).getUserRequestContext();
        }
        this.a.d.a((AdErrorEvent) new acc(new AdError(adErrorType, adErrorCode, str2), obj));
    }

    adu(ach ach) {
        this.a = ach;
    }
}
