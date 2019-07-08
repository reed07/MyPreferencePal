package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.AdListener;

@zzark
public final class zzvx extends zzxb {
    private final AdListener zzciz;

    public zzvx(AdListener adListener) {
        this.zzciz = adListener;
    }

    public final void onAdClosed() {
        this.zzciz.onAdClosed();
    }

    public final void onAdFailedToLoad(int i) {
        this.zzciz.onAdFailedToLoad(i);
    }

    public final void onAdLeftApplication() {
        this.zzciz.onAdLeftApplication();
    }

    public final void onAdLoaded() {
        this.zzciz.onAdLoaded();
    }

    public final void onAdOpened() {
        this.zzciz.onAdOpened();
    }

    public final void onAdClicked() {
        this.zzciz.onAdClicked();
    }

    public final void onAdImpression() {
        this.zzciz.onAdImpression();
    }

    public final AdListener getAdListener() {
        return this.zzciz;
    }
}
