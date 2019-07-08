package com.google.android.gms.internal.ads;

public abstract class zzop {
    private zzoq zzbfj;

    public abstract zzor zza(zzga[] zzgaArr, zzma zzma) throws zzff;

    public abstract void zzd(Object obj);

    public final void zza(zzoq zzoq) {
        this.zzbfj = zzoq;
    }

    /* access modifiers changed from: protected */
    public final void invalidate() {
        zzoq zzoq = this.zzbfj;
        if (zzoq != null) {
            zzoq.zzbu();
        }
    }
}
