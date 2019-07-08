package com.google.android.gms.internal.ads;

final class zzapi implements Runnable {
    private final /* synthetic */ zzaph zzdsq;

    zzapi(zzaph zzaph) {
        this.zzdsq = zzaph;
    }

    public final void run() {
        this.zzdsq.onStop();
    }
}
