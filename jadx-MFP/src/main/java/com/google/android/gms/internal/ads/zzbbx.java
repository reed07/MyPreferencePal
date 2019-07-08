package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;

final /* synthetic */ class zzbbx implements Runnable {
    private final zzbcl zzcak;
    private final zzbcb zzepa;

    zzbbx(zzbcl zzbcl, zzbcb zzbcb) {
        this.zzcak = zzbcl;
        this.zzepa = zzbcb;
    }

    public final void run() {
        zzbcl zzbcl = this.zzcak;
        try {
            zzbcl.set(this.zzepa.get());
        } catch (ExecutionException e) {
            zzbcl.setException(e.getCause());
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            zzbcl.setException(e2);
        } catch (Exception e3) {
            zzbcl.setException(e3);
        }
    }
}
