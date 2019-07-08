package com.google.android.gms.internal.ads;

final class zzapz implements Runnable {
    private final /* synthetic */ zzbcl zzdtn;
    private final /* synthetic */ String zzdto;
    private final /* synthetic */ zzapw zzdtp;

    zzapz(zzapw zzapw, zzbcl zzbcl, String str) {
        this.zzdtp = zzapw;
        this.zzdtn = zzbcl;
        this.zzdto = str;
    }

    public final void run() {
        this.zzdtn.set((zzaek) this.zzdtp.zzdtf.zzkr().get(this.zzdto));
    }
}
