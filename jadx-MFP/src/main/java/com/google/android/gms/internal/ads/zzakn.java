package com.google.android.gms.internal.ads;

import android.content.Context;

final /* synthetic */ class zzakn implements Runnable {
    private final Context zzdks;
    private final zzakm zzdkt;

    zzakn(Context context, zzakm zzakm) {
        this.zzdks = context;
        this.zzdkt = zzakm;
    }

    public final void run() {
        zzakm.zza(this.zzdks, this.zzdkt);
    }
}
