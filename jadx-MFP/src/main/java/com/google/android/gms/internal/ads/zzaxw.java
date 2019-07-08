package com.google.android.gms.internal.ads;

final class zzaxw implements Runnable {
    private final /* synthetic */ zzaxv zzekg;

    zzaxw(zzaxv zzaxv) {
        this.zzekg = zzaxv;
    }

    public final void run() {
        this.zzekg.zzeke = Thread.currentThread();
        this.zzekg.zzki();
    }
}
