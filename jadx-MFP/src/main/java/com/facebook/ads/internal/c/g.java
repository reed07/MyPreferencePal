package com.facebook.ads.internal.c;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.internal.r.a;
import java.lang.ref.WeakReference;
import java.util.EnumSet;

public class g {
    public final Context a;
    public final String b;
    @Nullable
    public InterstitialAdListener c;
    @Nullable
    public String d;
    public EnumSet<CacheFlag> e;
    public String f;
    public long g = -1;
    @Nullable
    private InterstitialAd h;
    private WeakReference<InterstitialAd> i;

    public g(Context context, @Nullable InterstitialAd interstitialAd, String str) {
        this.a = context;
        this.b = str;
        this.h = interstitialAd;
        this.i = new WeakReference<>(interstitialAd);
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public InterstitialAd a() {
        InterstitialAd interstitialAd = this.h;
        return interstitialAd != null ? interstitialAd : (InterstitialAd) this.i.get();
    }

    public void a(@Nullable InterstitialAd interstitialAd) {
        if (interstitialAd != null || a.Z(this.a)) {
            this.h = interstitialAd;
        }
    }
}
