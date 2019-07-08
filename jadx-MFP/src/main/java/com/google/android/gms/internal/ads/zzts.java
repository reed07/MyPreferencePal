package com.google.android.gms.internal.ads;

final class zzts implements zzsw {
    private final /* synthetic */ zztq zzbzt;

    zzts(zztq zztq) {
        this.zzbzt = zztq;
    }

    public final void zzs(boolean z) {
        if (z) {
            this.zzbzt.connect();
        } else {
            this.zzbzt.disconnect();
        }
    }
}
