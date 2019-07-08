package com.google.android.gms.internal.ads;

import android.content.Context;

final /* synthetic */ class zzzd implements Runnable {
    private final zzzc zzcmx;
    private final Context zzcmy;

    zzzd(zzzc zzzc, Context context) {
        this.zzcmx = zzzc;
        this.zzcmy = context;
    }

    public final void run() {
        this.zzcmx.getRewardedVideoAdInstance(this.zzcmy);
    }
}
