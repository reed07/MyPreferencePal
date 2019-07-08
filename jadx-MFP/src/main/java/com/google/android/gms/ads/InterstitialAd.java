package com.google.android.gms.ads;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.reward.AdMetadataListener;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzvt;
import com.google.android.gms.internal.ads.zzzb;

public final class InterstitialAd {
    private final zzzb zzvy;

    public InterstitialAd(Context context) {
        this.zzvy = new zzzb(context);
        Preconditions.checkNotNull(context, "Context cannot be null");
    }

    public final AdListener getAdListener() {
        return this.zzvy.getAdListener();
    }

    public final String getAdUnitId() {
        return this.zzvy.getAdUnitId();
    }

    public final boolean isLoaded() {
        return this.zzvy.isLoaded();
    }

    public final boolean isLoading() {
        return this.zzvy.isLoading();
    }

    @RequiresPermission
    public final void loadAd(AdRequest adRequest) {
        this.zzvy.zza(adRequest.zzaz());
    }

    public final void setAdListener(AdListener adListener) {
        this.zzvy.setAdListener(adListener);
        if (adListener == null || !(adListener instanceof zzvt)) {
            if (adListener == null) {
                this.zzvy.zza((zzvt) null);
            }
            return;
        }
        this.zzvy.zza((zzvt) adListener);
    }

    public final void setAdUnitId(String str) {
        this.zzvy.setAdUnitId(str);
    }

    public final String getMediationAdapterClassName() {
        return this.zzvy.getMediationAdapterClassName();
    }

    public final void show() {
        this.zzvy.show();
    }

    public final void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        this.zzvy.setRewardedVideoAdListener(rewardedVideoAdListener);
    }

    public final void setAdMetadataListener(AdMetadataListener adMetadataListener) {
        this.zzvy.setAdMetadataListener(adMetadataListener);
    }

    public final Bundle getAdMetadata() {
        return this.zzvy.getAdMetadata();
    }

    public final void zza(boolean z) {
        this.zzvy.zza(true);
    }

    public final void setImmersiveMode(boolean z) {
        this.zzvy.setImmersiveMode(z);
    }
}
