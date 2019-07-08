package com.facebook.ads;

import android.content.Context;
import android.support.annotation.UiThread;
import com.facebook.ads.internal.c.i;
import com.facebook.ads.internal.c.j;

@UiThread
public class RewardedVideoAd implements Ad {
    public static final int UNSET_VIDEO_DURATION = -1;
    private final j a;
    private final i b = new i(this.a);

    public RewardedVideoAd(Context context, String str) {
        this.a = new j(context.getApplicationContext(), str, this);
    }

    private void a(String str, boolean z) {
        this.b.a(this, str, z);
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

    public int getVideoDuration() {
        return this.a.h;
    }

    public boolean isAdInvalidated() {
        return this.b.g();
    }

    public boolean isAdLoaded() {
        return this.b.f();
    }

    public void loadAd() {
        a(null, true);
    }

    public void loadAd(boolean z) {
        a(null, z);
    }

    public void loadAdFromBid(String str) {
        a(str, true);
    }

    public void loadAdFromBid(String str, boolean z) {
        a(str, z);
    }

    public void setAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        this.a.c = rewardedVideoAdListener;
    }

    public void setExtraHints(ExtraHints extraHints) {
        this.a.d = extraHints.getHints();
    }

    public void setRewardData(RewardData rewardData) {
        this.b.a(rewardData);
    }

    public boolean show() {
        return show(-1);
    }

    public boolean show(int i) {
        return this.b.a(this, i);
    }
}
