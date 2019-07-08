package com.google.android.gms.internal.ads;

import android.view.View;

final class zzbhw implements Runnable {
    private final /* synthetic */ View val$view;
    private final /* synthetic */ zzawr zzeyn;
    private final /* synthetic */ int zzeyo;
    private final /* synthetic */ zzbhu zzfay;

    zzbhw(zzbhu zzbhu, View view, zzawr zzawr, int i) {
        this.zzfay = zzbhu;
        this.val$view = view;
        this.zzeyn = zzawr;
        this.zzeyo = i;
    }

    public final void run() {
        this.zzfay.zza(this.val$view, this.zzeyn, this.zzeyo - 1);
    }
}
