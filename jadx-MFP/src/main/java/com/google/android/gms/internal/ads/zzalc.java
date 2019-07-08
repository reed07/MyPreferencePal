package com.google.android.gms.internal.ads;

final class zzalc implements Runnable {
    private final /* synthetic */ zzala zzdnp;
    private final /* synthetic */ zzbcb zzdnq;

    zzalc(zzala zzala, zzbcb zzbcb) {
        this.zzdnp = zzala;
        this.zzdnq = zzbcb;
    }

    public final void run() {
        for (zzbcb zzbcb : this.zzdnp.zzdnl.keySet()) {
            if (zzbcb != this.zzdnq) {
                ((zzaku) this.zzdnp.zzdnl.get(zzbcb)).cancel();
            }
        }
    }
}
