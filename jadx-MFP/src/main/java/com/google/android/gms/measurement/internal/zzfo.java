package com.google.android.gms.measurement.internal;

final class zzfo implements Runnable {
    private final /* synthetic */ zzfs zzauf;
    private final /* synthetic */ zzfn zzaug;

    zzfo(zzfn zzfn, zzfs zzfs) {
        this.zzaug = zzfn;
        this.zzauf = zzfs;
    }

    public final void run() {
        this.zzaug.zza(this.zzauf);
        this.zzaug.start();
    }
}
