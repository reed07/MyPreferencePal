package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

final class zzaqd implements Runnable {
    private final /* synthetic */ AtomicInteger zzduc;
    private final /* synthetic */ int zzdud;
    private final /* synthetic */ zzbcl zzdue;
    private final /* synthetic */ List zzduf;

    zzaqd(AtomicInteger atomicInteger, int i, zzbcl zzbcl, List list) {
        this.zzduc = atomicInteger;
        this.zzdud = i;
        this.zzdue = zzbcl;
        this.zzduf = list;
    }

    public final void run() {
        if (this.zzduc.incrementAndGet() >= this.zzdud) {
            try {
                this.zzdue.set(zzapw.zzk(this.zzduf));
            } catch (InterruptedException | ExecutionException e) {
                zzaxz.zzc("Unable to convert list of futures to a future of list", e);
            }
        }
    }
}
