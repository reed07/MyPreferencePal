package com.google.android.gms.internal.ads;

import android.view.View;

final class zzbgj implements Runnable {
    private final /* synthetic */ View val$view;
    private final /* synthetic */ zzawr zzeyn;
    private final /* synthetic */ int zzeyo;
    private final /* synthetic */ zzbgh zzeyp;

    zzbgj(zzbgh zzbgh, View view, zzawr zzawr, int i) {
        this.zzeyp = zzbgh;
        this.val$view = view;
        this.zzeyn = zzawr;
        this.zzeyo = i;
    }

    public final void run() {
        this.zzeyp.zza(this.val$view, this.zzeyn, this.zzeyo - 1);
    }
}
