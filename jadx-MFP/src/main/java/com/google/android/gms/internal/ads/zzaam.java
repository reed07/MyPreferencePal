package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

final class zzaam implements Callable<T> {
    private final /* synthetic */ zzaac zzcnt;
    private final /* synthetic */ zzaak zzcnu;

    zzaam(zzaak zzaak, zzaac zzaac) {
        this.zzcnu = zzaak;
        this.zzcnt = zzaac;
    }

    public final T call() {
        return this.zzcnt.zza(this.zzcnu.zzcnp);
    }
}
