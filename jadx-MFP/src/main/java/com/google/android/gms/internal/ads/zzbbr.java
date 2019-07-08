package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;

final /* synthetic */ class zzbbr implements Runnable {
    private final zzbbn zzeoz;
    private final zzbcb zzepa;

    zzbbr(zzbbn zzbbn, zzbcb zzbcb) {
        this.zzeoz = zzbbn;
        this.zzepa = zzbcb;
    }

    public final void run() {
        Throwable e;
        zzbbn zzbbn = this.zzeoz;
        try {
            zzbbn.zzl(this.zzepa.get());
        } catch (ExecutionException e2) {
            e = e2.getCause();
            zzbbn.zzb(e);
        } catch (InterruptedException e3) {
            e = e3;
            Thread.currentThread().interrupt();
            zzbbn.zzb(e);
        } catch (Exception e4) {
            e = e4;
            zzbbn.zzb(e);
        }
    }
}
