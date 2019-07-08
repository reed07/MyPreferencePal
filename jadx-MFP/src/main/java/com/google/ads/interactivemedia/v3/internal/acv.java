package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent.AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventListener;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType;
import com.google.ads.interactivemedia.v3.api.AdProgressInfo;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.BaseDisplayContainer;
import com.google.ads.interactivemedia.v3.api.BaseManager;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.impl.data.CompanionData;
import com.google.ads.interactivemedia.v3.impl.data.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: IMASDK */
abstract class acv implements BaseManager, adw {
    protected final aeb a;
    protected final String b;
    protected AdsRenderingSettings c;
    protected ada d;
    protected adb e;
    protected boolean f = false;
    private final List<AdEventListener> g = new ArrayList(1);
    private final add h = new add();
    private final Context i;
    private c j;
    private AdProgressInfo k;
    private adt l;
    private aee m;

    protected acv(String str, aeb aeb, BaseDisplayContainer baseDisplayContainer, adt adt, aee aee, Context context, boolean z) throws AdError {
        this.b = str;
        this.a = aeb;
        this.i = context;
        this.c = new acn();
        if (adt != null) {
            this.l = adt;
        } else {
            this.l = new adt(str, aeb, baseDisplayContainer.getAdContainer());
        }
        this.l.a(z);
        if (aee == null) {
            aee = null;
        } else {
            aee.a(this.b);
            aee.b(baseDisplayContainer.getAdContainer());
            addAdEventListener(aee);
            addAdErrorListener(aee);
            act act = (act) baseDisplayContainer;
            for (View c2 : act.b()) {
                aee.c(c2);
            }
            act.a(aee);
        }
        this.m = aee;
        aeb.a((adw) this, str);
        this.l.a();
    }

    public void a(Map<String, CompanionData> map) {
    }

    public abstract boolean isCustomPlaybackUsed();

    public void init() {
        init(null);
    }

    public void init(AdsRenderingSettings adsRenderingSettings) {
        if (adsRenderingSettings == null) {
            adsRenderingSettings = this.c;
        }
        this.c = adsRenderingSettings;
        HashMap hashMap = new HashMap();
        hashMap.put("adsRenderingSettings", this.c);
        adb adb = this.e;
        if (adb != null) {
            VideoProgressUpdate a2 = adb.a();
            if (!a2.equals(VideoProgressUpdate.VIDEO_TIME_NOT_READY)) {
                double currentTime = (double) a2.getCurrentTime();
                StringBuilder sb = new StringBuilder(68);
                sb.append("AdsManager.init -> Setting contentStartTime ");
                sb.append(currentTime);
                Log.d("IMASDK", sb.toString());
                hashMap.put("contentStartTime", Double.valueOf(currentTime));
            }
        }
        if (!isCustomPlaybackUsed()) {
            hashMap.put("sdkOwnedPlayer", Boolean.valueOf(true));
        }
        this.a.a(this.c);
        this.a.b(new ado(adq.adsManager, adr.init, this.b, hashMap));
    }

    public Ad getCurrentAd() {
        return this.j;
    }

    public void addAdErrorListener(AdErrorListener adErrorListener) {
        this.h.a(adErrorListener);
    }

    public void removeAdErrorListener(AdErrorListener adErrorListener) {
        this.h.b(adErrorListener);
    }

    public void addAdEventListener(AdEventListener adEventListener) {
        this.g.add(adEventListener);
    }

    public void removeAdEventListener(AdEventListener adEventListener) {
        this.g.remove(adEventListener);
    }

    /* access modifiers changed from: protected */
    public void a() {
        aee aee = this.m;
        if (aee != null && aee.d()) {
            Log.d("IMASDK", "OMID ad session ended on BaseManager destroy.");
        }
        adb adb = this.e;
        if (adb != null) {
            adb.c();
        }
        this.l.b();
        this.a.b(this.b);
        this.j = null;
    }

    /* access modifiers changed from: protected */
    public final void a(adr adr) {
        this.a.b(new ado(adq.adsManager, adr, this.b));
    }

    private final void a(AdEventType adEventType, Map<String, String> map) {
        acd acd = new acd(adEventType, this.j, map);
        for (AdEventListener onAdEvent : this.g) {
            onAdEvent.onAdEvent(acd);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(AdErrorEvent adErrorEvent) {
        this.h.a(adErrorEvent);
    }

    public void a(adv adv) {
        AdEventType adEventType = adv.a;
        c cVar = adv.b;
        switch (adEventType.ordinal()) {
            case 1:
                String clickThruUrl = cVar.getClickThruUrl();
                if (!qi.c(clickThruUrl)) {
                    this.a.c(clickThruUrl);
                    break;
                }
                break;
            case 2:
            case 13:
                this.k = null;
                break;
            case 4:
                adb adb = this.e;
                if (adb != null) {
                    adb.c();
                }
                this.l.c();
                break;
            case 5:
                adb adb2 = this.e;
                if (adb2 != null) {
                    adb2.b();
                }
                this.l.d();
                break;
            case 12:
                if (this.c.getFocusSkipButtonWhenAvailable()) {
                    a(this.b);
                    break;
                }
                break;
            case 14:
                if (cVar != null) {
                    this.j = cVar;
                    break;
                }
                break;
            case 16:
                if (!qi.c(adv.f)) {
                    this.a.c(adv.f);
                    break;
                }
                break;
            case 18:
                this.j = cVar;
                break;
            case 19:
                this.k = adv.e;
                break;
            case 22:
                this.k = null;
                break;
        }
        if (adv.c != null) {
            a(adEventType, adv.c);
        } else {
            a(adEventType, null);
        }
        if (adEventType == AdEventType.COMPLETED || adEventType == AdEventType.SKIPPED) {
            this.j = null;
        }
    }

    /* access modifiers changed from: protected */
    public final void a(String str) {
        if (aes.a(this.i, this.a.c())) {
            this.a.b().requestFocus();
            this.a.b(new ado(adq.videoDisplay, adr.focusSkipButton, str));
        }
    }

    public void a(AdErrorType adErrorType, int i2, String str) {
        b(new acc(new AdError(adErrorType, i2, str)));
    }

    public void a(AdErrorType adErrorType, AdErrorCode adErrorCode, String str) {
        b(new acc(new AdError(adErrorType, adErrorCode, str)));
    }

    private final void b(AdErrorEvent adErrorEvent) {
        this.k = null;
        a(adErrorEvent);
    }

    public AdProgressInfo getAdProgressInfo() {
        return this.k;
    }
}
