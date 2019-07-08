package com.google.android.gms.tagmanager;

final class zzca implements Runnable {
    private final /* synthetic */ zzby zzbcs;
    private final /* synthetic */ long zzbct;
    private final /* synthetic */ String zzbcu;
    private final /* synthetic */ zzbz zzbcv;

    zzca(zzbz zzbz, zzby zzby, long j, String str) {
        this.zzbcv = zzbz;
        this.zzbcs = zzby;
        this.zzbct = j;
        this.zzbcu = str;
    }

    public final void run() {
        if (this.zzbcv.zzbcr == null) {
            zzfn zzqe = zzfn.zzqe();
            zzqe.zza(this.zzbcv.zzri, this.zzbcs);
            this.zzbcv.zzbcr = zzqe.zzqf();
        }
        this.zzbcv.zzbcr.zzb(this.zzbct, this.zzbcu);
    }
}
