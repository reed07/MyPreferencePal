package com.google.android.gms.internal.measurement;

final class zzao implements Runnable {
    private final /* synthetic */ zzal zzvs;
    private final /* synthetic */ String zzvu;
    private final /* synthetic */ Runnable zzvv;

    zzao(zzal zzal, String str, Runnable runnable) {
        this.zzvs = zzal;
        this.zzvu = str;
        this.zzvv = runnable;
    }

    public final void run() {
        this.zzvs.zzvq.zzy(this.zzvu);
        Runnable runnable = this.zzvv;
        if (runnable != null) {
            runnable.run();
        }
    }
}
