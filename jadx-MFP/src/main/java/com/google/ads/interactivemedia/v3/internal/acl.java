package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorType;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent.AdErrorListener;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventListener;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType;
import com.google.ads.interactivemedia.v3.api.AdProgressInfo;
import com.google.ads.interactivemedia.v3.api.AdsManager;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.BaseDisplayContainer;
import com.google.ads.interactivemedia.v3.api.player.ContentProgressProvider;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.impl.data.CompanionData;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

/* compiled from: IMASDK */
public final class acl extends acv implements AdsManager {
    private List<CompanionData> g;
    private List<Float> h;
    private aed i;

    acl(String str, aeb aeb, aec aec, BaseDisplayContainer baseDisplayContainer, ContentProgressProvider contentProgressProvider, List<Float> list, SortedSet<Float> sortedSet, aee aee, Context context, boolean z) throws AdError {
        this(str, aeb, aec, baseDisplayContainer, contentProgressProvider, list, sortedSet, null, null, null, aee, context, z);
    }

    private acl(String str, aeb aeb, aec aec, BaseDisplayContainer baseDisplayContainer, ContentProgressProvider contentProgressProvider, List<Float> list, SortedSet<Float> sortedSet, aed aed, adb adb, adt adt, aee aee, Context context, boolean z) throws AdError {
        String str2 = str;
        aeb aeb2 = aeb;
        ContentProgressProvider contentProgressProvider2 = contentProgressProvider;
        SortedSet<Float> sortedSet2 = sortedSet;
        super(str, aeb, baseDisplayContainer, null, aee, context, z);
        this.h = list;
        if (sortedSet2 != null && !sortedSet.isEmpty()) {
            if (contentProgressProvider2 != null) {
                this.e = new adb(contentProgressProvider2, aec.a());
                this.d = new ada(aeb, sortedSet2, str);
                this.e.a(this.d);
                this.e.b();
            } else {
                throw new AdError(AdErrorType.PLAY, AdErrorCode.PLAYLIST_NO_CONTENT_TRACKING, "Unable to handle cue points, no content progress provider configured.");
            }
        }
        aed aed2 = new aed(str, aec, aeb, this, (AdDisplayContainer) baseDisplayContainer, context);
        this.i = aed2;
        addAdErrorListener(this.i);
        aeb.a((aep) this.i, str);
    }

    public final void discardAdBreak() {
        a(adr.discardAdBreak);
    }

    public final void requestNextAdBreak() {
        if (this.e != null) {
            this.a.b(new ado(adq.contentTimeUpdate, adr.contentTimeUpdate, this.b, this.e.a()));
            a(adr.requestNextAdBreak);
        }
    }

    public final void init(AdsRenderingSettings adsRenderingSettings) {
        super.init(adsRenderingSettings);
        this.i.a(this.c.getDisableUi());
    }

    public final void start() {
        a(adr.start);
    }

    public final void pause() {
        a(adr.pause);
    }

    public final void resume() {
        a(adr.resume);
    }

    public final void skip() {
        a(adr.skip);
    }

    public final boolean isCustomPlaybackUsed() {
        return this.i.d();
    }

    private final List<CompanionData> getCurrentCompanions() {
        return this.g;
    }

    private final void onCompanionRendered(String str) {
        this.a.a(str, this.b);
    }

    private final void b(Map<String, CompanionData> map) {
        if (map != null) {
            this.g = agb.a(map.values());
        } else {
            this.g = null;
        }
    }

    public final VideoProgressUpdate getAdProgress() {
        if (this.f) {
            return VideoProgressUpdate.VIDEO_TIME_NOT_READY;
        }
        return this.i.getAdProgress();
    }

    public final void a(adv adv) {
        AdEventType adEventType = adv.a;
        int ordinal = adEventType.ordinal();
        if (ordinal != 0) {
            if (ordinal != 2) {
                if (ordinal != 5) {
                    switch (ordinal) {
                        case 13:
                            break;
                        case 14:
                            this.i.a(adv.b);
                            break;
                    }
                } else {
                    this.i.a();
                }
            }
            this.i.e();
            this.i.b();
        } else {
            a();
            if (!this.f) {
                a(adr.destroy);
            }
        }
        if (adEventType == AdEventType.COMPLETED || adEventType == AdEventType.SKIPPED) {
            b(null);
        }
        super.a(adv);
    }

    public final List<Float> getAdCuePoints() {
        return this.h;
    }

    public final void a(Map<String, CompanionData> map) {
        b(map);
    }

    public final void clicked() {
        this.a.b(new ado(adq.adsManager, adr.click, this.b));
    }

    public final void focusSkipButton() {
        if (getCurrentAd() != null && getCurrentAd().isSkippable()) {
            a(this.b);
        }
    }

    public final void destroy() {
        if (this.e != null) {
            this.e.c();
        }
        a(adr.destroy);
        this.f = true;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        this.g = null;
        this.i.c();
        super.a();
    }

    public final /* bridge */ /* synthetic */ AdProgressInfo getAdProgressInfo() {
        return super.getAdProgressInfo();
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
