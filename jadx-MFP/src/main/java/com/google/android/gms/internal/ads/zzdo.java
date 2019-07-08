package com.google.android.gms.internal.ads;

final class zzdo implements Runnable {
    private final /* synthetic */ zzdl zzth;
    private final /* synthetic */ int zzti;
    private final /* synthetic */ boolean zztj;

    zzdo(zzdl zzdl, int i, boolean z) {
        this.zzth = zzdl;
        this.zzti = i;
        this.zztj = z;
    }

    public final void run() {
        zzbl zzb = this.zzth.zzb(this.zzti, this.zztj);
        this.zzth.zzsx = zzb;
        if (zzdl.zza(this.zzti, zzb)) {
            this.zzth.zza(this.zzti + 1, this.zztj);
        }
    }
}
