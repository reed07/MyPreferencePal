package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzavr;
import com.google.android.gms.internal.ads.zzawd;

final class zzan implements zzavr {
    private final /* synthetic */ zzal zzbow;

    zzan(zzal zzal) {
        this.zzbow = zzal;
    }

    public final void onRewardedVideoAdOpened() {
        this.zzbow.zziw();
    }

    public final void onRewardedVideoStarted() {
        this.zzbow.zzkf();
    }

    public final void onRewardedVideoAdClosed() {
        this.zzbow.zziv();
    }

    public final void zzc(zzawd zzawd) {
        this.zzbow.zzb(zzawd);
    }

    public final void zzkh() {
        this.zzbow.onAdClicked();
    }

    public final void onRewardedVideoAdLeftApplication() {
        this.zzbow.zzij();
    }

    public final void onRewardedVideoCompleted() {
        this.zzbow.zzkg();
    }
}
