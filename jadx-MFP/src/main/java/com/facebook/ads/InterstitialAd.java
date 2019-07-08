package com.facebook.ads;

import android.content.Context;
import android.support.annotation.UiThread;
import com.facebook.ads.internal.c.f;
import com.facebook.ads.internal.c.g;
import java.util.EnumSet;

@UiThread
public class InterstitialAd implements Ad {
    private final g a;
    private final f b = new f(this.a);

    public InterstitialAd(Context context, String str) {
        this.a = new g(context.getApplicationContext(), this, str);
    }

    private void a(EnumSet<CacheFlag> enumSet, String str) {
        this.b.a(this, enumSet, str);
    }

    public void destroy() {
        this.b.d();
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        this.b.e();
    }

    public String getPlacementId() {
        return this.a.b;
    }

    public boolean isAdInvalidated() {
        return this.b.g();
    }

    public boolean isAdLoaded() {
        return this.b.f();
    }

    public void loadAd() {
        loadAd(CacheFlag.ALL);
    }

    public void loadAd(EnumSet<CacheFlag> enumSet) {
        a(enumSet, null);
    }

    public void loadAdFromBid(String str) {
        a(CacheFlag.ALL, str);
    }

    public void loadAdFromBid(EnumSet<CacheFlag> enumSet, String str) {
        a(enumSet, str);
    }

    public void setAdListener(InterstitialAdListener interstitialAdListener) {
        this.a.c = interstitialAdListener;
    }

    public void setExtraHints(ExtraHints extraHints) {
        this.a.d = extraHints.getHints();
    }

    public boolean show() {
        return this.b.a(this);
    }
}
