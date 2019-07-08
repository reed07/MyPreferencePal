package com.google.android.gms.internal.ads;

final class zzafw implements Runnable {
    private final /* synthetic */ zzaft zzdgo;

    zzafw(zzaft zzaft) {
        this.zzdgo = zzaft;
    }

    public final void run() {
        this.zzdgo.disconnect();
    }
}
