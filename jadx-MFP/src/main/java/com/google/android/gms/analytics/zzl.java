package com.google.android.gms.analytics;

final class zzl implements Runnable {
    private final /* synthetic */ zzg zzss;
    private final /* synthetic */ zzk zzst;

    zzl(zzk zzk, zzg zzg) {
        this.zzst = zzk;
        this.zzss = zzg;
    }

    public final void run() {
        this.zzss.zzz().zza(this.zzss);
        for (zzn zza : this.zzst.zzsn) {
            zza.zza(this.zzss);
        }
        zzk.zzb(this.zzss);
    }
}
