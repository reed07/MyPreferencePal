package com.google.android.gms.internal.ads;

final class zzavl implements Runnable {
    private final /* synthetic */ zzwb zzbod;
    private final /* synthetic */ zzalj zzeey;
    private final /* synthetic */ zzavk zzeez;

    zzavl(zzavk zzavk, zzwb zzwb, zzalj zzalj) {
        this.zzeez = zzavk;
        this.zzbod = zzwb;
        this.zzeey = zzalj;
    }

    public final void run() {
        this.zzeez.zza(this.zzbod, this.zzeey);
    }
}
