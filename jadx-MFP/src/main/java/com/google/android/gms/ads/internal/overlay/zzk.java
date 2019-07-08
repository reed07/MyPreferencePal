package com.google.android.gms.ads.internal.overlay;

import android.graphics.drawable.Drawable;

final class zzk implements Runnable {
    private final /* synthetic */ Drawable zzdrq;
    private final /* synthetic */ zzj zzdrr;

    zzk(zzj zzj, Drawable drawable) {
        this.zzdrr = zzj;
        this.zzdrq = drawable;
    }

    public final void run() {
        this.zzdrr.zzdrn.mActivity.getWindow().setBackgroundDrawable(this.zzdrq);
    }
}
