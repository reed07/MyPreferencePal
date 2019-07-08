package com.facebook.ads.internal.c.a;

import android.os.Bundle;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.S2SRewardedVideoAdExtendedListener;
import com.facebook.ads.internal.c.e;
import com.facebook.ads.internal.c.j;

public class d extends a implements S2SRewardedVideoAdExtendedListener {
    private final e c;
    private final j d;

    public d(String str, c cVar, e eVar, j jVar) {
        super(str, cVar);
        this.c = eVar;
        this.d = jVar;
    }

    public void onAdClicked(Ad ad) {
        this.b.a(2104, this.a, null);
    }

    public void onAdLoaded(Ad ad) {
        Bundle bundle = new Bundle();
        bundle.putLong("LONG_INVALIDATION_TIME_KEY", this.c.b());
        bundle.putInt("INT_RV_VIDEO_DURATION_KEY", this.d.h);
        this.b.a(AdError.BROKEN_MEDIA_ERROR_CODE, this.a, bundle);
    }

    public void onError(Ad ad, AdError adError) {
        Bundle bundle = new Bundle();
        bundle.putString("STR_ERROR_MESSAGE_KEY", adError.getErrorMessage());
        bundle.putInt("INT_ERROR_CODE_KEY", adError.getErrorCode());
        this.b.a(2103, this.a, bundle);
    }

    public void onLoggingImpression(Ad ad) {
        this.b.a(2105, this.a, null);
    }

    public void onRewardServerFailed() {
        this.b.a(2109, this.a, null);
    }

    public void onRewardServerSuccess() {
        this.b.a(2108, this.a, null);
    }

    public void onRewardedVideoActivityDestroyed() {
        this.b.a(2106, this.a, null);
    }

    public void onRewardedVideoClosed() {
        this.b.a(2110, this.a, null);
    }

    public void onRewardedVideoCompleted() {
        this.b.a(2107, this.a, null);
    }
}
