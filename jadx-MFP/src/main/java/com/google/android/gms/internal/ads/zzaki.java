package com.google.android.gms.internal.ads;

final class zzaki implements zzbco {
    private final /* synthetic */ zzaji zzdkk;
    private final /* synthetic */ zzbcl zzdkm;

    zzaki(zzakg zzakg, zzbcl zzbcl, zzaji zzaji) {
        this.zzdkm = zzbcl;
        this.zzdkk = zzaji;
    }

    public final void run() {
        this.zzdkm.setException(new zzaju("Unable to obtain a JavascriptEngine."));
        this.zzdkk.release();
    }
}
