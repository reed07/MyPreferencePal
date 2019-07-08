package com.facebook.ads.internal.c.a;

import android.os.Bundle;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdExtendedListener;
import com.facebook.ads.internal.c.d;
import com.facebook.ads.internal.e.a;

public final class b extends a implements InterstitialAdExtendedListener {
    private final d c;

    public b(String str, c cVar, d dVar) {
        super(str, cVar);
        this.c = dVar;
    }

    public void onAdClicked(Ad ad) {
        this.b.a(1024, this.a, null);
    }

    public void onAdLoaded(Ad ad) {
        Bundle bundle = new Bundle();
        bundle.putLong("LONG_INVALIDATION_TIME_KEY", this.c.b());
        this.b.a(1020, this.a, bundle);
    }

    public void onError(Ad ad, AdError adError) {
        Bundle bundle = new Bundle();
        bundle.putString("STR_ERROR_MESSAGE_KEY", adError.getErrorMessage());
        bundle.putInt("INT_ERROR_CODE_KEY", adError.getErrorCode());
        this.b.a(1023, this.a, bundle);
    }

    public void onInterstitialActivityDestroyed() {
        this.b.a(1026, this.a, null);
        a.a().c(this.a);
    }

    public void onInterstitialDismissed(Ad ad) {
        this.b.a(1022, this.a, null);
    }

    public void onInterstitialDisplayed(Ad ad) {
        this.b.a(1021, this.a, null);
    }

    public void onLoggingImpression(Ad ad) {
        this.b.a(1025, this.a, null);
    }
}
