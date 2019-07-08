package com.google.android.gms.tagmanager;

final class zzfp implements Runnable {
    private final /* synthetic */ zzfn zzbgl;

    zzfp(zzfn zzfn) {
        this.zzbgl = zzfn;
    }

    public final void run() {
        this.zzbgl.zzbgb.dispatch();
    }
}
