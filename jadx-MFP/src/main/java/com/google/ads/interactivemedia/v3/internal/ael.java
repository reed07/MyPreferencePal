package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.util.Log;
import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent.AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventListener;
import com.google.ads.interactivemedia.v3.api.AdProgressInfo;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.CuePoint;
import com.google.ads.interactivemedia.v3.api.StreamDisplayContainer;
import com.google.ads.interactivemedia.v3.api.StreamManager;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: IMASDK */
public final class ael extends acv implements StreamManager {
    private final String g;
    private List<CuePoint> h;
    private aen i;

    ael(String str, aeb aeb, aec aec, String str2, aee aee, Context context, String str3, boolean z, StreamDisplayContainer streamDisplayContainer) throws AdError {
        streamDisplayContainer.getVideoStreamPlayer();
        this(str, aeb, aec, streamDisplayContainer, str2, null, null, aee, context, str3, z);
    }

    public final boolean isCustomPlaybackUsed() {
        return true;
    }

    private ael(String str, aeb aeb, aec aec, StreamDisplayContainer streamDisplayContainer, String str2, aen aen, adt adt, aee aee, Context context, String str3, boolean z) throws AdError {
        String str4 = str;
        super(str4, aeb, streamDisplayContainer, null, aee, context, z);
        this.h = new ArrayList();
        this.g = str3;
        aen aen2 = new aen(str4, aec, aeb, this, streamDisplayContainer, str2, context);
        this.i = aen2;
        this.i.g();
        addAdErrorListener(this.i);
        aeb aeb2 = aeb;
        aeb.a((aep) this.i, str);
    }

    public final void init(AdsRenderingSettings adsRenderingSettings) {
        super.init(adsRenderingSettings);
        aen aen = this.i;
        this.c.getDisableUi();
        aen.a();
    }

    public final VideoProgressUpdate getAdProgress() {
        if (this.f) {
            return VideoProgressUpdate.VIDEO_TIME_NOT_READY;
        }
        return this.i.getAdProgress();
    }

    public final void a(adv adv) {
        switch (adv.a.ordinal()) {
            case 2:
                this.i.h();
                break;
            case 3:
                this.h = adv.d;
                break;
            case 13:
                double d = adv.g;
                StringBuilder sb = new StringBuilder(54);
                sb.append("Seek time when ad is skipped: ");
                sb.append(d);
                Log.i("IMASDK", sb.toString());
                this.i.a(Math.round(adv.g * 1000.0d));
                break;
            case 14:
                this.i.a(adv.b);
                break;
            case 21:
                this.i.c();
                break;
            case 22:
                this.i.d();
                break;
            case 23:
                this.i.e();
                break;
            case 24:
                this.i.f();
                break;
        }
        super.a(adv);
    }

    public final String getStreamId() {
        return this.g;
    }

    public final List<CuePoint> getCuePoints() {
        return Collections.unmodifiableList(this.h);
    }

    public final double getStreamTimeForContentTime(double d) {
        double d2 = d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        for (CuePoint cuePoint : this.h) {
            if (cuePoint.getStartTime() > cuePoint.getEndTime()) {
                return 0.0d;
            }
            d3 += cuePoint.getStartTime() - d4;
            if (d3 > d) {
                return d2;
            }
            d2 += cuePoint.getEndTime() - cuePoint.getStartTime();
            d4 = cuePoint.getEndTime();
        }
        return d2;
    }

    public final double getContentTimeForStreamTime(double d) {
        double d2 = d;
        for (CuePoint cuePoint : this.h) {
            if (cuePoint.getStartTime() > cuePoint.getEndTime()) {
                return 0.0d;
            }
            if (d >= cuePoint.getEndTime()) {
                d2 -= cuePoint.getEndTime() - cuePoint.getStartTime();
            } else if (d < cuePoint.getEndTime() && d > cuePoint.getStartTime()) {
                d2 -= d - cuePoint.getStartTime();
            }
        }
        return d2;
    }

    public final CuePoint getPreviousCuePointForStreamTime(double d) {
        CuePoint cuePoint = null;
        for (CuePoint cuePoint2 : this.h) {
            if (cuePoint2.getStartTime() < d) {
                cuePoint = cuePoint2;
            }
        }
        return cuePoint;
    }

    public final void destroy() {
        a(adr.contentComplete);
        this.f = true;
        this.i.b();
        a();
    }

    public final /* bridge */ /* synthetic */ AdProgressInfo getAdProgressInfo() {
        return super.getAdProgressInfo();
    }

    public final /* bridge */ /* synthetic */ void a(Map map) {
        super.a(map);
    }

    public final /* bridge */ /* synthetic */ void a(AdErrorType adErrorType, AdErrorCode adErrorCode, String str) {
        super.a(adErrorType, adErrorCode, str);
    }

    public final /* bridge */ /* synthetic */ void a(AdErrorType adErrorType, int i2, String str) {
        super.a(adErrorType, i2, str);
    }

    public final /* bridge */ /* synthetic */ void removeAdEventListener(AdEventListener adEventListener) {
        super.removeAdEventListener(adEventListener);
    }

    public final /* bridge */ /* synthetic */ void addAdEventListener(AdEventListener adEventListener) {
        super.addAdEventListener(adEventListener);
    }

    public final /* bridge */ /* synthetic */ void removeAdErrorListener(AdErrorListener adErrorListener) {
        super.removeAdErrorListener(adErrorListener);
    }

    public final /* bridge */ /* synthetic */ void addAdErrorListener(AdErrorListener adErrorListener) {
        super.addAdErrorListener(adErrorListener);
    }

    public final /* bridge */ /* synthetic */ Ad getCurrentAd() {
        return super.getCurrentAd();
    }

    public final /* bridge */ /* synthetic */ void init() {
        super.init();
    }
}
