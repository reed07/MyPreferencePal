package com.facebook.ads.internal.c;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.RewardData;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.facebook.ads.internal.r.a;
import java.lang.ref.WeakReference;

public class j {
    public final Context a;
    public final String b;
    @Nullable
    public RewardedVideoAdListener c;
    @Nullable
    public String d;
    @Nullable
    public RewardData e;
    @Nullable
    public String f;
    public boolean g;
    public int h = -1;
    public long i;
    @Nullable
    private RewardedVideoAd j;
    private WeakReference<RewardedVideoAd> k;

    public j(Context context, String str, @Nullable RewardedVideoAd rewardedVideoAd) {
        this.a = context;
        this.b = str;
        this.j = rewardedVideoAd;
        this.k = new WeakReference<>(rewardedVideoAd);
        this.i = -1;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public RewardedVideoAd a() {
        RewardedVideoAd rewardedVideoAd = this.j;
        return rewardedVideoAd != null ? rewardedVideoAd : (RewardedVideoAd) this.k.get();
    }

    public void a(@Nullable RewardedVideoAd rewardedVideoAd) {
        if (rewardedVideoAd != null || a.Z(this.a)) {
            this.j = rewardedVideoAd;
        }
    }
}
