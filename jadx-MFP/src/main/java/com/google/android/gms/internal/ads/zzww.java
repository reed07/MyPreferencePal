package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.AdListener;

@zzark
public class zzww extends AdListener {
    private final Object lock = new Object();
    private AdListener zzclu;

    public final void zza(AdListener adListener) {
        synchronized (this.lock) {
            this.zzclu = adListener;
        }
    }

    public void onAdClosed() {
        synchronized (this.lock) {
            if (this.zzclu != null) {
                this.zzclu.onAdClosed();
            }
        }
    }

    public void onAdFailedToLoad(int i) {
        synchronized (this.lock) {
            if (this.zzclu != null) {
                this.zzclu.onAdFailedToLoad(i);
            }
        }
    }

    public void onAdLeftApplication() {
        synchronized (this.lock) {
            if (this.zzclu != null) {
                this.zzclu.onAdLeftApplication();
            }
        }
    }

    public void onAdOpened() {
        synchronized (this.lock) {
            if (this.zzclu != null) {
                this.zzclu.onAdOpened();
            }
        }
    }

    public void onAdLoaded() {
        synchronized (this.lock) {
            if (this.zzclu != null) {
                this.zzclu.onAdLoaded();
            }
        }
    }
}
