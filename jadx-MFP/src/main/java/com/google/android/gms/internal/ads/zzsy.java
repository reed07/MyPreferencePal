package com.google.android.gms.internal.ads;

import android.view.View;

final class zzsy implements Runnable {
    private final /* synthetic */ View zzbyj;
    private final /* synthetic */ zzsx zzbyk;

    zzsy(zzsx zzsx, View view) {
        this.zzbyk = zzsx;
        this.zzbyj = view;
    }

    public final void run() {
        this.zzbyk.zzk(this.zzbyj);
    }
}
