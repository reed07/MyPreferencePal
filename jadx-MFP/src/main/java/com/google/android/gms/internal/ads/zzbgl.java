package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzn;

final class zzbgl implements zzn {
    private zzn zzexw;
    private zzbgg zzeyq;

    public zzbgl(zzbgg zzbgg, zzn zzn) {
        this.zzeyq = zzbgg;
        this.zzexw = zzn;
    }

    public final void onPause() {
    }

    public final void onResume() {
    }

    public final void zziw() {
        this.zzexw.zziw();
        this.zzeyq.zzvv();
    }

    public final void zziv() {
        this.zzexw.zziv();
        this.zzeyq.zzade();
    }
}
