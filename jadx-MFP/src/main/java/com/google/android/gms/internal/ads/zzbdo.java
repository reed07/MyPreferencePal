package com.google.android.gms.internal.ads;

final class zzbdo implements Runnable {
    private final /* synthetic */ zzbdk zzeru;

    zzbdo(zzbdk zzbdk) {
        this.zzeru = zzbdk;
    }

    public final void run() {
        this.zzeru.zzb("surfaceDestroyed", new String[0]);
    }
}
